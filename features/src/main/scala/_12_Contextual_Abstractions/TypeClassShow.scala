package _12_Contextual_Abstractions

trait TypeClassShow[T]:
  def show(value: T): String

object TypeClassShow:
  def apply[T](using s: TypeClassShow[T]): TypeClassShow[T] = s

given TypeClassShow[Int] with
  def show(value: Int): String = s"Int($value)"

given TypeClassShow[String] with
  def show(value: String): String = s"$value"

def printValue[T](value: T)(using s: TypeClassShow[T]): String =
  s.show(value)
