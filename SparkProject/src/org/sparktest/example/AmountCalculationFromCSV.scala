package org.sparktest.example

import org.apache.spark.SparkConf
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object AmountCalculationFromCSV {
  def main(args : Array[String]){
    val userName : String="John"
    val sparkconf=new SparkConf().setAppName("BillCalc").setMaster("local[*]")
    val sparkcontext=new SparkContext(sparkconf)
    val initialRdd= sparkcontext.textFile(".\\resources\\employee.csv")
    val header=initialRdd.first()
/*
 * For single User filteration
 *     val filterRdd= initialRdd.filter( x => x !=header && x.contains(userName))
 */
    val filterRdd= initialRdd.filter( x => x !=header)
    val splittedRdd=filterRdd.map(x => (x.split(",")(0),x.split(",")(2).toInt))
    val rdd= splittedRdd.reduceByKey((x, y) => x + y);
    rdd.foreach(println)
  }
}