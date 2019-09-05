package org.sparktest.example

import org.apache.spark.SparkConf
import org.apache.spark.sql.SQLContext
import org.apache.spark.SparkContext

object AvroFileRead {
  def main(args : Array[String]){
    val sparkConf = new SparkConf().setAppName("AvroFileRead").setMaster("local[*]")
    val sqlContext = new SQLContext(new SparkContext(sparkConf))
    val avroRead = sqlContext.read.format("avro").load(".\\resources\\userdata1.avro").groupBy("country").count
    avroRead.collect.foreach(println)
  }
}