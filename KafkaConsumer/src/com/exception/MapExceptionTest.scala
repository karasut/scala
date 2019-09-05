package com.exception

object MapExceptionTest {
  def main(args: Array[String]): Unit = {
    
    val  a= List("a","b","c","d","e","f")
    a map { word =>
      try{
        calledMethod(word)
        calledBMethod()
      }catch{
        case e : Exception => println(e)
      }
      
    }
    
    
  }
  
  def calledMethod(a: String) : Int ={
    println(a)
    if(a == "d"){
        println(s"$a need to be handled")
        throw new Exception(s"$a need to be handled")
    }
    1
  }
  def calledBMethod() : Unit ={
    println("hello world")
  }
}