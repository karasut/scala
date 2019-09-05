package org.sparktest.example

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.hive.HiveContext

object LoadJsonDataUsingHive {
  def main(args : Array[String]){
    val sparkConf= new SparkConf().setAppName("JsonToHive").setMaster("local[*]")
    val sc= new SparkContext(sparkConf)
    val hc=new HiveContext(sc)
    hc.sql("create database HiveTest");
    hc.sql("create table employee(json string)")
    hc.sql("load data local inpath './/resources//ename.json' into table employee")
    hc.sql("select * from employee").collect().foreach(println)
  }
}