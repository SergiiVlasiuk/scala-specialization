package _11_Types_and_the_Type_System.other_types

object HigherKindedTypes:

  // A type class for containers that can map
  trait Functor[F[_]]:
    def map[A, B](fa: F[A])(f: A => B): F[B]

  // Functor instance for List
  given Functor[List] with
    def map[A, B](fa: List[A])(f: A => B): List[B] = fa.map(f)

  // Functor instance for Option
  given Functor[Option] with
    def map[A, B](fa: Option[A])(f: A => B): Option[B] = fa.map(f)

  def transform[F[_]: Functor, A, B](fa: F[A])(f: A => B): F[B] =
    summon[Functor[F]].map(fa)(f)
