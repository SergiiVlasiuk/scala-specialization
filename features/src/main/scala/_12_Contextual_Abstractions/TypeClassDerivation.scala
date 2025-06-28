package _12_Contextual_Abstractions

object TypeClassDerivation:
  trait ShowTypeClassDerivation[T]:
    def show(value: T): String

  object ShowTypeClassDerivation:
    given ShowTypeClassDerivation[Int] with
      def show(v: Int): String = v.toString

    // Автоматичне виведення для case class через Mirror
    import scala.deriving.Mirror
    import scala.compiletime.{erasedValue, summonInline}

    inline def summonAll[T <: Tuple]: List[ShowTypeClassDerivation[?]] =
      inline erasedValue[T] match
        case _: EmptyTuple => Nil
        case _: (h *: t) => summonInline[ShowTypeClassDerivation[h]] :: summonAll[t]

    given derived[T](using m: Mirror.ProductOf[T]): ShowTypeClassDerivation[T] with
      def show(value: T): String =
        val elems = value.asInstanceOf[Product].productIterator.toList
        s"${value.getClass.getSimpleName}(${elems.mkString(", ")})"


  def printValue[T](value: T)(using s: ShowTypeClassDerivation[T]): String = s.show(value)

  case class Person(name: String, age: Int) derives ShowTypeClassDerivation
