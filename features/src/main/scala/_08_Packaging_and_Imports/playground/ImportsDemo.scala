package _08_Packaging_and_Imports.playground

object ImportsDemo:
  import scala.collection.mutable.{Map, ArrayBuffer}
  import java.time.LocalDate as Date
  import scala.util.{Try, Success, Failure as F}

  def demo(): Unit =
    val buffer = ArrayBuffer(1, 2, 3)
    val map = Map("x" -> 1)
    val today = Date.now()
    val result = Try("123".toInt)

    result match
      case Success(value) => println(s"Parsed: $value")
      case F(ex) => println(s"Failed: ${ex.getMessage}")
