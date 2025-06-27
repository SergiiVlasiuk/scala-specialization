package _08_Packaging_and_Imports

object GivenByType:
  given intOrd: Ordering[Int] = Ordering.Int
  given strOrd: Ordering[String] = Ordering.String

  def sortStrings(): List[String] =
    import scala.language.`implicitConversions`
    List("b", "a", "c").sorted(using strOrd)

  def sortInts(): List[Int] =
//    import scala.language.`implicitConversions`
    List(3, 2, 1).sorted(using intOrd)


@main def run(): Unit =
  println("hello")
