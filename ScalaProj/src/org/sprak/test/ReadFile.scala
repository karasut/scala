package org.sprak.test

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object ReadFile {
  def main(args : Array[String]){
    val conf= new SparkConf().setAppName("Test").setMaster("local[*]");
    val sc= new SparkContext(conf)
    val lines=sc.textFile("C:\\Users\\karasut_old\\CARE.sql");
    lines.foreach(println)
    
  }
}