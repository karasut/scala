package org.sparktest.example

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object SparkJsonRead {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("HiveJsonParsing").master("local[*]").getOrCreate()
    import spark.implicits._
    val jsonRead=spark.read.json(".//resources//employee.json")
    jsonRead.createOrReplaceTempView("emp")
    val table=spark.sql("select employees from emp")
    table.printSchema()
    table.withColumn("employees", explode($"employees")).select("employees.*").groupBy("eName").sum("sal").alias("sal").show()
  }
}