package _12_Contextual_Abstractions

object ExtensionMethods:
  extension (x: Int)
    def squared: Int = x * x

  extension (s: String)
    def isUpperCase: Boolean = s == s.toUpperCase

  trait Show[T]:
    def show(value: T): String

  given Show[Int] with
    def show(value: Int): String = s"Int($value)"

  extension [T](value: T)
    def show(using Show[T]): String = summon[Show[T]].show(value)

