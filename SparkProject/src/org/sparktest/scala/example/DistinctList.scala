package org.sparktest.scala.example

object DistinctList {
  def main(args : Array[String]){
    val listA=List("aaa","bbb","ccc","dddd")
    val listB=List("aaa","ccc")
    val listC="ppp" :: listA.diff(listB) ::: listB.diff(listA) 
    println(listA.lift(2))
    println(listA(2))
    println(listC ::: List("ddd"))
    println(List("one","two","three","four").toSet & List("one","two","four").toSet)
    println (listA.contains("aaa"))
    val listOfChar= List('a','c','e','b','u','i','e','d')
    val distinctlistOfChar=listOfChar.distinct
    println("Distnct List Of Char :::"+distinctlistOfChar)
    val vowelList=for(char<-distinctlistOfChar if(char=='a' || char=='e' || char=='i' || char=='o' || char == 'u'))yield char
    println("for loop vowel list :::"+vowelList)
    var chlist : List[Char] = Nil
    distinctlistOfChar.foreach{char =>
      if(char=='a' || char=='e' || char=='i' || char=='o' || char == 'u'){
        chlist= chlist ::: List(char)
      }
      chlist
     }
    println("foreach loop vowel list :::" + chlist)
     val mul = List.tabulate( 4,5 )( (x,y)=>y * y )      
      println( "mul : " + mul  )
  }
}