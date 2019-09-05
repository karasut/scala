package org.sparktest.example

import org.apache.spark.sql.SparkSession

object GenericLoadAndSaveFuncSparkSql {
  def main(args : Array[String]){
    val spark = SparkSession.builder().appName("ParquetLoad").config("spark.some.config","some-config").master("local[*]").getOrCreate()
    val firstRdd= spark.read.load(".//resources//resources//users.parquet")
    firstRdd.select("name", "favorite_color").write.save("new.parquet")
  }
}