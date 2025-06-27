package _09_Scala_Collections.methods

import _09_Scala_Collections.methods._
import org.scalatest.funsuite.AnyFunSuite

class CollectionsMethodsTest extends AnyFunSuite:

  test("map examples"):
    assert(MapExamples.squares == List(1, 4, 9, 16, 25))
    assert(MapExamples.lengths == List(5, 6, 6, 4))

  test("filter examples"):
    assert(FilterExamples.evens == List(2, 4))
    assert(FilterExamples.longWords == List("banana", "cherry"))

  test("head and tail"):
    assert(HeadTailExamples.first == 1)
    assert(HeadTailExamples.rest == List(2, 3, 4, 5))

  test("take, takeRight, takeWhile"):
    assert(TakeExamples.firstTwo == List(1, 2))
    assert(TakeExamples.lastTwo == List(4, 5))
    assert(TakeExamples.takenWhile == List(1, 2, 3))

  test("drop, dropRight, dropWhile"):
    assert(DropExamples.dropTwo == List(3, 4, 5))
    assert(DropExamples.dropRightTwo == List(1, 2, 3))
    assert(DropExamples.dropWhileLessThan3 == List(3, 4, 5))

  test("reduce examples"):
    assert(ReduceExamples.sum == 15)
    assert(ReduceExamples.max == 5)

  test("more examples"):
    assert(MoreExamples.reversed == List("date", "cherry", "banana", "apple"))
    assert(MoreExamples.joined == "apple, banana, cherry, date")
    assert(MoreExamples.distinctNums == List(1, 2, 3))
