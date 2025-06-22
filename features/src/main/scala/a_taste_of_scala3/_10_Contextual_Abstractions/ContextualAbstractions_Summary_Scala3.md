# Contextual Abstractions у Scala 3

## Вступ

Contextual Abstractions включають:
- `given` — оголошення контекстних значень
- `using` — контекстні параметри
- `extension` — методи розширення
- `summon` — отримання контекстного значення

Це сучасний замінник `implicit` у Scala 2.

## Основні концепції

```scala
given intOrd: Ordering[Int] with
  def compare(x: Int, y: Int): Int = x - y

def max[T](x: T, y: T)(using ord: Ordering[T]): T =
  if ord.compare(x, y) > 0 then x else y

val result = max(10, 20)  // Використовує given Ordering[Int]

val ordInt = summon[Ordering[Int]]
```

## Приклад

```scala
trait Show[T]:
  def show(value: T): String

given Show[Int] with
  def show(value: Int): String = s"Int: $value"

given Show[String] with
  def show(value: String): String = s"String: $value"

def printValue[T](value: T)(using s: Show[T]): Unit =
  println(s.show(value))
```

## Переваги

- Явність і читабельність
- Безпечність контекстів
- Зручна робота з type class-ами
- Уніфікований синтаксис
