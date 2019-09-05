package org.sparktest.example

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.storage.StorageLevel

object SparkPersistanceExample {
  def main(args : Array[String]){
    val sparkconf=new SparkConf().setAppName("PersitEx").setMaster("local[*]");
    val spartctx=new SparkContext(sparkconf);
    val lines=spartctx.textFile("C:\\Users\\karasut_old\\CARE.sql");
    lines.persist(StorageLevel.DISK_ONLY)
    lines.count();
    Thread.sleep(20000)
  }
}