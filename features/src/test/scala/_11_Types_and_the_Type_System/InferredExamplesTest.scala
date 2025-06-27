package _11_Types_and_the_Type_System

// ----------------------------------------
// Scala 3 — Tests for InferredExamples
// ----------------------------------------

import org.scalatest.funsuite.AnyFunSuite

class InferredExamplesTest extends AnyFunSuite:

  test("Inferred types for simple values") {
    assert(InferredExamples.anInt == 42)
    assert(InferredExamples.aString == "Scala")
    assert(InferredExamples.aBoolean)
    assert(InferredExamples.aList == List(1, 2, 3))
  }

  test("Inferred type for add function") {
    assert(InferredExamples.add(2, 3) == 5)
  }

  test("Inferred type for greet function") {
    assert(InferredExamples.greet("Serhii") == "Hello, Serhii")
  }

  test("Mixed list inferred as List[Any]") {
    assert(InferredExamples.mixedList.contains(2.0))
    assert(InferredExamples.mixedList.contains("three"))
  }
