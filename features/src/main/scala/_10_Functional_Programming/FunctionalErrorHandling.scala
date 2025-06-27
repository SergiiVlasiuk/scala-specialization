package _10_Functional_Programming

def safeDivide(a: Int, b: Int): Either[String, Int] =
  if b == 0 then Left("Division by zero") else Right(a / b)

def demonstrateSafeDivide(): Unit =
  val result = safeDivide(10, 2).map(_ * 2) // Right(20)

  val message = result match
    case Right(value) => s"Success: $value"
    case Left(error)  => s"Error: $error"

  val folded = result.fold(
    err => s"Failed: $err",
    value => s"Got: $value"
  )

  println(message)
  println(folded)

import scala.util.{Try, Success, Failure}

def risky(s: String): Try[Int] = Try(s.toInt)

def processInput(input: String): String =
  risky(input) match
    case Success(v) => s"Parsed: $v"
    case Failure(e) => s"Error: ${e.getMessage}"

def fallbackValue(input: String): Int =
  risky(input).getOrElse(0)
