package a_taste_of_scala3._07_First_Class_Functions

import org.scalatest.funsuite.AnyFunSuite

class FirstClassFunctionsTest extends AnyFunSuite:

  test("addOne adds 1 to number") {
    assert(FirstClassFunctions.addOne(4) == 5)
  }

  test("sum adds two numbers") {
    assert(FirstClassFunctions.sum(3, 7) == 10)
  }

  test("applyFunc applies function to 10") {
    val doubleFunc: Int => Int = _ * 2
    assert(FirstClassFunctions.applyFunc(doubleFunc) == 20)
  }

  test("makeAdder creates adder function") {
    val addThree = FirstClassFunctions.makeAdder(3)
    assert(addThree(7) == 10)
  }

  test("map applies function to collection") {
    val numbers = List(1, 2, 3)
    val result = numbers.map(x => x * 3)
    assert(result == List(3, 6, 9))
  }
