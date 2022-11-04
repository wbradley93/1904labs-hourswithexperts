package com.labs1904.hwe

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.{HBaseConfiguration, TableName}
import org.apache.hadoop.hbase.client.{Connection, ConnectionFactory, Delete, Get, Put, Scan}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.logging.log4j.{LogManager, Logger}
import scala.collection.JavaConverters._

object MyApp {
  lazy val logger: Logger = LogManager.getLogger(this.getClass)

  def main(args: Array[String]): Unit = {
    logger.info("MyApp starting...")
    var connection: Connection = null
    try {
      val conf = HBaseConfiguration.create()
      conf.set("hbase.zookeeper.quorum", "CHANGE ME")
      connection = ConnectionFactory.createConnection(conf)

      // Example code... change me
//      val table = connection.getTable(TableName.valueOf("table-name"))
//      val get = new Get(Bytes.toBytes("row-key"))
//      val result = table.get(get)
//      logger.debug(result)

      val table = connection.getTable(TableName.valueOf("wbradley:users"))

      // CHALLENGE 1
//      val getUser = new Get(Bytes.toBytes("10000001"))
//      val result = table.get(getUser)
//      val email = result.getValue(Bytes.toBytes("f1"), Bytes.toBytes("mail"))
//      logger.debug(Bytes.toString(email))

      // CHALLENGE 2
//      val newUser = new Put(Bytes.toBytes("99"))
//      newUser.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("username"), Bytes.toBytes("DE-HWE"))
//      newUser.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("name"), Bytes.toBytes("The Panther"))
//      newUser.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("sex"), Bytes.toBytes("F"))
//      newUser.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("favorite_color"), Bytes.toBytes("pink"))
//      table.put(newUser)
//
//      val getUser = new Get(Bytes.toBytes("99"))
//      val result = table.get(getUser)
//      val username = result.getValue(Bytes.toBytes("f1"), Bytes.toBytes("username"))
//      val name = result.getValue(Bytes.toBytes("f1"), Bytes.toBytes("name"))
//      val sex = result.getValue(Bytes.toBytes("f1"), Bytes.toBytes("sex"))
//      val favoriteColor = result.getValue(Bytes.toBytes("f1"), Bytes.toBytes("favorite_color"))
//      logger.debug(Bytes.toString(username))
//      logger.debug(Bytes.toString(name))
//      logger.debug(Bytes.toString(sex))
//      logger.debug(Bytes.toString(favoriteColor))

      // CHALLENGE 3
//      val scan = new Scan()
//      scan.withStartRow(Bytes.toBytes("10000001"), true)
//      scan.withStopRow(Bytes.toBytes("10006001"), true)
//      val scanner = table.getScanner(scan)
//      var count = 0
//      scanner.forEach(row => count += 1)
//      logger.debug(count)

      // CHALLENGE 4
//      val deleteUser = new Delete(Bytes.toBytes("99"))
//      table.delete(deleteUser)
//      val getUser = new Get(Bytes.toBytes("99"))
//      val result = table.get(getUser)
//      logger.debug(result)

      // CHALLENGE 5
//      val batchGets = List("9005729", "500600", "30059640", "6005263", "800182").map(id => new Get(Bytes.toBytes(id))).asJava
//      val batchResult = table.get(batchGets)
//      batchResult.foreach(result => logger.debug(Bytes.toString(result.getValue(Bytes.toBytes("f1"), Bytes.toBytes("mail")))))
    }
  }
}
