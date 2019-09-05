package org.sparktest.example

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.Dataset
import org.apache.spark.sql.Row
import org.apache.spark.rdd.RDD
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object StudentResultAnalysis {
  def main(args: Array[String]) {
    val path = ".//resources//StudentResult.csv"
    rddRead(path).collect().map(x => (x(0), x(1), x(2), x(3), x(4), x(5), x(6), x(7))).foreach(println)
  }

  def rddRead(path: String): RDD[Array[String]] = {
    val sparkConf = new SparkConf().setAppName("StudentResultAnalysis").setMaster("local[*]")
    val sc = new SparkContext(sparkConf)
    val fileRead = sc.textFile(path, 6)
    val header= fileRead.first()
    println(header)
    fileRead.distinct().filter(x=> (x!=header)).map(x => (x.split(","))).filter(x => !x.contains(""))
  }

  def dataframeRead(path: String): Dataset[Row] = {
    val spark = SparkSession.builder().appName("StudentResultAnalysis").master("local[*]")
      .config("spark.some.value.config", "someconfig").getOrCreate()
    import spark.implicits._
    val fileRead = spark.read.option("inferSchema", "true").option("header", "true").csv(path)
    val filteredRecord = fileRead.filter(fileRead.col("name").isNotNull).distinct()
    filteredRecord
  }
}