package _01_a_taste_of_scala3._09_Collections

object FoldExamples:

  def sumLeft(nums: List[Int]): Int =
    nums.foldLeft(0)((acc, elem) => acc + elem)

  def sumRight(nums: List[Int]): Int =
    nums.foldRight(0)((elem, acc) => elem + acc)

  def concatLeft(words: List[String]): String =
    words.foldLeft("")((acc, elem) => acc + elem)

  def concatRight(words: List[String]): String =
    words.foldRight("")((elem, acc) => elem + acc)

  def diffLeft(nums: List[Int]): Int =
    nums.foldLeft(0)((acc, elem) => acc - elem)

  def diffRight(nums: List[Int]): Int =
    nums.foldRight(0)((elem, acc) => elem - acc)
