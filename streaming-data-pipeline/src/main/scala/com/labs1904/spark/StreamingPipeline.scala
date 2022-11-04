package com.labs1904.spark

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.{OutputMode, Trigger}
import org.apache.hadoop.hbase.{HBaseConfiguration, TableName}
import org.apache.hadoop.hbase.client.{ConnectionFactory, Get}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.spark.sql.types.{StructType, StringType, IntegerType}

case class Review(marketplace: String, customer_id: String, review_id: String, product_id: String, product_parent: String,
                  product_title: String, product_category: String, star_rating: String, helpful_votes: String, total_votes: String,
                  vine: String, verified_purchase: String, review_headline: String, review_body: String, review_date: String)

case class UserReview(marketplace: String, customer_id: String, review_id: String, product_id: String, product_parent: String,
                      product_title: String, product_category: String, star_rating: String, helpful_votes: String, total_votes: String,
                      vine: String, verified_purchase: String, review_headline: String, review_body: String, review_date: String,
                      birthdate: String, mail: String, name: String, sex: String, username: String)

//val schema = new StructType().add("marketplace", StringType, nullable=true)
//                             .add("customer_id", IntegerType, nullable=true)
//                             .add("review_id", StringType, nullable=true)
//                             .add("product_id", StringType, nullable=true)
//                             .add("product_parent", IntegerType, nullable=true)
//                             .add("product_title", StringType, nullable=true)
//                             .add("product_category", StringType, nullable=true)
//                             .add("star_rating", IntegerType, nullable=true)
//                             .add("helpful_votes", IntegerType, nullable=true)
//                             .add("total_votes", IntegerType, nullable=true)
//                             .add("vine", StringType, nullable=true)
//                             .add("verified_purchase", StringType, nullable=true)
//                             .add("review_headline", StringType, nullable=true)
//                             .add("review_body", StringType, nullable=true)
//                             .add("review_date", StringType, nullable=true)

/**
 * Spark Structured Streaming app
 *
 */
object StreamingPipeline {
  lazy val logger: Logger = Logger.getLogger(this.getClass)
  val jobName = "StreamingPipeline"

  val hdfsUrl = "CHANGEME"
  val bootstrapServers = "CHANGEME"
  val username = "CHANGEME"
  val password = "CHANGEME"
  val hdfsUsername = "CHANGEME" // TODO: set this to your handle

  //Use this for Windows
  //val trustStore: String = "src\\main\\resources\\kafka.client.truststore.jks"
  //Use this for Mac
  val trustStore: String = "src/main/resources/kafka.client.truststore.jks"

  def main(args: Array[String]): Unit = {
    try {
      // LOCAL
      val spark = SparkSession.builder()
        .config("spark.sql.shuffle.partitions", "3")
        .appName(jobName)
        .master("local[*]")
        .getOrCreate()

      // REMOTE
//      val spark = SparkSession.builder()
//        .config("spark.hadoop.dfs.client.use.datanode.hostname", "true")
//        .config("spark.hadoop.fs.defaultFS", hdfsUrl)
//        .config("spark.sql.shuffle.partitions", "3")
//        .appName(jobName)
//        .master("local[*]")
//        .getOrCreate()

      import spark.implicits._

      val ds = spark
        .readStream
        .format("kafka")
        .option("kafka.bootstrap.servers", bootstrapServers)
        .option("subscribe", "reviews")
        .option("startingOffsets", "earliest")
        .option("maxOffsetsPerTrigger", "20")
        .option("startingOffsets","earliest")
        .option("kafka.security.protocol", "SASL_SSL")
        .option("kafka.sasl.mechanism", "SCRAM-SHA-512")
        .option("kafka.ssl.truststore.location", trustStore)
        .option("kafka.sasl.jaas.config", getScramAuthString(username, password))
        .load()
        .selectExpr("CAST(value AS STRING)").as[String]

      val reviews = ds.map(review => {
        val vals = review.split("\t")
        Review(vals(0), vals(1), vals(2), vals(3), vals(4), vals(5), vals(6), vals(7), vals(8), vals(9), vals(10), vals(11),
               vals(12), vals(13), vals(14))
      })

      val userReviews = reviews.mapPartitions(partition => {
        val conf = HBaseConfiguration.create()
        conf.set("hbase.zookeeper.quorum", hdfsUrl)
        val connection = ConnectionFactory.createConnection(conf)
        val table = connection.getTable(TableName.valueOf(s"$hdfsUsername:users"))

        val partitionReviews = partition.map(review => {
          val get = new Get(Bytes.toBytes(review.customer_id)).addFamily(Bytes.toBytes("f1"))
          val result = table.get(get)

          UserReview(review.marketplace, review.customer_id, review.review_id, review.product_id, review.product_parent,
                     review.product_title, review.product_category, review.star_rating, review.helpful_votes, review.total_votes,
                     review.vine, review.verified_purchase, review.review_headline, review.review_body, review.review_date,
                     Bytes.toString(result.getValue(Bytes.toBytes("f1"), Bytes.toBytes("birthdate"))),
                     Bytes.toString(result.getValue(Bytes.toBytes("f1"), Bytes.toBytes("mail"))),
                     Bytes.toString(result.getValue(Bytes.toBytes("f1"), Bytes.toBytes("name"))),
                     Bytes.toString(result.getValue(Bytes.toBytes("f1"), Bytes.toBytes("sex"))),
                     Bytes.toString(result.getValue(Bytes.toBytes("f1"), Bytes.toBytes("username"))))
        }).toList.iterator

        connection.close()

        partitionReviews
      })

      // Write output to console
      val query = userReviews.writeStream
        .outputMode(OutputMode.Append())
        .format("console")
        .option("truncate", false)
        .trigger(Trigger.ProcessingTime("5 seconds"))
        .start()

      // Write output to HDFS
//      val query = userReviews.writeStream
//        .outputMode(OutputMode.Append())
//        .format("json")
//        .option("path", s"/user/${hdfsUsername}/reviews_json")
//        .option("checkpointLocation", s"/user/${hdfsUsername}/reviews_checkpoint")
//        .trigger(Trigger.ProcessingTime("5 seconds"))
//        .start()

      query.awaitTermination()
    } catch {
      case e: Exception => logger.error(s"$jobName error in main", e)
    }
  }

  def getScramAuthString(username: String, password: String) = {
    s"""org.apache.kafka.common.security.scram.ScramLoginModule required
   username=\"$username\"
   password=\"$password\";"""
  }
}
