val ints = List(1, 2, 3, 4, 5)

for i <- ints do println(i)

//Guards
for
  i <- ints
  if i > 2
do println(i)

for
  i <- 1 to 3
  j <- 'a' to 'c'
  if i == 2
  if j == 'b'
do println(s"i = $i, j = $j")

val doubles = for i <- ints yield i * 2

val result = for {
  n <- List(1, 2, 3)
  if n % 2 != 0
  s <- List("a", "b")
} yield s"$n$s"

val strings = List("a", "b")
ints
  .filter(_ % 2 != 0)
  .flatMap(n => strings.map(s => s"$n$s"))

val names = List("chris", "ed", "maurice")
val capNames = for name <- names yield name.capitalize
