package _09_Scala_Collections.types

import _09_Scala_Collections.types.*
import org.scalatest.funsuite.AnyFunSuite

import scala.collection.mutable.ArrayBuffer

class CollectionTypesTest extends AnyFunSuite:

  test("List creation and prepend"):
    assert(ListExamples.empty.isEmpty)
    assert(ListExamples.withElements == List(1, 2, 3))
    assert(ListExamples.prepended == List(0, 1, 2, 3))

  test("Vector updated"):
    val result = VectorExamples.updated
    assert(result == Vector(1, 99, 3))

  test("ArrayBuffer operations"):
    val ab = ArrayBufferExamples.ab
    assert(ab.contains(3))
    assert(!ab.contains(2))
    assert(ab(0) == 100)

  test("Map access and mutation"):
    assert(MapExamples.paris == "Paris")
    assert(MapExamples.updated.contains("Spain"))
    assert(!MapExamples.removed.contains("Germany"))

  test("Set add and remove"):
    assert(SetExamples.added.contains(4))
    assert(!SetExamples.removed.contains(2))

  test("Range inclusive and exclusive"):
    assert(RangeExamples.inclusive == List(1, 2, 3, 4, 5))
    assert(RangeExamples.exclusive == List(1, 2, 3, 4))
    assert(RangeExamples.stepped == List(1, 3, 5, 7, 9))
    assert(RangeExamples.reversed == List(5, 4, 3, 2, 1))
