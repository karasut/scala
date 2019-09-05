package com.exception

object ExceptionTest {
  def getException() : List[String] = {
    val a=1
    if(a==1){
        val list=List("a","b","c")
        //println(list)
        list
        }
      
      else{
      throw new IllegalStateException("Exception thrown")
      }
  }
  def main(args: Array[String]): Unit = {
    try{
      println(getException())
    }catch {
      case e : Exception => println(e.printStackTrace())
    }
  }
}