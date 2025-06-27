package _11_Types_and_the_Type_System.other_types

object TypeLambdas:

  // Traditional type alias
  type EitherString[A] = Either[String, A]

  // Type lambda version
  type LambdaEither = [A] =>> Either[String, A]

  def create[A](value: A): LambdaEither[A] = Right(value)
