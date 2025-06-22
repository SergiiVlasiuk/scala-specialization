package a_taste_of_scala3._06_Methods

import org.scalatest.funsuite.AnyFunSuite
import a_taste_of_scala3._06_Methods.ExtensionVsEta.*

class ExtensionVsEtaSpec extends AnyFunSuite:
  test("extension method repeats action") {
    var count = 0
    3.times { count += 1 }
    assert(count == 3)
  }

  test("extension method repeats action (infix format)") {
    var count = 0
    3 times (count += 1)
    assert(count == 3)
  }

  test("eta expansion lets method be treated as function") {
    assert(greetFn.isInstanceOf[() => Unit])
  }
