package com.glassbeam.fileprocessor

import java.io.FileNotFoundException
import java.io.File

object FileRead {
  def main(args: Array[String]): Unit = {
    val fileList = getListOfFiles(".\\resources\\channel1")
    fileList.foreach(println(_))
    val details = readFile(".\\resources\\channel1")
  }
  def readFile(filePath: String): Array[String] = {
    val fileList = getListOfFiles(filePath)
    var words: Array[String] = Array[String]()

    fileList.foreach { x =>
      try {
        var fileData = scala.io.Source.fromFile(x)
        words = try fileData.mkString.split(" ") finally fileData.close()
      } catch {
        case fnfe: FileNotFoundException => fnfe.getCause // TODO: handle error
        case e: Exception                => e.getCause
      }
    }
    words
  }
  def getListOfFiles(dir: String): List[File] = {
    val d = new File(dir)
    if (d.exists && d.isDirectory) {
      d.listFiles.filter(_.isFile).toList
    } else {
      List[File]()
    }
  }
}