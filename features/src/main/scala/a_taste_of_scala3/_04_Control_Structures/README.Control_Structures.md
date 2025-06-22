# for-yield
У Scala for-вирази — це синтаксичний цукор над комбінацією map, flatMap та withFilter (або filter).

Механізм такий:

    for (x <- xs) yield f(x) → xs.map(x => f(x))
    for (x <- xs; y <- ys) yield f(x, y) → xs.flatMap(x => ys.map(y => f(x, y)))
    for (x <- xs if cond(x)) yield f(x) → xs.withFilter(cond).map(x => f(x))

🔹 1. map — коли один генератор, без фільтрів
val numbers = List(1, 2, 3)
val doubled = for (n <- numbers) yield n * 2
// те саме що:
val doubledMap = numbers.map(n => n * 2)
🔁 for (n <- numbers) yield n * 2 → numbers.map(n => n * 2)

🔹 2. flatMap — коли два (або більше) генераторів підряд
val xs = List(1, 2)
val ys = List("a", "b")

val pairs = for {
x <- xs
y <- ys
} yield s"$x$y"
// Результат: List("1a", "1b", "2a", "2b")
Це розгортає всі комбінації. Аналог у flatMap:

val pairsFlatMap = xs.flatMap(x => ys.map(y => s"$x$y"))
🔁 for { x <- xs; y <- ys } yield ... → xs.flatMap(x => ys.map(...))

🔹 3. withFilter (або filter) — коли є if
val numbers = List(1, 2, 3, 4, 5)
val evenSquares = for {
n <- numbers
if n % 2 == 0
} yield n * n
// Результат: List(4, 16)
Це те саме, що:

val evenSquaresFilter = numbers.withFilter(n => n % 2 == 0).map(n => n * n)
// або .filter(...) у простих випадках
🔁 for (x <- xs if p(x)) yield f(x) → xs.withFilter(p).map(f)
