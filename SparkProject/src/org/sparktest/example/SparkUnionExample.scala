package org.sparktest.example

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

object SparkUnionExample {
   def main(args : Array[String]){
    val sparkconf=new SparkConf().setAppName("MapPartition").setMaster("local[*]")
    val sc= new SparkContext(sparkconf)
    val lines=sc.textFile("C:\\Users\\karasut_old\\CARE.sql")
    val list=List("My List","your list","our list")
    val listrdd=sc.parallelize(list)
    val transformrdd=lines.union(listrdd)
    transformrdd.foreach(println)
  }
}