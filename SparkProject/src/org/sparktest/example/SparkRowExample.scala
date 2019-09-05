package org.sparktest.example

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.Row

object SparkRowExample {
  def main(args : Array[String]){
    val sparkConf = new SparkConf().setAppName("ROWRDD").setMaster("local[*]")
    val sc = new SparkContext(sparkConf)
    val file = sc.textFile(".\\resources\\employee.csv", 2)
    val header = file.first()
    val withoutHeader = file.filter(x=> (x!= header))
    withoutHeader.collect.foreach(println)
    val rows=withoutHeader.map(x=>x.split(",")).map(x=>Row.fromSeq(x))
    rows.collect().foreach(println)
   val row = Row("name","phone","amount")
   val rowRdd=withoutHeader.map(row => row.toString())
   rowRdd.collect().foreach(println)
    
  }
}