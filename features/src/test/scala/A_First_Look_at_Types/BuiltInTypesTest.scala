package A_First_Look_at_Types

import org.scalatest.funsuite.AnyFunSuite

class BuiltInTypesTest extends AnyFunSuite:

  test("Basic types hold correct values") {
    val age: Int = 30
    val pi: Double = 3.14
    val isScalaFun: Boolean = true

    assert(age == 30)
    assert(pi == 3.14)
    assert(isScalaFun)
  }

  test("Char and String types") {
    val letter: Char = 'A'
    val name: String = "Alice"

    assert(letter == 'A')
    assert(name.startsWith("A"))
  }

  test("Unit return type") {
    def log(msg: String): Unit = println(msg)
    val result = log("Hello")
    assert(result == ()) // Unit — це значення ()
  }
