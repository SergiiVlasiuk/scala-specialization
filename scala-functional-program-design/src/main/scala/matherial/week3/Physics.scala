package matherial.week3

sealed abstract class Substance
class Gaz extends Substance
class Liquid extends Substance
class Solid extends Substance

trait Physics {
  implicit def air: Gaz
  implicit def condense(implicit gaz: Gaz): Liquid
  implicit def freeze(implicit liquid: Liquid): Solid

  implicitly[Solid]
  implicitly(freeze)
  implicitly(freeze(condense(air)))
  implicitly(freeze(condense(air))): Solid
  implicitly[Solid](freeze(condense(air)))
  implicitly[Solid](freeze(condense(air))): Solid
}

