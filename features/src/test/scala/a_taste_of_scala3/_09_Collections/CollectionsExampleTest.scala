package a_taste_of_scala3._09_Collections

import org.scalatest.funsuite.AnyFunSuite

class CollectionsExampleTest extends AnyFunSuite:

  test("listExample returns doubled evens") {
    val result = CollectionsExample.listExample()
    assert(result == List(2, 4, 6, 8, 10))
  }

  test("tupleExample returns tuple") {
    val tuple = CollectionsExample.tupleExample()
    assert(tuple._1 == 42)
    assert(tuple._2 == "Scala")
    assert(tuple._3)
  }

  test("divideWithRemainder works correctly") {
    val (q, r) = CollectionsExample.divideWithRemainder(10, 3)
    assert(q == 3)
    assert(r == 1)
  }
