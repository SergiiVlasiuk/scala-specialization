package _07_Functions

object HigherOrderFunctionsExample:

  // Метод, який приймає функцію Int => String
  def describeNumber(x: Int, f: Int => String): String = f(x)

  // Метод, який приймає дві функції
  def composeFunctions[A, B, C](a: A, f: A => B, g: B => C): C =
    g(f(a))

  // Фільтр власної реалізації
  def myFilter[A](list: List[A], predicate: A => Boolean): List[A] =
    list.foldRight(List.empty[A]) { (elem, acc) =>
      if predicate(elem) then elem :: acc
      else acc
    }

