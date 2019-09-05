package org.sparktest.example
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
object SparkReadFile {
  def main(args : Array[String]){
    val conf= new SparkConf().setMaster("spark://10.16.2.13:6666").setAppName("Test");
    val sc = new SparkContext(conf)
		  // Load our input data.
		  val input = sc.textFile("./data/CHANGES.txt")
		  // Split it up into words.
		  val words = input.flatMap(line => line.split(" "))
		  // Transform into pairs and count.
		  val counts = words.map(word => (word, 1)).reduceByKey{case (x, y) => x + y}
	  // Save the word count back out to a text file, causing evaluation.
	  counts.saveAsTextFile("./data/wordCounts_output1.txt")
  }
}