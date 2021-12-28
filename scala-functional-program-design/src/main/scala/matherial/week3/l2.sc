// using clauses
def sort[T](xs: List[T])(using ord: Ordering[T]): List[T] = ???
//         sort(strings)(using Ordering.String)
//         sort(strings)

// Anonymous using clauses
def sortAnonimous[T](xs: List[T])(using Ordering[T]): List[T] = ???



//context bound (upper bound in this case):
def printSortedUsualWay[T]          (as: List[T])(using Ordering[T]) = println(sort(as))
def printSorted        [T: Ordering](as: List[T])                    = println(sort(as))


object Ordering:
  // given instance
  given Int: Ordering[Int] with
    def compare(x: Int, y: Int): Int =
      if x < y then -1 else if x > y then 1 else 0
  // Anonymous given instance
  given Ordering[Double] with
    def compare(x: Double, y: Double): Int = // compiler names given_Ordering_Double
      if x < y then -1 else if x > y then 1 else 0


/** Summon a given value of type `T`. Usually, the argument is not passed explicitly.
 *
 *  @tparam T the type of the value to be summoned
 *  @return the given value typed: the provided type parameter
 */
//  transparent inline def summon[T](using inline x: T): x.type = x
//summon(23.2)


/*
importing given instances
- by name
  import scala.math.Ordering.Int
- by type
  import scala.math.Ordering.{given Ordering[Int]}
  import scala.math.Ordering.{given Ordering[?]}
- with wildcard:
  import scala.math.given
 */


class A[T](x: T)
given universal[T](using x: T): A[T](x) with {}
given specific: A[Int](2) with {}

summon[A[Int]]

