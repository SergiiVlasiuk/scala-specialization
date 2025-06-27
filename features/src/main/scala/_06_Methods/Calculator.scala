package _06_Methods

class Calculator:

  // Простий метод з параметрами
  def add(x: Int, y: Int): Int = x + y

  // Метод з параметром за замовчуванням
  def multiply(x: Int, y: Int = 2): Int = x * y

  // Безаргументний метод без дужок (getter-стиль)
  def pi: Double = math.Pi

  // Поліморфний метод
  def identity[T](x: T): T = x

  // Метод із контекстним параметром
  def greet(name: String)(using greeting: String): String =
    s"$greeting, $name!"
