# Scala 3: Control Structures

## 1. if / else — вирази

```scala
val result = if 1 + 1 == 2 then "Yes" else "No"
```

- `if` повертає значення
- можна використовувати як вираз у змінній

## 2. while / do-while

```scala
var i = 0
while i < 3 do
  println(i)
  i += 1

do
  println(s"From do-while: $i")
  i -= 1
while i > 0
```

- Мають тип `Unit`
- Класичні імперативні цикли

## 3. for з генераторами та фільтрами

```scala
for i <- 1 to 5 if i % 2 == 0 do println(i)
```

- Прості цикли з `if` у тілі

## 4. for ... yield — функціональна конструкція

```scala
val evens = for
  i <- 1 to 10
  if i % 2 == 0
yield i * 2
```

- Повертає колекцію
- Вбудовані фільтри, трансформації

## 5. match — pattern matching

```scala
def describe(x: Any): String = x match
  case 0 => "zero"
  case s: String => s"String: $s"
  case _ => "something else"
```

- Потужна заміна switch
- Працює з типами, шаблонами, case-класами
