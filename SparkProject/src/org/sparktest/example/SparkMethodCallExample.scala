package org.sparktest.example

import org.apache.spark.rdd.RDD
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext


object SparkMethodCallExample {
  def main(args: Array[String]){
    val sparkconf=new SparkConf().setAppName("FunctionCall").setMaster("local[2]")
    val sc= new SparkContext(sparkconf);
    val lines=sc.textFile("C:\\Users\\karasut_old\\CARE.sql")
    val wordcount= getWordCountDetails(lines)
    val line= lines.take(2)
    println(wordcount)
    line.foreach(println)
    
  }
  def getWordCountDetails(rdd : RDD[String]) : Long ={
    return rdd.count();
  }
}