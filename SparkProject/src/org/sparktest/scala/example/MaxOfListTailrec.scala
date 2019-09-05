package org.sparktest.scala.example

import scala.annotation.tailrec

object MaxOfListTailrec extends App{
  val list = List.range(1, 100000)
  println(max(list))
  
  def max(list : List[Int]) : Int ={
    
    @tailrec
    def maxList(subList : List[Int], res : Int) : Int ={
     
      subList match{
        case Nil => res
        case x :: tail => 
          val maximum =if (x > res) x else res
          maxList(tail, maximum)
      }
    }
    maxList(list, 0)
  }
  
}