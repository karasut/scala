package com.string

object CDRManupulation extends App{
  val str="9739900002,,SID MO SMS,OnNet,Test2,2018-10-17T09:45:56,9739900002,1451602772,33939,20.34,,,GHS,CDR1.txt,ProcessBenchGenerator1,,,Sms,200000032,Subscriber Not Found,1"
  val cdrArray=str.split(',')(19) 
  println(s"$cdrArray")
  val validCdr=str.substring(0, str.indexOf(cdrArray)-1)
  println(s"$validCdr")
}