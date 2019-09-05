package org.sparktest.example

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.types.StructField
import org.apache.spark.sql.types.IntegerType
import org.apache.spark.sql.types.StringType

object DFSchemaTest1 {
  def main(args : Array[String]){
   val spark=SparkSession.builder().appName("DFSchemaTest1").config("spaek.some.config.name","some-config").master("local[*]").getOrCreate()
   import spark.implicits._
   val df=spark.read.json(".\\resources\\resources\\people.json")
   df.show()
   df.printSchema()
   val ageBelow30= df.filter($"age" <= 19)
   ageBelow30.show()
   df.groupBy("name").count.show
   df.createOrReplaceTempView("people")
   spark.sql("select * from people where name='Michael'").show()
   
   df.createOrReplaceGlobalTempView("people")
   spark.sql("select * from global_temp.people").show()
   
   val sparkNew=SparkSession.builder().appName("SchemaTest").config("spark.some.config.name","some-config").master("local[*]").getOrCreate()
   val schemaConfig=StructType(Array(StructField("Id",IntegerType,true),StructField("desc",StringType,true)))
   val lookupDf=sparkNew.read.option("header", "true").option("inferSchema", "true").csv(".\\resources\\lookUp.csv")
   lookupDf.show
  }
}