package a_taste_of_scala3._06_Methods

import org.scalatest.funsuite.AnyFunSuite

class CurriedMethodTest extends AnyFunSuite:
  test("multiply returns product of two numbers") {
    assert(CurriedMethod.multiply(3)(4) == 12)
  }

  test("partial application of multiply") {
    val double = CurriedMethod.multiply(2) _
    assert(double(5) == 10)
  }