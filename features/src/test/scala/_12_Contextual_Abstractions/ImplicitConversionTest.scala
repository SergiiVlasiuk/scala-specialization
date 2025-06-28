package _12_Contextual_Abstractions

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import _12_Contextual_Abstractions.Conversions.given

class RationalConversionTest extends AnyFlatSpec with Matchers {

  "describe" should "accept Int via implicit conversion" in {
    val result = describe(3) // implicitly converted to Rational(3,1)
    result shouldEqual "This is a rational number: 3/1"
  }

  it should "also accept Rational directly" in {
    val result = describe(Rational(5, 2))
    result shouldEqual "This is a rational number: 5/2"
  }
}
