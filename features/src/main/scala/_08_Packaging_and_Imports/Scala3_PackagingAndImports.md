# Scala 3: Packaging and Imports

Повне пояснення ключових концепцій з прикладами.

---

## 1. Створення пакета

```scala
package com.example.utils

object MathUtils:
  def square(x: Int): Int = x * x
```

## 2. Кілька пакетів у одному файлі

```scala
package com.example.services:
  import com.example.models.User
  import com.example.utils.MathUtils
```

## 3. Імпорт — базовий синтаксис

- Імпорт кількох елементів:
  ```scala
  import scala.collection.mutable.{Map, ArrayBuffer}
  ```

- Перейменування:
  ```scala
  import java.time.LocalDate as Date
  ```

- Приховування:
  ```scala
  import scala.util.{Try, Success, Failure as _}
  ```

## 4. Імпорти можна писати будь-де

```scala
def handle(file: String): Unit =
  if file.endsWith(".json") then
    import scala.util.parsing.json._
    ...
```

## 5. "Static" imports

```scala
import scala.math.*

val result = cos(PI / 3)
```

## 6. Імпорти за замовчуванням

- `java.lang._`
- `scala._`
- `scala.Predef._`

## 7. Конфлікти назв

```scala
import java.util.{Date => UtilDate}
import java.sql.{Date => SqlDate}
```

## 8. Імпорти given-екземплярів

```scala
given Ordering[Int] = Ordering.Int.reverse
val sorted = List(3, 1, 2).sorted
```

## 9. By-type імпорти (Scala 3)

```scala
import given Ordering[Int]
List(3, 2, 1).sorted
```

---

Кожен пункт має відображення в окремому файлі для кращої організації та тестування.