package _01_a_taste_of_scala3._11_Toplevel_Definitions

import org.scalatest.funsuite.AnyFunSuite

class TopLevelDefinitionsTest extends AnyFunSuite:

  test("Top-level greet function works") {
    assert(greet("Scala") == "Hello, Scala!")
  }

  test("Top-level val is accessible") {
    assert(appName == "ToplevelDemo")
  }

  test("Top-level class User works") {
    val u = User("Ada")
    assert(u.greet() == "Hello, Ada!")
  }
