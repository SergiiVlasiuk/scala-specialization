trait SemiGroup[T]:
  extension (x: T) def combine (y: T): T

def reduce[T: SemiGroup](xs: List[T]): T = xs.reduceLeft(_.combine(_))

trait Monoid[T] extends SemiGroup[T]:
  def unit: T
object Monoid:
  def apply[T](using m: Monoid[T]): Monoid[T] = m

def reduce2[T](xs: List[T])(using m: Monoid[T]): T = xs.foldLeft(m.unit)(_.combine(_))
def reduce3[T: Monoid](xs: List[T]): T = xs.foldLeft(summon[Monoid[T]].unit)(_.combine(_))
//def reduce4[T: Monoid](xs: List[T]): T = xs.reduceLeft(Monoid[T].unit)(_.combine(_)) // apply ?

given sumMonoid: Monoid[Int] with
  extension (x: Int) def combine(y: Int): Int = x + y
  def unit: Int = 0

given prodMonoid: Monoid[Int] with
  extension (x: Int) def combine(y: Int): Int = x * y
  def unit: Int = 1


def sum(xs: List[Int]) = reduce(xs)(using sumMonoid)
def product(xs: List[Int]) = reduce(xs)(using prodMonoid)

val xs = List(1, 2, 3, 4)
sum(xs)
//product(xs)
