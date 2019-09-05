package org.sparktest.scala.example

import scala.util.control.Breaks._
import scala.util.control._

object Test1 {
  def main(args: Array[String]) {
    var charArr = Array('a', 'b', 'c', 'a', 'b')
    val len = charArr.length
    for (i <- 0 until len) {
      var nonRepeating = charArr(i)
      breakable {
        for (j <- i to 0) {
          if (charArr(i) == charArr(j)) {
            println(charArr(i))
            break
          } else {
            println("Hello")
          }

        }
      }
      nonRepeating = charArr(0)
      println("nonRepeating char  ::: " + nonRepeating)
    }
  }

}