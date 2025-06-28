package _12_Contextual_Abstractions

object Scala2Compat:

  // greet із контекстним параметром
  def greet(name: String)(using prefix: String): String =
    s"$prefix $name"

  // Type class
  trait Show[T]:
    def show(value: T): String

  object Show:
    given Show[Int] with
      def show(value: Int): String = value.toString

  // Метод, який використовує Show
  def printValue[T](value: T)(using s: Show[T]): String =
    s.show(value)
