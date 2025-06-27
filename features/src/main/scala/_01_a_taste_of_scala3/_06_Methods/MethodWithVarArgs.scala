package _01_a_taste_of_scala3._06_Methods


object MethodWithVarArgs:
  def average(nums: Double*): Double =
    nums.sum / nums.length
