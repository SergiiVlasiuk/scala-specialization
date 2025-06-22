package a_taste_of_scala3._06_Methods

import org.scalatest.funsuite.AnyFunSuite

class MethodWithoutParensSpec extends AnyFunSuite:
  test("method without parens returns constant") {
    assert(MethodWithoutParens.meaningOfLife == 42)
  }
