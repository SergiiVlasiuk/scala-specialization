# 📘 Scala 3 — Pure Functions

---

## ❓ Що таке Pure Function?

**Чиста функція** — це функція, яка:
1. **Завжди повертає той самий результат** при однакових аргументах.
2. **Не має побічних ефектів** (не змінює зовнішній стан, не викликає I/O, не змінює глобальні змінні тощо).

---

## ✅ Приклади чистих функцій

```scala
def add(x: Int, y: Int): Int =
  x + y

def isEven(n: Int): Boolean =
  n % 2 == 0

def capitalize(name: String): String =
  name.trim.toUpperCase()
```

---

## ❌ Приклади нечистих функцій

```scala
var counter = 0
def increment(): Int =
  counter += 1
  counter

def logMessage(msg: String): Unit =
  println(s"LOG: $msg")

def getCurrentTime(): Long =
  System.currentTimeMillis()
```

---

## 😐 Але нечисті функції все ж потрібні…

- I/O (запис у файл, консоль, базу даних)
- Робота з часом (`System.currentTimeMillis`)
- Отримання випадкових чисел (`scala.util.Random`)
- Виклики API

⚠ FP не забороняє impure-функції, але **ізолює** їх.

---

## 🛠 Як писати чисті функції

### 1. **Не змінюй зовнішній стан**

```scala
def addToList(lst: List[Int], elem: Int): List[Int] =
  lst :+ elem
```

### 2. **Залежність тільки від параметрів**

```scala
def greet(name: String): String =
  s"Hello, $name"
```

### 3. **Немає `println`, `var`, `random`, `new Date()` всередині функції**

---

## 🔑 Ключові ознаки

| Ознака                | Чиста функція | Нечиста функція |
|-----------------------|---------------|-----------------|
| Залежить лише від аргументів | ✅            | ❌              |
| Завжди однаковий результат  | ✅            | ❌              |
| Побічні ефекти відсутні     | ✅            | ❌              |
| Легко тестується            | ✅            | ❌              |
| Можна кешувати              | ✅            | ❌              |

---

## 🧠 Питання для співбесіди

### ❓ Що таке pure function?

> Це функція без побічних ефектів, яка завжди повертає той самий результат при однакових вхідних даних.

---

### ❓ Наведи приклад нечистої функції

> `def log(msg: String): Unit = println(msg)` — має побічний ефект: вивід у консоль.

---

### ❓ Чому чисті функції кращі?

> Вони простіші для тестування, дебагу, рефакторингу. Можна виконувати паралельно. Можна кешувати.

---

### ❓ Чи можна обійтись без нечистих функцій?

> Ні, бо I/O потрібне. Але FP дозволяє **ізолювати** та **контролювати** їх (наприклад, через `IO`, `ZIO`, `Try`, `Either`).

---

## 📌 Висновок

Функціональне програмування базується на **чистих функціях**. Нечисті — допустимі, але мають бути керовані.