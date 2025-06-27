package _01_a_taste_of_scala3._06_Methods

import org.scalatest.funsuite.AnyFunSuite
import _01_a_taste_of_scala3._06_Methods.EtaExpansion.*

class EtaExpansionSpec extends AnyFunSuite:
  test("eta expansion allows treating method as function") {
    assert(addFunction(3, 4) == 7)
  }
