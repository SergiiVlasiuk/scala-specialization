# Collection Grouping and Aggregation Methods

## `groupBy`
Groups elements based on a function that returns a key.
```scala
val names = List("Anna", "Bob", "Alice", "Brian")
val grouped = names.groupBy(_.head) // Map('A' -> List("Anna", "Alice"), 'B' -> List("Bob", "Brian"))
```

## `partition`
Splits a collection into two based on a predicate.
```scala
val nums = List(1, 2, 3, 4, 5)
val (even, odd) = nums.partition(_ % 2 == 0) // (List(2, 4), List(1, 3, 5))
```

## `fold` (and `foldLeft`, `foldRight`)
Aggregates values with an initial seed.
```scala
val sum = List(1, 2, 3).fold(0)(_ + _) // 6
val product = List(1, 2, 3).foldLeft(1)(_ * _) // 6
```

## `scan`
Like `fold`, but returns intermediate results.
```scala
val scanned = List(1, 2, 3).scan(0)(_ + _) // List(0, 1, 3, 6)
```

## `flatten`
Flattens nested collections.
```scala
val nested = List(List(1, 2), List(3, 4))
nested.flatten // List(1, 2, 3, 4)
```

## `flatMap`
Maps and flattens in one pass.
```scala
val words = List("hello", "world")
val chars = words.flatMap(_.toList) // List('h','e','l','l','o','w','o','r','l','d')
```

---

# Interview Questions & Answers (Collections)

### Q8: When should you use `groupBy`?
**A:** When you want to categorize elements based on a computed key.

### Q9: What's the difference between `fold`, `reduce`, and `scan`?
**A:** `fold` uses an initial value; `reduce` doesn’t; `scan` returns all intermediate results.

### Q10: How does `flatMap` differ from `map` + `flatten`?
**A:** `flatMap` combines both operations efficiently.

### Q11: What does `partition` return?
**A:** A tuple with elements that satisfy the predicate and those that don’t.

---

# Summary (Collections)
- `groupBy` helps categorize data.
- `partition` cleanly splits based on a condition.
- `fold` and `scan` support complex aggregations.
- `flatten` and `flatMap` simplify working with nested structures.
- These are essential for idiomatic, functional Scala code.

