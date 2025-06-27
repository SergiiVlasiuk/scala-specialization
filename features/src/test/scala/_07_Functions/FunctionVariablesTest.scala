package _07_Functions

import org.scalatest.funsuite.AnyFunSuite

class FunctionVariablesTest extends AnyFunSuite:

  test("assigning function to a variable") {
    val square = (x: Int) => x * x
    assert(square(4) == 16)
  }

  test("using function variable in higher-order function") {
    val isEven = (x: Int) => x % 2 == 0
    val result = List(1, 2, 3, 4).filter(isEven)
    assert(result == List(2, 4))
  }

  test("eta expansion from method to function") {
    def add(x: Int, y: Int): Int = x + y
    val addFn: (Int, Int) => Int = add
    assert(addFn(2, 3) == 5)
  }
