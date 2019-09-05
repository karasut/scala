package org.sparktest.example

import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.types.StructField
import org.apache.spark.sql.types.IntegerType
import org.apache.spark.sql.types.StringType
import org.apache.spark.sql.SparkSession


object DataFrameTest {
  def main(args : Array[String]){
    val schemaStruct= StructType(StructField("Id",IntegerType) :: StructField("Type",StringType) :: StructField("desc",StringType) :: Nil)
    val sparkSession = SparkSession.builder().appName("DataFrameTest").config("spark.some.config.option", "some-value").master("local[*]").getOrCreate()
    import sparkSession.implicits._
    val fileDf=sparkSession.read.csv(".\\resources\\DailedNumber.csv").filter("_c0 == 1").groupBy("_c0").count()
    fileDf.printSchema()
    fileDf.show()
    
   // fileDf.foreach(println)
  }
}