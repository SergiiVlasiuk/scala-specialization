## 🔸 Доповнення: `using` / `given` у Scala 3

Scala 3 замінює старі `implicit` конструкції на більш зрозумілі `using` / `given`.

---

### 🔹 Контекстні параметри

```scala
def showName(using name: String): String =
  s"User is $name"
```

- `name` — параметр, який **повинен бути наданий неявно** (через `given`)
- Такий параметр не передається явно за замовчуванням

---

### 🔹 Контекстне значення

```scala
given name: String = "Alice"
```

- Тепер у області видимості **є `String`**, який може бути використаний будь-якою функцією, що очікує `using String`

---

### 🔹 Приклад виклику

```scala
val result = showName // "User is Alice"
```

- `showName` викликається **без параметрів**
- Scala компілятор **підставляє** `given name: String`

---

### 🔹 Явне передавання

```scala
showName(using "Bob") // => "User is Bob"
```

---

## 🧱 Повноцінний приклад

```scala
trait Logger:
  def log(msg: String): Unit

given ConsoleLogger: Logger with
  def log(msg: String): Unit = println(s"[LOG]: $msg")

def process()(using logger: Logger): Unit =
  logger.log("Processing started...")

process() // [LOG]: Processing started...
```

---

## 🔄 `using` з `summon`

```scala
def show()(using name: String): String =
  val n = summon[String]
  s"Hello, $n"
```

- `summon[T]` — отримати `given T` із контексту

---

## 🧪 Тест

```scala
test("summon should use given context") {
  given testName: String = "Test"
  val result = summon[String]
  assert(result == "Test")
}
```

---

## 📚 Практичні сценарії

- Логування: `given Logger`
- Конфігурація: `given AppConfig`
- Локалізація: `given Locale`
- Typeclasses: `given Eq[T]`, `given Show[T]`
