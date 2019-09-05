package org.sparktest.example.sql

import org.apache.spark.sql.SparkSession

object DataFrame1 {
  def main(args: Array[String]) {
    val spark = SparkSession.builder().appName("DF1").master("local[*]").config("spark.sql.warehouse.dir", "warehouseLocation").getOrCreate()
    val readFile=spark.read.format("csv").option("sep", ",").load(".//resources//employee.csv")
    val header=readFile.first()
    val totalSalary=(readFile.filter(x=>(x!=header)).select("_c2").rdd.map(_(0).toString().toInt)).reduce(_+_)
    println(s"totalSalary ::::::::: $totalSalary") 
  }
}