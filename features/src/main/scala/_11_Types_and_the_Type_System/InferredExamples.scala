package _11_Types_and_the_Type_System

// ----------------------------------------
// Scala 3 — Inferred Types Examples
// ----------------------------------------

object InferredExamples:

  // Прості типи
  val anInt     = 42                // Int
  val aString   = "Scala"           // String
  val aBoolean  = true              // Boolean
  val aList     = List(1, 2, 3)     // List[Int]

  // Функція з виведеним типом: (Int, Int) => Int
  def add(a: Int, b: Int) = a + b

  // Функція з виведеним типом: String => String
  def greet(name: String) = s"Hello, $name"

  // Потенційно ризикований випадок
  def doSomething() = {
    println("Working...")
    123 // ця функція має тип Unit!
  }

  // Функція з колекцією Any через змішані типи
  val mixedList = List(1, 2.0, "three") // List[Any]
