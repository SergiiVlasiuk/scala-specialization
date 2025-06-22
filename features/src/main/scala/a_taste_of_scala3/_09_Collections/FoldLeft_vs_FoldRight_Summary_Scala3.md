# FoldLeft vs FoldRight у Scala 3

## Що таке fold?

- Метод для згортання колекції у одне значення.
- Приймає початкове значення (акумулятор) і функцію для комбінування акумулятора та елемента.
- Відмінність foldLeft і foldRight — напрям обходу колекції.

## foldLeft

- Починає з лівого (першого) елемента.
- Функція: (accumulator, element) => ...
- Виконує операцію зліва направо.

```scala
val numbers = List(1, 2, 3, 4)
val sum = numbers.foldLeft(0)((acc, elem) => acc + elem)
// (((0 + 1) + 2) + 3) + 4 = 10
```
## foldRight

- Починає з правого (останнього) елемента.
- Функція: (element, accumulator) => ...
- Виконує операцію справа наліво.

```scala 3
val numbers = List(1, 2, 3, 4)
val sum = numbers.foldRight(0)((elem, acc) => elem + acc)
// 1 + (2 + (3 + (4 + 0))) = 10
```

## Приклад із неасоціативною операцією

```scala 3
val nums = List(1, 2, 3, 4)
val leftFold = nums.foldLeft(0)((acc, elem) => acc - elem)   // -10
val rightFold = nums.foldRight(0)((elem, acc) => elem - acc) // -2
```

## Код із прикладами
```scala 3
object FoldExamples:

  def sumLeft(nums: List[Int]): Int =
    nums.foldLeft(0)((acc, elem) => acc + elem)

  def sumRight(nums: List[Int]): Int =
    nums.foldRight(0)((elem, acc) => elem + acc)

  def diffLeft(nums: List[Int]): Int =
    nums.foldLeft(0)((acc, elem) => acc - elem)

  def diffRight(nums: List[Int]): Int =
    nums.foldRight(0)((elem, acc) => elem - acc)
```

## Tests
```scala 3
import org.scalatest.funsuite.AnyFunSuite

class FoldExamplesTest extends AnyFunSuite:

  test("sumLeft sums correctly") {
    assert(FoldExamples.sumLeft(List(1, 2, 3, 4)) == 10)
  }

  test("sumRight sums correctly") {
    assert(FoldExamples.sumRight(List(1, 2, 3, 4)) == 10)
  }

  test("diffLeft subtracts correctly") {
    assert(FoldExamples.diffLeft(List(1, 2, 3, 4)) == -10)
  }

  test("diffRight subtracts correctly") {
    assert(FoldExamples.diffRight(List(1, 2, 3, 4)) == -2)
  }
```
