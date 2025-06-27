package _01_a_taste_of_scala3._09_Collections

import org.scalatest.funsuite.AnyFunSuite

class FoldExamplesTest extends AnyFunSuite:

  test("sumLeft sums elements left fold") {
    assert(FoldExamples.sumLeft(List(1, 2, 3, 4)) == 10)
  }

  test("sumRight sums elements right fold") {
    assert(FoldExamples.sumRight(List(1, 2, 3, 4)) == 10)
  }

  test("concatLeft concatenates strings with foldLeft") {
    assert(FoldExamples.concatLeft(List("a", "b", "c")) == "abc")
  }

  test("concatRight concatenates strings with foldRight") {
    assert(FoldExamples.concatRight(List("a", "b", "c")) == "abc")
  }

  test("diffLeft subtracts with foldLeft") {
    assert(FoldExamples.diffLeft(List(1, 2, 3, 4)) == -10)
  }

  test("diffRight subtracts with foldRight") {
    assert(FoldExamples.diffRight(List(1, 2, 3, 4)) == -2)
  }
