package _06_Methods

import org.scalatest.funsuite.AnyFunSuite

class CalculatorTest extends AnyFunSuite:
  val calc = new Calculator()

  test("add should sum two numbers") {
    assert(calc.add(3, 4) == 7)
  }

  test("multiply should multiply with default argument") {
    assert(calc.multiply(3) == 6)
    assert(calc.multiply(3, 3) == 9)
  }

  test("pi should return Pi constant") {
    assert(math.abs(calc.pi - 3.1415) < 0.001)
  }

  test("identity should return same value") {
    assert(calc.identity("hello") == "hello")
  }

  test("greet should use given context parameter") {
    given String = "Hi"
    assert(calc.greet("John") == "Hi, John!")
  }
