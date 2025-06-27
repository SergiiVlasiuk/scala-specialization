package _09_Scala_Collections.methods

// ----------------------------------------
// Scala Collections — Methods Examples
// ----------------------------------------

object SampleData:
  val nums = List(1, 2, 3, 4, 5)
  val words = List("apple", "banana", "cherry", "date")


// ----------------------------------------
// map — трансформація елементів
// ----------------------------------------

object MapExamples:
  val squares = SampleData.nums.map(n => n * n)
  val lengths = SampleData.words.map(_.length)


// ----------------------------------------
// filter — вибірка за умовою
// ----------------------------------------

object FilterExamples:
  val evens = SampleData.nums.filter(_ % 2 == 0)
  val longWords = SampleData.words.filter(_.length > 5)


// ----------------------------------------
// foreach — побічні ефекти (наприклад println)
// ----------------------------------------

object ForeachExamples:
  def printWords(): Unit = SampleData.words.foreach(println)


// ----------------------------------------
// head / tail — доступ до початку списку
// ----------------------------------------

object HeadTailExamples:
  val first = SampleData.nums.head
  val rest = SampleData.nums.tail


// ----------------------------------------
// take / takeRight / takeWhile — відбір елементів
// ----------------------------------------

object TakeExamples:
  val firstTwo = SampleData.nums.take(2)
  val lastTwo = SampleData.nums.takeRight(2)
  val takenWhile = SampleData.nums.takeWhile(_ < 4)


// ----------------------------------------
// drop / dropRight / dropWhile — відкидання елементів
// ----------------------------------------

object DropExamples:
  val dropTwo = SampleData.nums.drop(2)
  val dropRightTwo = SampleData.nums.dropRight(2)
  val dropWhileLessThan3 = SampleData.nums.dropWhile(_ < 3)


// ----------------------------------------
// reduce — зведення елементів у одне значення
// ----------------------------------------

object ReduceExamples:
  val sum = SampleData.nums.reduce(_ + _)
  val max = SampleData.nums.reduce(_ max _)


// ----------------------------------------
// Even more: reverse, mkString, distinct
// ----------------------------------------

object MoreExamples:
  val reversed = SampleData.words.reverse
  val joined = SampleData.words.mkString(", ")
  val distinctNums = List(1, 2, 2, 3, 1).distinct
