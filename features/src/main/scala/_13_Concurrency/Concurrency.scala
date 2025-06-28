package _13_Concurrency

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object Concurrency:

  def slowComputation(x: Int): Future[Int] = Future {
    Thread.sleep(100)
    x * 2
  }

  def singleThreadComputation(x: Int): Int = {
    Thread.sleep(100)
    x * 2
  }
