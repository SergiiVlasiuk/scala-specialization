package _07_Functions

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class AnonymousFunctionsTest extends AnyFunSuite with Matchers:

  test("add two numbers") {
    val add = (x: Int, y: Int) => x + y
    add(2, 3) shouldBe 5
  }

  test("double a number") {
    val double = (x: Int) => x * 2
    double(4) shouldBe 8
  }

  test("double list with underscore") {
    val doubleList: List[Int] => List[Int] = _.map(_ * 2)
    doubleList(List(1, 2, 3)) shouldBe List(2, 4, 6)
  }
