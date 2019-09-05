package org.sparktest.scala.example

object MapTest {
  def main(args : Array[String]){
    val map1= Map((0,1),(1,2),(2,3))
    println(s"Sum Of Map Values ::: ${sumOfMapValues(map1) { x=>x.values.fold(0)(_+_) }}")
  }
  
  def sumOfMapValues(x : Map[Int,Int])(f : Map[Int,Int] =>Int) : Int ={
    println(x)
    f(x)
  }
}