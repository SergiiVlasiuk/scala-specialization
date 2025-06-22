package a_taste_of_scala3._06_Methods

import org.scalatest.funsuite.AnyFunSuite
import a_taste_of_scala3._06_Methods.InfixMethod.*

class InfixMethodSpec extends AnyFunSuite:
  test("infix method syntax works") {
    assert("Serhii" likes "coffee")
    assert(!("Ivan" likes "tea"))
  }
