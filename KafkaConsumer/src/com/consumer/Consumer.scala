package com.consumer

import scala.collection.JavaConverters._
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import scala.util.Properties
import java.util.Properties
import java.util.List
import java.util.ArrayList
import java.util.Collections
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.clients.consumer.OffsetAndMetadata

object Consumer extends App {
  val properties = new Properties()
  properties.put("bootstrap.servers", "172.20.44.63:9092") // specify the kafka IP and port
  properties.put("group.id", "ngb") // Specify the group id
  properties.put("enable.auto.commit", "false"); // to disable the autocomit
  //properties.put("partition.assignment.strategy", "1")
  properties.put("auto.commit.interval.ms", "10000"); // Describe the autocommit interval
  properties.put("session.timeout.ms", "30000"); // Describe the Session timeout
  properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
  properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

  val kafkaConsumer = new KafkaConsumer[String, String](properties)
  val topicName = new ArrayList[String]()
  topicName.add("ngb")// Topic names should be mentioned
  kafkaConsumer.subscribe(topicName)

  while (true) {
    val results = kafkaConsumer.poll(200)// poll method is used to consume from the topic which take long timeout value as input
    for (partition <- results.partitions().asScala) {
      val partitionRecords = results.records(partition);
      for (record <- partitionRecords.asScala) {
        System.out.println(record.offset() + ": " + record.value());
        //val lastOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
        val lastOffset = record.offset();
        println(s"record.offset() ${record.offset()}")
        kafkaConsumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(lastOffset)));
        Thread.sleep(5000)
      }
    }
  }
}