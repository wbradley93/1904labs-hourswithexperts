package com.labs1904.hwe.consumers

import com.labs1904.hwe.producers.SimpleProducer
import com.labs1904.hwe.util.Util
import com.labs1904.hwe.util.Util.getScramAuthString
import net.liftweb.json.DefaultFormats
import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord, ConsumerRecords, KafkaConsumer}
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.common.serialization.StringDeserializer

import java.time.Duration
import java.util.{Arrays, Properties, UUID}

object HweConsumer {
  val BootstrapServer : String = "CHANGEME"
  val consumerTopic: String = "question-1"
  val producerTopic: String = "question-1-output"
  val username: String = "CHANGEME"
  val password: String = "CHANGEME"
  //Use this for Windows
  //val trustStore: String = "src\\main\\resources\\kafka.client.truststore.jks"
  //Use this for Mac
  val trustStore: String = "src/main/resources/kafka.client.truststore.jks"

  implicit val formats: DefaultFormats.type = DefaultFormats

  case class RawUser(unknownNumber: String, username: String, name: String, gender: String, email: String, birthdate: String)
  case class EnrichedUser(unknownNumber: String, username: String, name: String, gender: String, email: String,
                          birthdate: String, numberAsWord: String, hweDeveloper: String = "Wes Bradley")

  def main(args: Array[String]): Unit = {

    // Create the KafkaConsumer
    val consumerProperties = SimpleConsumer.getProperties(BootstrapServer)
    val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](consumerProperties)

    // Create the KafkaProducer
    val producerProperties = SimpleProducer.getProperties(BootstrapServer)
    val producer = new KafkaProducer[String, String](producerProperties)

    // Subscribe to the topic
    consumer.subscribe(Arrays.asList(consumerTopic))

    while ( {
      true
    }) {
      // poll for new data
      val duration: Duration = Duration.ofMillis(100)
      val records: ConsumerRecords[String, String] = consumer.poll(duration)

      records.forEach((record: ConsumerRecord[String, String]) => {
        // Retrieve the message from each record
        val message = record.value()
        println(s"Message Received: $message")

        val Array(v1,v2,v3,v4,v5,v6) = message.split('\t')
        val userObject = EnrichedUser(v1,v2,v3,v4,v5,v6,Util.numberToWordMap(v1.toInt))
        producer.send(new ProducerRecord[String, String](producerTopic, userObject.productIterator.mkString(",")))
      })
    }
  }
}