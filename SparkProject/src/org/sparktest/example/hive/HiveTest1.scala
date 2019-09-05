package org.sparktest.example.hive

import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object HiveTest1 extends App{
  
  def hiveContextCreation() : HiveContext = {
    System.setProperty("hadoop.home.dir","C:\\hadoop" );
    val sparkConf= new SparkConf().setAppName("HiveTest1").setMaster("local[*]")
    val sc = new SparkContext(sparkConf)
    new HiveContext(sc)    
  }
  
  val hiveContext= hiveContextCreation()
  hiveContext.sql("create table if not exists employee(id int, name string, deptno int) row format delimited fields terminated by ','")
  hiveContext.sql("load data local inpath './/resources//employee.csv' into table employee")
  var result = hiveContext.sql("select * from employee")
  result.show()
}