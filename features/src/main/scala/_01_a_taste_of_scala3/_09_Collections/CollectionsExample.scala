package _01_a_taste_of_scala3._09_Collections

object CollectionsExample:

  def listExample(): List[Int] =
    val nums = List(1, 2, 3, 4, 5)
    val doubled = nums.map(_ * 2)
    val evens = doubled.filter(_ % 2 == 0)
    evens

  def tupleExample(): (Int, String, Boolean) =
    val tuple = (42, "Scala", true)
    tuple

  def divideWithRemainder(x: Int, y: Int): (Int, Int) = (x / y, x % y)
