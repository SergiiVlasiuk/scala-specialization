package _01_a_taste_of_scala3._07_First_Class_Functions

object FirstClassFunctions:

  // Функція, що додає 1
  val addOne: Int => Int = x => x + 1

  // Функція, що сумує два числа
  val sum: (Int, Int) => Int = (a, b) => a + b

  // Функція, що приймає іншу функцію і застосовує її до 10
  def applyFunc(f: Int => Int): Int = f(10)

  // Функція, що повертає функцію додавання певного числа
  def makeAdder(n: Int): Int => Int = x => x + n
