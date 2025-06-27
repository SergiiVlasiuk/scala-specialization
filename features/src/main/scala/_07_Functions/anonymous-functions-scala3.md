# Anonymous Functions у Scala 3

## 📘 Що таке анонімна функція?

Анонімна функція (lambda) — це функція без імені, яка використовується там, де потрібно передати функціональне значення. Часто використовується у методах `map`, `filter`, `fold`, як колбеки та аргументи для вищих функцій.

---

## 🔶 Повна форма (longer form)

```scala
val add = (x: Int, y: Int) => x + y
```

---

## 🔽 Скорочення анонімних функцій

### 1. Без типів (Scala сама виведе)

```scala
val doubled = List(1, 2, 3).map(x => x * 2)
```

### 2. Placeholder syntax (`_`)

```scala
val incremented = List(1, 2, 3).map(_ + 1)
val summed = List(1, 2, 3).reduce(_ + _)
```

---

## ⚠️ Обмеження placeholder-нотації

```scala
List(1, 2, 3).map(_ + _ + _) // НЕ працює! Невідомо, що означають три `_`
```

---

## ✅ Юніт-тести

```scala
import org.scalatest.funsuite.AnyFunSuite

class AnonymousFunctionsTest extends AnyFunSuite:

  test("long form anonymous function") {
    val add = (x: Int, y: Int) => x + y
    assert(add(3, 4) == 7)
  }

  test("short form with inferred types") {
    val list = List(1, 2, 3)
    val doubled = list.map(x => x * 2)
    assert(doubled == List(2, 4, 6))
  }

  test("placeholder syntax") {
    val list = List(1, 2, 3)
    val incremented = list.map(_ + 1)
    assert(incremented == List(2, 3, 4))
  }

  test("reduce with placeholder") {
    val list = List(1, 2, 3)
    val sum = list.reduce(_ + _)
    assert(sum == 6)
  }
```

---

## 💼 Інтерв’ю-питання і відповіді

### 1. Які форми анонімних функцій підтримує Scala 3?

**Відповідь:** Повна форма з параметрами і типами, коротка форма з виведеними типами, та placeholder-нотація `_`.

---

### 2. Коли не можна використовувати `_`?

**Відповідь:** Якщо кількість `_` перевищує кількість очікуваних параметрів або виникає неоднозначність.

---

### 3. Чим відрізняється `x => x + 1` від `_ + 1`?

**Відповідь:** Перший варіант явно оголошує параметр, другий використовує плейсхолдер. Поведінка однакова, але перший гнучкіший для складних виразів.

---

Склала: Scala 3 Mentor 😎
