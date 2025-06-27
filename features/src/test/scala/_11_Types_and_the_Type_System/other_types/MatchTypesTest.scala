package _11_Types_and_the_Type_System.other_types

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import _11_Types_and_the_Type_System.other_types.MatchTypes.*

class MatchTypesTest extends AnyFlatSpec with Matchers {

  "Element match type" should "resolve Char for String" in {
    val e: Element[String] = 'a'
    e shouldBe a [Char]
  }

  it should "resolve element type for Array" in {
    val e: Element[Array[Int]] = 42
    e shouldBe a [Int]
  }

  it should "resolve element type for Iterable" in {
    val e: Element[List[Double]] = 3.14
    e shouldBe a [Double]
  }

  it should "fallback to identity for other types" in {
    val e: Element[Boolean] = true
    e shouldBe a [Boolean]
  }

  "getElementType" should "return Char description of element type" in {
    getElementType("hello") should include("Char")
  }
  it should "return Array element description of element type" in {
    getElementType(Array(1,2,3)) should include ("Array element")
  }
  it should "return Iterable element description of element type" in {
    getElementType(List(1,2,3)) should include ("Iterable element")
  }
  it should "return Unknown description of element type" in {
    getElementType(123) should include ("Unknown")
  }
}