# Scala 3: A First Look at Types — Part 4: Типи у функціях, псевдоніми типів
![img](img.png)

## 1. Типи у функціях

```scala
def square(x: Int): Int = x * x
```

## 2. Функції як значення

```scala
val doubler: Int => Int = x => x * 2
```

## 3. Функції з кількома аргументами

```scala
val adder: (Int, Int) => Int = (x, y) => x + y
```

## 4. Псевдоніми типів (Type Aliases)

```scala
type Name = String
type Age = Int
type Person = (Name, Age)

val person: Person = ("Alice", 30)
```

## 5. Union типи

```scala
def stringify(value: Int | String): String =
  value match
    case i: Int => s"int: $i"
    case s: String => s"string: $s"
```

## Висновки

- Функції — це значення в Scala
- Можна описувати їх типи вручну
- Type aliases спрощують сигнатури
- Union/Intersection типи роблять код гнучкішим
