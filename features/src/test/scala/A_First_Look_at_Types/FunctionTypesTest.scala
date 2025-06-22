package A_First_Look_at_Types

import org.scalatest.funsuite.AnyFunSuite

class FunctionTypesTest extends AnyFunSuite:

  test("Typed function parameter and return") {
    def square(x: Int): Int = x * x
    assert(square(5) == 25)
  }

  test("Function as value") {
    val doubler: Int => Int = x => x * 2
    assert(doubler(3) == 6)
  }

  test("Function with multiple args") {
    val add: (Int, Int) => Int = (a, b) => a + b
    assert(add(2, 3) == 5)
  }

  test("Type aliases work") {
    type Name = String
    type Age = Int
    type Person = (Name, Age)

    val user: Person = ("Bob", 25)
    assert(user._1 == "Bob")
  }

  test("Union types in parameters") {
    def format(value: Int | String): String = value match
      case i: Int => s"int: $i"
      case s: String => s"string: $s"

    assert(format(10) == "int: 10")
    assert(format("abc") == "string: abc")
  }
