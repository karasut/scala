package org.sparktest.scala.example

import scala.annotation.tailrec

object FactorialByTailRecursion extends App{
  
  println(factorial(5))
  
  def factorial(n : Int) : Int = {
    
    @tailrec
    def factorialRec(n : Int ,  result : Int) : Int = {
      println(s"n::::::: $n")
      println(s"result ::::: $result")
      if(n==1) result
      else factorialRec(n-1 , n * result )
    }
    factorialRec(n,1)
  }
  
}