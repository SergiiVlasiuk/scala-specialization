package _11_Types_and_the_Type_System.other_types

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import _11_Types_and_the_Type_System.other_types.TypeLambdas.*

class TypeLambdasTest extends AnyFlatSpec with Matchers {

  "Type lambda" should "wrap an Int in Either[String, Int]" in {
    val result = create(42)
    result shouldBe Right(42)
  }

  it should "wrap a String in Either[String, String]" in {
    val result = create("test")
    result shouldBe Right("test")
  }
}