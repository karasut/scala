package org.sparktest.example

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object ShortElementIn2Files {
  def main(args : Array[String]){
    val sparkConf=new SparkConf().setAppName("MinVal").setMaster("local[*]")
    val sc=new SparkContext(sparkConf)
    val initRdd1= sc.textFile(".\\resources\\firstFile.csv")
    val initRdd2= sc.textFile(".\\resources\\SecondFile.csv")
    val mergedRdd=initRdd1.union(initRdd2)
    val sortedRdd=mergedRdd.flatMap(x=>x.split(",")).reduce(getMin)
    sortedRdd.foreach(println)
  }
  def getMin(a : String,b: String)=if(a.toInt > b.toInt)  b else a
}