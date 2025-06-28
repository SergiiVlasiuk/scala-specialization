package _12_Contextual_Abstractions

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import _12_Contextual_Abstractions.ExtensionMethods.{isUpperCase, show, squared}

class ExtensionMethodsTest extends AnyFlatSpec with Matchers {

  "squared" should "square an integer" in {
    5.squared shouldEqual 25
  }

  "isUpperCase" should "return true if string is upper case" in {
    "HELLO".isUpperCase shouldBe true
    "Hello".isUpperCase shouldBe false
  }

  "show" should "work with Show typeclass" in {
    42.show shouldEqual "Int(42)"
  }
}
