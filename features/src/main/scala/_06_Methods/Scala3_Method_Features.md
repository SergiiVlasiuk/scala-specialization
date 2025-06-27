# Scala 3: Method Features

Повний огляд можливостей при роботі з методами у Scala 3.

---

## 🔹 Defining Methods

```scala
def greet(name: String): String = s"Hello, $name"
```

Методи завжди оголошуються з `def`. Параметри можуть мати типи, і метод може повертати значення.

---

## 🔹 Calling Methods

```scala
greet("Serhii")
```

Методи викликаються з дужками, але **без дужок**, якщо метод не має параметрів:

```scala
def appName: String = "MyApp"
val name = appName
```

---

## 🔹 Multiline Methods

```scala
def logAndReturn(x: Int): Int =
  println(s"Logging $x")
  x
```

У Scala 3 **тіло методу без `=`** означає, що метод повертає `Unit`.

```scala
def log(x: Int): Unit =
  println(x) // немає "=" -> Unit
```

---

## 🔹 Default Parameter Values

```scala
def greet(name: String = "User") = s"Hello, $name"
greet()       // Hello, User
greet("Bob")  // Hello, Bob
```

---

## 🔹 Named Parameters

```scala
def format(name: String, age: Int): String = s"$name is $age"
format(age = 30, name = "Alice")
```

---

## 🔹 Methods without Parameters

```scala
def currentTime: Long = System.currentTimeMillis()
val t = currentTime // краще ніж currentTime()
```

📌 Рекомендація: **використовуй `()` тільки якщо метод має side effects**.

---

## 🔹 `if` як тіло методу

```scala
def isEven(n: Int): Boolean =
  if n % 2 == 0 then true else false
```

---

## 🔹 `match` як тіло методу

```scala
def sign(n: Int): String = n match
  case x if x > 0 => "positive"
  case x if x < 0 => "negative"
  case _          => "zero"
```

---

## 🔹 Controlling Visibility in Classes

```scala
class Secret:
  private def secretMethod(): String = "hidden"
```

- `private` — тільки в межах класу
- `protected` — доступний підкласам

---

## 🔹 Objects Can Contain Methods

```scala
object MathUtils:
  def square(x: Int): Int = x * x
```

---

## 🔹 Extension Methods

```scala
extension (x: Int)
  def squared: Int = x * x

val result = 5.squared // 25
```

---

## 🔹 Even More...

- `inline` методи
- `using` / `given`
- `varargs`: `def sumAll(xs: Int*) = xs.sum`
- рекурсивні методи
- анонімні методи (`val f = (x: Int) => x * 2`)
- partially applied methods: `val plusOne = add(1)(_)`

