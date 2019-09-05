

object ProfitMaximization {
  def main(args: Array[String]) {
    val length = scala.io.StdIn.readInt()
    val profit = Array.ofDim[Int](length)
    var profit1 = Array.ofDim[Int](length)
    val finalProfit = Array.ofDim[Int](length)
    var result =0
    if (length < scala.math.pow(10, 3)) {
      profit1=scala.io.StdIn.readLine().split(" ").map(_.toInt)
      for (i <- 0 until length) {
        profit(i) = profit1(i)
        finalProfit(i) = profit(i)
      }
      for (i <- 0 until length) {
        
        for (j <- 0 until length if j < i) {
          if (profit(i) % profit(j) == 0) {
            finalProfit(i) = scala.math.max(finalProfit(i), finalProfit(j) + profit(i))
          }

        }
        
        for(i <- 0 until length){
          result=scala.math.max(finalProfit(i), result)
        }
       
      }
       println(result)
    }
    
  }
}