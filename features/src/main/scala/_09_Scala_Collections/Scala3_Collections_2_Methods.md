# Scala 3 Collections — Methods

Цей конспект охоплює типові методи роботи з колекціями в Scala, їх призначення та приклади використання.

---

## 🔂 Вхідні дані

```scala
val nums = List(1, 2, 3, 4, 5)
val words = List("apple", "banana", "cherry", "date")
```

---

## 🔁 map — трансформує кожен елемент

```scala
val squares = nums.map(n => n * n)         // List(1, 4, 9, 16, 25)
val lengths = words.map(_.length)          // List(5, 6, 6, 4)
```

---

## 🔍 filter — вибирає елементи за умовою

```scala
val evens = nums.filter(_ % 2 == 0)        // List(2, 4)
val longWords = words.filter(_.length > 5) // List("banana", "cherry")
```

---

## 🪝 foreach — ітерація для побічних ефектів

```scala
words.foreach(println)
```

---

## 🎯 head / tail — доступ до початку списку

```scala
val first = nums.head   // 1
val rest = nums.tail    // List(2, 3, 4, 5)
```

---

## 🎣 take / takeRight / takeWhile

```scala
nums.take(2)             // List(1, 2)
nums.takeRight(2)        // List(4, 5)
nums.takeWhile(_ < 4)    // List(1, 2, 3)
```

---

## 🪓 drop / dropRight / dropWhile

```scala
nums.drop(2)             // List(3, 4, 5)
nums.dropRight(2)        // List(1, 2, 3)
nums.dropWhile(_ < 3)    // List(3, 4, 5)
```

---

## 🧮 reduce — агрегація

```scala
nums.reduce(_ + _)       // 15
nums.reduce(_ max _)     // 5
```

---

## 🎁 Інші корисні методи

```scala
words.reverse            // List("date", "cherry", "banana", "apple")
words.mkString(", ")     // "apple, banana, cherry, date"
List(1, 2, 2, 3, 1).distinct // List(1, 2, 3)
```

---

📌 Продовження: методи з групуванням (`groupBy`, `partition`, `fold`, `scan`, `flatten`, `flatMap`) — в наступній частині.