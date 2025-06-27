# Scala 3: Control Structures — Практичні Тонкощі та Співбесіди

## 1. `if` як вираз

```scala
val maybeString: String | Unit =
  if 1 > 0 then "Yes"
```

- Якщо немає `else`, тип: `T | Unit`
- Завжди додавайте `else`, якщо очікуєте значення

## 2. `while` — антипатерн у FP

- Має тип `Unit`
- Не підтримує immutable state
- Краще замінити на рекурсію або `.foldLeft`, `.foreach`

## 3. `for` comprehension

```scala
val doubled = for i <- List(1,2,3) yield i * 2
```

- Транслюється у: `List(1,2,3).map(i => i * 2)`
- Без `yield`: виконується `foreach`, повертає `Unit`

## 4. `match` повинен бути повним

```scala
def danger(x: Boolean) = x match
  case true => "yes"
```

- Компілюється, але `false` спричинить MatchError
- Завжди охоплюйте всі варіанти

## 5. for comprehension з Option, Either, Future

```scala
val result = for
  a <- Some(2)
  b <- Some(3)
yield a + b
```

- Працює, бо `Option` має `map`, `flatMap`, `withFilter`

---

## Підсумкова Таблиця

| Тема                | Що знати                                               |
|---------------------|--------------------------------------------------------|
| `if`                | Це вираз, не оператор. Повертає значення.              |
| `while`             | Має тип Unit. Небажаний у FP-коді.                     |
| `for`               | `for ... yield` = map/flatMap/withFilter               |
| `match`             | Повинен бути повним, інакше MatchError                 |
| for-comprehension   | Працює з Option, Either, Future, List тощо             |
