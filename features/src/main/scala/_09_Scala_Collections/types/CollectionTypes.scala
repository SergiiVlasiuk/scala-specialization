package _09_Scala_Collections.types

object CollectionTypes:

  // --- Three main categories of collections ---
  // 1. Sequences (Seq): ordered collections (List, Vector, etc.)
  // 2. Sets: no duplicate elements (Set, HashSet, TreeSet)
  // 3. Maps: key-value pairs (Map, HashMap)

  val list: List[Int] = List(1, 2, 3)
  val vector: Vector[String] = Vector("a", "b")
  val set: Set[Int] = Set(1, 2, 2, 3)         // duplicates removed
  val map: Map[String, Int] = Map("a" -> 1, "b" -> 2)

  // --- Immutable by default ---
  // scala.collection.immutable is imported by default

  val immutableList = List(1, 2, 3)
  import scala.collection.mutable.ArrayBuffer
  val mutableList = ArrayBuffer(1, 2, 3)


// ----------------------------------------
// List — ordered, immutable, fast prepend
// ----------------------------------------

object ListExamples:
  val empty = List.empty[Int]
  val withElements = List(1, 2, 3)

  // Prepend is fast (O(1)) — use ::
  val prepended = 0 :: withElements

  // Looping
  def printAll(l: List[Int]): Unit =
    for elem <- l do println(elem)


// ----------------------------------------
// Vector — immutable, balanced performance
// ----------------------------------------

object VectorExamples:
  val v = Vector(1, 2, 3)
  val updated = v.updated(1, 99)


// ----------------------------------------
// ArrayBuffer — mutable, fast appends
// ----------------------------------------

import scala.collection.mutable.ArrayBuffer

object ArrayBufferExamples:
  val ab = ArrayBuffer(1, 2)
  ab += 3                 // add
  ab -= 2                 // remove
  ab(0) = 100             // update


// ----------------------------------------
// Map examples
// ----------------------------------------

object MapExamples:
  val capitals = Map("France" -> "Paris", "Germany" -> "Berlin")
  val paris = capitals("France")           // access
  val updated = capitals + ("Spain" -> "Madrid")
  val removed = capitals - "Germany"

  def printMap(m: Map[String, String]): Unit =
    for (k, v) <- m do println(s"$k -> $v")


// ----------------------------------------
// Set examples
// ----------------------------------------

object SetExamples:
  val s = Set(1, 2, 3)
  val added = s + 4
  val removed = s - 2


// ----------------------------------------
// Range — immutable, efficient
// ----------------------------------------

object RangeExamples:
  val inclusive = 1 to 5    // includes 5
  val exclusive = 1 until 5 // excludes 5

  val stepped = 1 to 10 by 2
  val reversed = (1 to 5).reverse
