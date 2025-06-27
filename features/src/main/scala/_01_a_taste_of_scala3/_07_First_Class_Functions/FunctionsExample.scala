package _01_a_taste_of_scala3._07_First_Class_Functions

object FunctionsExample {

  // Оголошення функції з параметрами та поверненням Int
  def add(x: Int, y: Int): Int = {
    x + y
  }

  // Функція без явного return, last expression - результат
  def multiply(x: Int, y: Int): Int = x * y

  // Функція без параметрів, повертає випадкове число
  def randomInt(): Int = scala.util.Random.nextInt(100)

  // Лямбда-функція (функція як значення)
  val subtract: (Int, Int) => Int = (a, b) => a - b

  // Вищого порядку функція: приймає функцію і застосовує її
  def applyFunction(f: Int => Int, v: Int): Int = f(v)

  def main(args: Array[String]): Unit = {
    println(add(2, 3))               // 5
    println(multiply(4, 5))          // 20
    println(randomInt())             // випадкове число
    println(subtract(10, 7))         // 3
    println(applyFunction(x => x * 2, 5))  // 10
  }
}
