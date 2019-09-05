package org.sparktest.example

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object SparkReduceByKeyExample {
  def main(args: Array[String]) {
     val sparkconf=new SparkConf().setAppName("MapPartition").setMaster("local[*]")
    val sc= new SparkContext(sparkconf)
    val words = Array("one", "two", "two", "four", "five", "six", "six", "eight", "nine", "ten")
    val data = sc.parallelize(words).map(w => (w, 1)).reduceByKey(_ + _)
    data.foreach(println)

  }
}