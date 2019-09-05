package org.sparktest.example.sql

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.types.StructField
import org.apache.spark.sql.types.StringType
import org.apache.spark.sql.types.DoubleType

object DataFrame3 {
  def main(args: Array[String]) {
    val spark = SparkSession.builder().appName("DF3").master("local[*]").config("some.config.value", "config").getOrCreate()
    val productSchema = StructType(Array(StructField("pname", StringType, nullable = true), StructField("pId", StringType, nullable = true), StructField("price", DoubleType, nullable = true), StructField("minDis", DoubleType, nullable = true), StructField("maxDis", DoubleType, nullable = true)))
    val rivalProductSchema = StructType(Array(StructField("productName", StringType, nullable = true), StructField("productId", StringType, nullable = true), StructField("rivalprice", DoubleType, nullable = true), StructField("companyName", StringType, nullable = true)))
    val productRead = spark.read.option("header", "true").option("sep", "|").option("inferSchema", "true").schema(productSchema).csv(".//resources//product.csv")
    val rivalProdRead=spark.read.option("header", "true").option("sep", "|").option("inferSchema", "true").schema(rivalProductSchema).csv(".//resources/rivalProduct.csv")
    val joinedDF=productRead.join(rivalProdRead,productRead("pId")===rivalProdRead("productId"),"outer")
    val price=joinedDF.rdd.map{
      x =>
        if(x(2).toString().toDouble>x(7).toString().toDouble)
          (x(0),x(1),x(7),"rival product")
         else
           (x(0),x(1),x(2),"own product")
    }
    price.collect().foreach(println)
  }
}