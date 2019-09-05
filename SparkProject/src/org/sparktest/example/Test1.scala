package org.sparktest.example

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkContext

object Test1 {
  def main(args : Array[String]){
    val sparkConf=new SparkConf().setAppName("Test1").setMaster("local[*]")
    val sc= new SparkContext(sparkConf)
    val initRdd=sc.textFile(".//resources/user.csv", 3)
    val header=initRdd.first()
    val woHeaderRdd= initRdd.filter(x=>(x != header)).map(x=>x.split(","))
    val finalRdd=woHeaderRdd.filter(x=>(x!="myself")).map(x=>header.split(",").map{
        var i= 0
        y=>{
          val c = (y->x(i))
          i=i+1
          c
          }
    }).collect()
    finalRdd.map(x=>x.map(y=>println(y)))
  }
}