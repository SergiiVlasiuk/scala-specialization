# First-Class Functions у Scala

## Що таке First-Class Functions?

Функції в Scala — це повноцінні значення (first-class citizens), які можна присвоювати змінним, передавати як аргументи, повертати з функцій та зберігати у колекціях.

## Ключові особливості

- Функції як значення: `val f = (x: Int) => x + 1`
- Функції як параметри: `def applyFunc(f: Int => Int, x: Int) = f(x)`
- Функції як результат: `def makeAdder(n: Int): Int => Int = x => x + n`
- Анонімні функції (лямбди) для компактного опису
- Вбудовані типи функцій `Function1`, `Function2` і т.д.

## Приклади

```scala
val addOne: Int => Int = x => x + 1
def applyFunc(f: Int => Int, x: Int): Int = f(x)
def makeAdder(n: Int): Int => Int = x => x + n
val squares = List(1,2,3).map(x => x * x)
