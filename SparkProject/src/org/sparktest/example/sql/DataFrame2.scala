package org.sparktest.example.sql

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.types.StructField
import org.apache.spark.sql.types.StringType
import org.apache.spark.sql.types.IntegerType

object DataFrame2 {
  def main(args : Array[String]){
    val spark= SparkSession.builder().appName("DF2").master("local[*]").config("some.config","config").getOrCreate()
    val schema=StructType(Array(StructField("name",StringType,nullable = true),StructField("phone",StringType,nullable= true),StructField("amount",IntegerType,nullable = true)))
    val readFile=spark.read.option("header", "true").option("inferSchema", "true").schema(schema).csv(".//resources//employee.csv")
    readFile.printSchema()
    readFile.select("name", "amount").filter("amount>=500").show()
  }
}
