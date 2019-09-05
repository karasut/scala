package org.sparktest.example

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object SparkMapPartitionExample {
  def main(args : Array[String]){
    val sparkconf=new SparkConf().setAppName("MapPartition").setMaster("local[*]")
    val sc= new SparkContext(sparkconf)
    val lines=sc.textFile("C:\\Users\\karasut_old\\CARE.sql")
    val maprdd=lines.mapPartitions(
      (line =>(List(line.next()).iterator))
    )
    maprdd.collect();
  }
}