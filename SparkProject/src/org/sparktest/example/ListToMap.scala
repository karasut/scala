package org.sparktest.example

object ListToMap {
  def main(args : Array[String]){
    val list1 = List(1,2,3)
    val list2 = List("a","b","c")
    val mapConverted = list2.map(x=>(x,1))
    println(mapConverted.toMap)
    
    val list3=List("a","b","c","d","e")
    var a=0
    for(ele <- list3){
      if(ele.equals("a") || ele.equals("e") || ele.equals("i") || ele.equals("o") || ele.equals("u")){
        a=a+1
      }
    }
    println(s"vowel count ::: $a")
   //listToString(list3)
  }
  
  def listToString(l : List[String]) {
     l  match {
      case n :: rest => println(s"hello $n") 
                        listToString(rest)
      case Nil => println("In Nil")
    }
  }
}