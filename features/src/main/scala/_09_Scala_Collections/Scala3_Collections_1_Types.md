# Scala 3 Collections (Part 1)

Ця частина охоплює базові типи колекцій в Scala та основи роботи з ними.

---

## 🔢 Основні категорії колекцій

1. **Sequences (Seq)** — впорядковані, індексовані значення:
   - `List`, `Vector`, `Array`, `LazyList`, `ArrayBuffer`, `Range`

2. **Sets** — унікальні значення:
   - `Set`, `HashSet`, `TreeSet`

3. **Maps** — асоціації ключ-значення:
   - `Map`, `HashMap`, `TreeMap`

---

## 📌 Immutable collections

- За замовчуванням імпортується `scala.collection.immutable`
- Більшість стандартних API (наприклад `List`, `Map`) — незмінні

---

## 📋 List

- Швидке додавання на початок: `::`
- Повністю незмінна
- Ітерації з `for`, рекурсією, або `.foreach`

```scala
val list = List(1, 2, 3)
val newList = 0 :: list
list.foreach(println)
```

---

## ⚖ Vector

- Індексована, збалансована структура (дерево 32-гілкове)
- Краще підходить для великих об’ємів ніж `List`

```scala
val v = Vector(1, 2, 3).updated(1, 99)
```

---

## 🧱 ArrayBuffer

- Змінна, схожа на `ArrayList` в Java
- Швидке додавання, оновлення та видалення

```scala
val ab = ArrayBuffer(1, 2)
ab += 3
ab -= 2
ab(0) = 100
```

---

## 🔑 Map

```scala
val capitals = Map("France" -> "Paris")
val updated = capitals + ("Spain" -> "Madrid")
val removed = capitals - "France"
capitals.get("France")
```

---

## ✅ Set

- Не допускає дублікатів

```scala
val set = Set(1, 2, 3)
val added = set + 4
val removed = set - 2
```

---

## 🔁 Range

- Дуже ефективна послідовність
```scala
val r = 1 to 5      // 1, 2, 3, 4, 5
val r2 = 1 until 5  // 1, 2, 3, 4
val even = 2 to 10 by 2
```

---

## 🧵 LazyList

- Створює ліниву колекцію: обчислює елементи лише за потреби
- Ідеально для нескінченних або великих обчислень

```scala
val naturals: LazyList[Int] = LazyList.from(1)
val evens = naturals.filter(_ % 2 == 0)
```

---

📌 Продовження: методи колекцій (map, filter, flatMap, fold...) — у наступному розділі.