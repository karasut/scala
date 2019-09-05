package org.sparktest.scala.example

trait A{
  def a(){
    println("aaaaaaaaaaaaa")
  }
}

trait B extends A{
  def b(){
    println("bbbbbbbbbbbbbb")
  }
}

class c extends A{
  def c(){
    println("cccccccccccc")
  }
}

class d extends A with B{
  
}

case class e(a : Int)


object TraitTest {
  
}