case class Rational(number: Int, denom: Int)

//trait Ordering[A] with
//  def compare(x: A, y: A): Boolean
trait Ordering[A]:
  def compare(x: A, y: A): Int
  extension (x: A)
    def < (y: A): Boolean = compare(x, y) < 0
    def <=(y: A): Boolean = compare(x, y) <= 0
    def > (y: A): Boolean = compare(x, y) > 0
    def >=(y: A): Boolean = compare(x, y) >= 0

given RationalOrdering: Ordering[Rational] with
  def compare(x: Rational, y: Rational) =
    val xn = x.number * y.denom
    val yn = y.number * x.denom
    if xn < yn then -1 else if xn > yn then 1 else 0


