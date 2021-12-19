trait Generator[+T]:
  self => // Generator alias
  def generate(): T

  // trait inside implementation way
  def map[S](f: T => S) = new Generator[S]: // new Generator[S]: creates anonimous class
//    def generate() = f(Generator.this.generate())
    def generate() = f(self.generate()) // equivalent to f(Generator.this.generate())
  def flatMap[S](f: T => Generator[S]) = new Generator[S]:
    def generate() = f(Generator.this.generate()).generate()

// extension way
/*
extension [T, S](g: Generator[T])
  def map(f: T => S) = new Generator[S]:
    def generate() = f(g.generate())
  def flatMap(f: T => Generator[S]) = new Generator[S]:
    def generate() = f(g.generate()).generate()
*/


// ========================

val integers = new Generator[Int]:
  val rand = java.util.Random()
  def generate() = rand.nextInt()

val booleans = new Generator[Boolean]:
  def generate() = integers.generate() > 0
// val booleans = for x <- integers yield x > 0 // without new Generator
val booleans2 = integers.map(x => x > 0)
val booleans3 = new Generator[Boolean]:
  def generate(): Boolean = ((x: Int) => x > 0)(integers.generate())
val booleans4 = new Generator[Boolean]:
  def generate(): Boolean = integers.generate() > 0

booleans.generate()
booleans2.generate()
booleans3.generate()
booleans4.generate()

val pairs = new Generator[(Int, Int)]:
  override def generate(): (Int, Int) = (integers.generate(), integers.generate())
/* // without new Generator
def pairs[T, U](t: Generator[T], u: Generator[U]) =
  for x <- t; y <- u yield (x, y)
 */
def pairs2[T, U](t: Generator[T], u: Generator[U]) =
  t.flatMap(x => u.map(y => (x, y)))
def pairs3[T, U](t: Generator[T], u: Generator[U]) =
  t.flatMap(x => new Generator[(T, U)] { def generate() = (x, u.generate())})
def pairs4[T, U](t: Generator[T], u: Generator[U]) = new Generator[(T, U)]:
  def generate() =
    println(s"pairs4. generate: (new Generator[(T, U)] {...")
    (new Generator[(T, U)] {
      def generate() =
        println(s"pairs4. generate: (t.generate(), u.generate())")
        (t.generate(), u.generate())
    }).generate()
def pairs5[T, U](t: Generator[T], u: Generator[U]) = new Generator[(T, U)]:
  def generate() = (t.generate(), u.generate())



pairs.generate()
//pairs2()



// =========================
println("more genarator examples")

def single[T](x: T): Generator[T] = new Generator[T]:
  override def generate() = x

def choose[T](lo: Int, hi: Int): Generator[Int] =
  print(s"\n lo: $lo; hi: $hi")
  for
    x <- integers
    _ = print(s"$x ")
  yield lo + x.abs % (hi - lo)

def oneOf[T](xs: T*): Generator[T] =
  for idx <- choose(0, xs.length) yield xs(idx)


val oneOfGenerator = oneOf(23, 12, 41, 17, -2, 4)
println(s"oneOf(23,12,41,17,-2,4): ${oneOfGenerator.generate()}")
println(s"oneOf(23,12,41,17,-2,4): ${oneOfGenerator.generate()}")

println(s"integers.generate(): ${integers.generate()}")

val rangeGenerator = choose(-2,33)
println(s"rangeGenerator.generate(): ${rangeGenerator.generate()}")
println(s"rangeGenerator.generate(): ${rangeGenerator.generate()}")


// list generator
def lists: Generator[List[Int]] =
  def emptyLists: Generator[List[Int]] = single(Nil)
  def nonEmptyLists: Generator[List[Int]] =
    for
      head <- integers
      tail <- lists
    yield head :: tail
  for
    kind <- choose(0, 5)
    list <- if kind == 0 then emptyLists else nonEmptyLists
  yield list

lists.generate()
lists.generate()
lists.generate()
lists.generate()
lists.generate()
lists.generate()






//enum Tree:
//  case Inner(left: Tree, right: Tree)
//  case Leaf(x: Int)

trait Tree
case class Inner(left: Tree, right: Tree) extends Tree
case class Leaf(x: Int) extends Tree

def trees: Generator[Tree] =
//  import Tree.*
  def inners: Generator[Tree] = for x: Int <- integers yield Leaf(x)
  def leafs: Generator[Tree] = for x <- trees; y <- trees yield Inner(x, y)
  for
    isLeaf <- booleans
    tree <- if isLeaf then leafs else inners
  yield tree

trees.generate()
trees.generate()
trees.generate()

// test function

def test[T](g: Generator[T], numTimes: Int = 100)
           (test: T => Boolean): Unit =
  for i <- 0 until numTimes do
    val value = g.generate()
    assert(test(value))
  println(s"passed $numTimes tests")

test(pairs5(lists, lists)) {
  (xs, ys) => (xs ::: ys).length > xs.length
}

/* // scala check instead of writing tests, write properties ???
forAll { (l1: List[Int], l2: List[Int]) =>
  l1.size + l2.size == (l1 ++ l2).size
}
*/
