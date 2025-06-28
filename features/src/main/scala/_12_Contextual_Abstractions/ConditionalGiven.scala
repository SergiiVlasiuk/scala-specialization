package _12_Contextual_Abstractions

object ConditionalGiven:
  trait ShowConditional[T]:
    def show(value: T): String

  object ShowConditional:
    given ShowConditional[Int] with
      def show(value: Int): String = s"Int($value)"

    given [T](using s: ShowConditional[T]): ShowConditional[List[T]] with
      def show(value: List[T]): String =
        value.map(s.show).mkString("[", ", ", "]")

  def printValueConditional[T](value: T)(using sh: ShowConditional[T]): String =
    sh.show(value)

