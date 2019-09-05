package org.sparktest.example

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object Test {
  def main(args : Array[String]){
    val sparkConf=new SparkConf().setAppName("Test").setMaster("local[*]")
    val sc= new SparkContext(sparkConf)
    val initRdd=sc.parallelize(1 to 9,3)
    val finalRdd= initRdd.groupBy(x=>(if (x%2 == 0) "even" else "odd"))
    finalRdd.collect.foreach(println)
  }
}