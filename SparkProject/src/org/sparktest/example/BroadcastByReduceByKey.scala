package org.sparktest.example

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object BroadcastByReduceByKey {
  def main(args: Array[String]) {
    val sparkConf = new SparkConf().setAppName("BroadcastByReduceByKey").setMaster("local[*]")
    val sc = new SparkContext(sparkConf)
    val dialedNumRdd = sc.textFile(".\\resources\\DailedNumber.csv").flatMap(x => x.split("\n")).map(x => (x.split(",")(0), x.split(",")(0))).reduceByKey((x, y) => (x + y))
      .map(x => ("abc", List(x._1))).reduceByKey((x, y) => (x ::: y))

    val replacedNumberRdd = sc.textFile(".\\resources\\LookUp.csv").flatMap(x => x.split("\n")).map(x => (x.split(",")(0), x.split(",")(0))).reduceByKey((x, y) => (x + y))
      .map(x => ("abc", List(x._1))).reduceByKey((x, y) => (x ::: y))
    val lookup = dialedNumRdd.join(replacedNumberRdd).map(x => x._2)
    dialedNumRdd.foreach(println)
    replacedNumberRdd.foreach(println)
    lookup.foreach(println)
  }
}