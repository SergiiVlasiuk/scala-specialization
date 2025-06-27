# Singleton Objects у Scala 3

## Визначення

Singleton Object — це єдиний унікальний об’єкт, який існує у програмі в єдиному екземплярі.

## Оголошення

```scala
object Logger:
  def log(msg: String): Unit = println(s"[LOG] $msg")
```

## Companion Object

```scala
class User(val name: String):
  def greet(): String = s"Hello, $name!"

object User:
  def apply(name: String): User = new User(name)
```

## Особливості

- Singleton object створюється автоматично при першому виклику.
- Використовується для утиліт, глобального стану, фабричних методів.
- Scala 3 зберігає сумісність із Scala 2 у цьому питанні.

## Переваги

- Чистий і зручний спосіб організувати код.
- Дозволяє уникнути статичних методів.
- Підтримує патерн companion object для класів.
