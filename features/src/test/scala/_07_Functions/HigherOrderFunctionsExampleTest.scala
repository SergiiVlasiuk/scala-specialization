package _07_Functions

import org.scalatest.funsuite.AnyFunSuite

class HigherOrderFunctionsExampleTest extends AnyFunSuite:

  import HigherOrderFunctionsExample._

  test("describeNumber with a function to create a message") {
    val result = describeNumber(10, n => s"Number is $n")
    assert(result == "Number is 10")
  }

  test("composeFunctions applies two functions in order") {
    val f: Int => Double = _.toDouble * 2
    val g: Double => String = d => s"Value: $d"
    val result = composeFunctions(5, f, g)
    assert(result == "Value: 10.0")
  }

  test("myFilter filters list according to predicate") {
    val list = List(1, 2, 3, 4, 5)
    val filtered = myFilter(list, _ % 2 == 0)
    assert(filtered == List(2, 4))
  }
