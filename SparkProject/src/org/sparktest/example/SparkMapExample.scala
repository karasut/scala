package org.sparktest.example

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object SparkMapExample {
  def main(args : Array[String]){
  val sparkconf=new SparkConf().setAppName("MapExample").setMaster("local[*]")
  val sc= new SparkContext(sparkconf)
  val lines=sc.textFile("C:\\Users\\karasut_old\\CARE.sql")
//  val mapFile=lines.map(line => (line,line.length()))
  val flatmap= lines.flatMap(line =>(line.split(" "))).filter(value => value=="SR_STATUS")
 // mapFile.foreach(println)
  println("""""""""""""""""""""""""""""""""""""""""""""")
  flatmap.foreach(println)
  val list=List(2,3,4,6,9,7)
  val num : Int =0
  val listrdd=sc.parallelize(list)
  val evenrdd=listrdd.map(listrdd => listrdd + 1).filter(i => i%2==0)
  val evenrdd1=listrdd.flatMap( x => List(x)).filter(i => i%2==0)
  evenrdd.foreach(println)
  evenrdd1.foreach(println)
  }  
}