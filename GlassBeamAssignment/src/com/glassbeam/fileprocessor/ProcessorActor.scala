package com.glassbeam.fileprocessor

import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.Props
import akka.actor.ActorSystem
import akka.util.Timeout
import scala.concurrent.duration._
import akka.pattern.ask
import akka.dispatch.ExecutionContexts._
import scala.util.Success
import scala.util.control.Breaks._

case class StartProcessFileMsg()
case class StringProcessedMsg(words: Array[String])
case class StartFileRead(filePath: String)
case class MergeChannel(ch1Words: Array[String], ch2Words: Array[String])

object Main {
  implicit val ec = global
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("System")
    val actor = system.actorOf(Props(new ProcessorActor))
    implicit val timeout = Timeout(5 seconds)
    val future = actor ? StartProcessFileMsg()
    future.map { result =>
      system.terminate()
    }
  }
}

class ProcessorActor extends Actor {
  private var fileSender: Option[ActorRef] = None
  implicit val ec = global
  implicit val timeout = Timeout(1 millisecond)
  def receive = {
    case StartProcessFileMsg() => {
      fileSender = Some(sender)
      val ch1 = context.actorOf(Props[SubProcessChannel1Actor]) ? StartFileRead(".\\resources\\channel1")
      val ch2 = context.actorOf(Props[SubProcessChannel2Actor]) ? StartFileRead(".\\resources\\channel2")
      ch1.onComplete {
        case Success(StringProcessedMsg(ch1words)) => {
          ch2.onComplete {
            case Success(StringProcessedMsg(ch2words)) => {
              context.actorOf(Props[MergeOutputActor]) ! MergeChannel(ch1words, ch2words)
            }
          }

        }
      }
    }

    case StringProcessedMsg(words) => {
      for (i <- 0 until words.length) {
        println(words(i))
      }
      fileSender.map(_ ! words)
    }
  }
}

class SubProcessChannel1Actor extends Actor {
  def receive = {
    case StartFileRead(filePath: String) => {
      val words = FileRead.readFile(filePath)
      sender ! StringProcessedMsg(words)
    }
    case _ => println("Invalid Message in SubProcessChannel1Actor")
  }
}
class SubProcessChannel2Actor extends Actor {
  def receive = {
    case StartFileRead(filePath: String) => {
      val words = FileRead.readFile(filePath)
      sender ! StringProcessedMsg(words)
    }
    case _ => println("Invalid Message in SubProcessChannel2Actor")
  }
}
class MergeOutputActor extends Actor {
  def receive = {
    case MergeChannel(ch1Words: Array[String], ch2Words: Array[String]) => {
      var checkArray: Array[String] = ch2Words
      for (i <- 0 until ch1Words.length) {
        breakable {
          for (j <- 0 until checkArray.length) {
            if (checkArray(j).substring(0, 1) == ch1Words(i).substring(0, 1)) {
              println((ch1Words(i), checkArray(j)))
              checkArray = checkArray.filter(!_.contains(checkArray(j)))
              break()
            }
          }
        }
      }
    }
    case _ => println("Invalid Message in MergeOutputActor")
  }
}