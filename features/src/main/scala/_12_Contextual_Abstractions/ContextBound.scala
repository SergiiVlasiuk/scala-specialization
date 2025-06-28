package _12_Contextual_Abstractions

object ContextBound:

  trait Show[T]:
    def show(value: T): String

  given Show[Int] with
    def show(value: Int): String = s"Int($value)"

  def printValue[T: Show](value: T): String =
    summon[Show[T]].show(value)

  def compare[A: Ordering](a: A, b: A): Int =
    summon[Ordering[A]].compare(a, b)

