import scala.language.implicitConversions

implicit class HasIsEven(n: Int) {
  def isEven: Boolean = n % 2 == 0
}

42.isEven
HasIsEven(42).isEven
new HasIsEven(42).isEven
