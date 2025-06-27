# Partial Functions у Scala 3

## 🧠 Що таке Partial Function?

`PartialFunction[A, B]` — це функція, яка **визначена не для всіх значень типу A**.

```scala
val pf: PartialFunction[Int, String] = {
  case 1 => "один"
  case 2 => "два"
}
```

---

## 🔍 Властивості PartialFunction

- Має метод `isDefinedAt(x: A): Boolean`
- Реалізує `apply(x: A): B`
- Безпечна при використанні з `collect`, `orElse`, `lift`

---

## 📌 Коли використовувати

- Обробка лише відомого підмножини вхідних значень
- Замінити pattern matching у вищих функціях (`map`, `collect`)
- Валідація перед викликом (через `isDefinedAt`)

---

## ✨ Приклади

### 1. Звичайна функція проти часткової

```scala
val f: Int => String = x => x.toString          // тотальна
val pf: PartialFunction[Int, String] = {
  case 1 => "one"
  case 2 => "two"
}
```

### 2. Вивід `isDefinedAt`

```scala
pf.isDefinedAt(1) // true
pf.isDefinedAt(5) // false
```

---

## 🚀 Покращення коду з PartialFunction

### ➕ `collect` vs `map`

```scala
val list = List(1, 2, 3, 4)

val mapped = list.map {
  case 1 => "один"
  case 2 => "два"
  case x => s"інше: $x"
}

val collected = list.collect {
  case 1 => "один"
  case 2 => "два"
} // [один, два]
```

### ➕ `orElse`

```scala
val fallback: PartialFunction[Int, String] = {
  case x => s"невідоме: $x"
}

val combined = pf.orElse(fallback)

combined(2) // два
combined(5) // невідоме: 5
```

### ➕ `lift`

```scala
val lifted: Int => Option[String] = pf.lift

lifted(1) // Some("один")
lifted(9) // None
```

---

## 📚 Порівняння: Function vs PartialFunction

| Критерій              | Function1[A, B]        | PartialFunction[A, B]            |
|------------------------|-------------------------|-----------------------------------|
| Визначена для всіх A   | Так                     | Ні (лише для підмножини A)       |
| Безпечна при any input | Так                     | Ні (може кинути `MatchError`)    |
| Метод `isDefinedAt`    | Немає                   | Так                              |
| Можна комбінувати      | Ні                      | Так (`orElse`, `andThen`)        |
| Підтримка `lift`       | Немає                   | Так                              |

---

## ✅ Юніт-тести

```scala
import org.scalatest.funsuite.AnyFunSuite

class PartialFunctionTest extends AnyFunSuite:

  val pf: PartialFunction[Int, String] = {
    case 1 => "one"
    case 2 => "two"
  }

  test("apply defined case") {
    assert(pf(1) == "one")
  }

  test("isDefinedAt works") {
    assert(pf.isDefinedAt(2))
    assert(!pf.isDefinedAt(5))
  }

  test("fallback with orElse") {
    val fallback: PartialFunction[Int, String] = { case x => s"unknown: $x" }
    val combined = pf.orElse(fallback)
    assert(combined(2) == "two")
    assert(combined(9) == "unknown: 9")
  }

  test("lift converts to safe function") {
    val lifted = pf.lift
    assert(lifted(1) == Some("one"))
    assert(lifted(7) == None)
  }
```

---

## ❓ Інтерв’ю-питання і відповіді

### 1. Що таке PartialFunction і для чого його використовують?

**Відповідь:** PartialFunction — це функція, яка визначена не для всіх вхідних значень. Її використовують, коли потрібно безпечно обробити підмножину вхідних даних, зокрема в `collect`.

---

### 2. Чим PartialFunction кращий за pattern matching у функції?

**Відповідь:** PartialFunction має метод `isDefinedAt`, дозволяє безпечне використання з `collect`, `orElse`, `lift`, і полегшує композицію обробників.

---

### 3. Що таке PartialFunction у Scala?

**Відповідь:** Це функція, яка визначена не для всіх значень вхідного типу. Вона має метод isDefinedAt для перевірки.

---

### 4. У чому різниця між Function1 і PartialFunction?

**Відповідь:** Function1 визначена для всіх значень типу A. PartialFunction — лише для певної підмножини A і має метод `isDefinedAt`.

---

### 5. Як безпечно використовувати PartialFunction?

**Відповідь:** Або перевіряти isDefinedAt(x) перед викликом, або використовувати lift.

---

### 6. Для чого використовують orElse?

**Відповідь:** Для об'єднання кількох часткових функцій, кожна з яких обробляє свій випадок.

---

### 7. Які є альтернативи apply?

**Відповідь:** lift(x) повертає Option[B]; безпечніше та не кидає виключення.

---

Склала: Scala 3 Mentor 😎
