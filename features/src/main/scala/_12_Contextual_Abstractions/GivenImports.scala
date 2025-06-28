package _12_Contextual_Abstractions

import _12_Contextual_Abstractions.Instances.Show

object GivenImports

object Instances:

  trait Show[T]:
    def show(value: T): String

  given Show[Int] with
    def show(value: Int): String = s"Int($value)"

  given Show[String] with
    def show(value: String): String = s"String($value)"

object Usage:

  import Instances.given  // Імпортуємо всі given з Instances

  def printShow[T](value: T)(using s: Show[T]): String =
    s.show(value)
