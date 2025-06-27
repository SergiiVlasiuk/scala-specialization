package _01_a_taste_of_scala3._06_Methods

import org.scalatest.funsuite.AnyFunSuite

class MethodWithVarArgsSpec extends AnyFunSuite:
  test("average computes mean value") {
    assert(MethodWithVarArgs.average(1.0, 2.0, 3.0) == 2.0)
  }