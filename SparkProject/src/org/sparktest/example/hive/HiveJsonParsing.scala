package org.sparktest.example.hive

import org.apache.spark.sql.SparkSession
import java.io.File

object HiveJsonParsing {
  def main(args: Array[String]) {
    val warehouseLocation = new File("spark-warehouse").getAbsolutePath
    val spark = SparkSession.builder().appName("HiveJsonParsing").master("local[*]").config("spark.sql.warehouse.dir", warehouseLocation).enableHiveSupport().getOrCreate()
    spark.sql("create database IF NOT EXISTS NONSTRUCTUREDB")

    spark.sql("create table if not exists JSON_TABLE(value string)")
    spark.sql("create table if not exists JSON_TABLE_TMP(value string)").collect()
    spark.sql("load data local inpath './/resources//employee.json' into table JSON_TABLE")
    spark.sql("select count(*) from JSON_TABLE").show()
    //spark.sql("select get_json_object(value,'$.employees.eName') as empname, get_json_object(value,'$.employees.sal') as salary from NONSTRUCTUREDB.JSON_TABLE").show()
    spark.sql("insert into table JSON_TABLE_TMP select get_json_object(value,'$.employees') as empname from JSON_TABLE")
    spark.sql("select * from JSON_TABLE_TMP").show()
    spark.sql("select get_json_object(value,'$.sal') as sal from JSON_TABLE_TMP").show()
    spark.sql("drop table JSON_TABLE_TMP")
    spark.sql("drop table JSON_TABLE")
  }
}