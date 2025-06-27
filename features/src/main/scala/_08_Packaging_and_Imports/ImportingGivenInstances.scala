package _08_Packaging_and_Imports

object ImportingGivenInstances:
  given Ordering[Int] = Ordering.Int.reverse

  val nums = List(1, 3, 2)
  val sorted = nums.sorted

  def useExternalGiven(): Unit =
    import scala.concurrent.ExecutionContext.Implicits.global
    import scala.concurrent.Future
    val f = Future(println("Running in thread pool"))
