package org.sparktest.example

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import scala.collection.mutable.Node
import scala.collection.Map
object BroadcastTest {
  def main(args: Array[String]) {
    val sparkConf = new SparkConf().setAppName("BroadCast App").setMaster("local[*]")
    val sc = new SparkContext(sparkConf)
    val dialedNoRdd = sc.textFile(".\\resources\\DailedNumber.csv")
    val lookUpRdd = sc.textFile(".\\resources\\LookUp.csv").flatMap(x => x.split("\n")).map(x => (x.split(",")(0), x.split(",")(1)))
    val lookUPtable = sc.broadcast(lookUpRdd.collectAsMap())
    println(s"lookUPtable ======>$lookUPtable")
    lookUpRdd.foreach(println)
    val finalRdd = dialedNoRdd.flatMap(x=> x.split("\n")).map(x=>(lookUPtable.value(x.split(",")(0)),x.split(",")(1),x.split(",")(2)))
    finalRdd.foreach(println)
   /* val distinctDialedRdd= dialedNoRdd.flatMap(x=>x.split("\n")).map(x=>(x.split(",")(0),x.split(",")(0))).collectAsMap()
    val distinctSecondRdd= sc.textFile(".\\resources\\LookUp.csv").flatMap(x => x.split("\n")).map(x => (x.split(",")(0),x.split(",")(0))).collectAsMap()
    println(s"First Element In distinctDialedRdd ${distinctDialedRdd}")
    println(s"First Element In distinctSecondRdd ${distinctSecondRdd}")
    val lookup= (distinctDialedRdd,distinctSecondRdd).zipped.map{
      (dialed,second) => (dialed._1,second._1)
    }
    println(s"look Up ::::: $lookup")*/
   
  }
}