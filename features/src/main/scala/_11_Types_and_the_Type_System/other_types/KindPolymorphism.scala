package _11_Types_and_the_Type_System.other_types

object KindPolymorphism:
  trait Functor[F[_]]:
    def map[A, B](fa: F[A])(f: A => B): F[B]

  given Functor[List] with
    def map[A, B](fa: List[A])(f: A => B): List[B] = fa.map(f)



