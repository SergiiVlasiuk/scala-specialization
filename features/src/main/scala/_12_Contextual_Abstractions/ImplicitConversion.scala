package _12_Contextual_Abstractions

import scala.language.implicitConversions

case class Rational(n: Int, d: Int):
  override def toString: String = s"$n/$d"

object Conversions:
  given Conversion[Int, Rational] with
    def apply(x: Int): Rational = Rational(x, 1)

def describe(r: Rational): String =
  s"This is a rational number: $r"
