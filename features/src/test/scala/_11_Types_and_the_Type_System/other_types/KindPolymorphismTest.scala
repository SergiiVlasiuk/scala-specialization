package _11_Types_and_the_Type_System.other_types

import _11_Types_and_the_Type_System.other_types.KindPolymorphism.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class KindPolymorphismTest extends AnyFlatSpec with Matchers:

  def transform[F[_]: Functor, A, B](fa: F[A])(f: A => B): F[B] =
    summon[Functor[F]].map(fa)(f)

  "transform" should "map over List" in {
    transform(List(1, 2, 3))(_ * 2) shouldEqual List(2, 4, 6)
  }
