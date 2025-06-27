package _01_a_taste_of_scala3._06_Methods

import org.scalatest.funsuite.AnyFunSuite

class MethodWithDefaultParamsTest extends AnyFunSuite:
  test("greet uses default parameter") {
    assert(MethodWithDefaultParams.greet() == "Hello, friend!")
  }

  test("greet with explicit name") {
    assert(MethodWithDefaultParams.greet("Serhii") == "Hello, Serhii!")
  }
