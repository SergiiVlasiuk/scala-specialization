package _05_Domain_Modeling


import org.scalatest.funsuite.AnyFunSuite

class ShapeUtilsTest extends AnyFunSuite:

  test("area of circle") {
    val shape = Circle(2)
    assert(math.abs(ShapeUtils.area(shape) - 12.566) < 0.01)
  }

  test("area of rectangle") {
    val shape = Rectangle(3, 4)
    assert(ShapeUtils.area(shape) == 12.0)
  }
