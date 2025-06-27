package _07_Functions

// Full form
def add = (x: Int, y: Int) => x + y

// Shorter form
def double = (x: Int) => x * 2

// Underscore form
val doubleList: List[Int] => List[Int] = _.map(_ * 2)
