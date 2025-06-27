# Scala 3: Methods

## 🔹 Що таке метод?
Метод — іменована функція, яка:
- приймає параметри
- виконує логіку
- повертає значення

---

## 🔸 Базовий метод

```scala
def greet(name: String): String =
  s"Hello, $name!"
```

---

## 🔸 Кілька списків параметрів

```scala
def add(x: Int)(y: Int): Int = x + y
```

---

## 🔸 Метод як значення

```scala
val adder: (Int, Int) => Int = add(_, _)
```

---

## 🔸 Параметри за замовчуванням

```scala
def greet(name: String = "User"): String =
  s"Hello, $name"
```

---

## 🔸 Перевантаження методів

```scala
def log(msg: String): Unit = println(msg)
def log(msg: String, level: String): Unit = println(s"$level: $msg")
```

---

## 🔸 Inline-методи

```scala
inline def twice(inline x: Int): Int = x * 2
```

---

## 🔸 Using параметри

```scala
def showName(using name: String): String = s"User is $name"
given name: String = "Alice"
showName // "User is Alice"
```

---

## 🔸 Методи всередині об'єкта

```scala
object MathUtils:
  def square(x: Int): Int = x * x
```

---

## 🔸 Varargs

```scala
def sumAll(xs: Int*): Int = xs.sum
```

---

## 🧪 Тестування

```scala
class MethodTest extends AnyFunSuite:

  test("add should return correct sum") {
    assert(add(3)(4) == 7)
  }

  test("greet with default") {
    assert(greet() == "Hello, User")
  }

  test("sumAll with multiple arguments") {
    assert(sumAll(1, 2, 3) == 6)
  }
```
