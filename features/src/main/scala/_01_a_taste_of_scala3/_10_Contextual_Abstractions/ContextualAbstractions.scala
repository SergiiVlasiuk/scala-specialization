package _01_a_taste_of_scala3._10_Contextual_Abstractions

trait Show[T]:
  def show(value: T): String

given Show[Int] with
  def show(value: Int): String = s"Int: $value"

given Show[String] with
  def show(value: String): String = s"String: $value"

def printValue[T](value: T)(using s: Show[T]): Unit =
  println(s.show(value))
