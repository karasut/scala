package org.sparktest.scala.example

import scala.annotation.tailrec

object TailRecursionExample {
  def main(args : Array[String]){
    val list = List(1,2,3,4,5)
    println(sum(list))
  }
  
  def sum( list: List[Int]) : Int  = sumWithAccumulator(list, 0)
  
  @tailrec
  private def sumWithAccumulator(a : List[Int], accumulator: Int) : Int = a match {
    case Nil => accumulator
    case x :: xs =>sumWithAccumulator(xs ,accumulator + x )
  }
}