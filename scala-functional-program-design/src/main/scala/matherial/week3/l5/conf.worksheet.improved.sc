case class Person(name: String)
case class Paper(title: String, authors: List[Person], body: String)

object ConfManagement:
  type Viewers = Set[Person]

  class Conference(ratings: (Paper, Int)*):
    private val realScore = ratings.toMap

    def papers: List[Paper] = ratings.map(_._1).toList

    def score(paper: Paper)(using viewers: Viewers): Int =
      if viewers.intersect(paper.authors.toSet).nonEmpty then - 100
      else realScore(paper)

    def rankings(using viewers: Viewers): List[Paper] = papers.sortBy(score).reverse
    def ask[T](p: Person, query: Viewers => T) = query(Set(p))
    def delegateTo[T](p: Person, query: Viewers => T)(using viewers: Viewers): T = query(viewers + p)
  end Conference
end ConfManagement

import ConfManagement.*

val Smith = Person("Smith")
val Peters = Person("Peters")
val Abel = Person("Abel")
val Black = Person("Black")
val Ed = Person("Ed")

val conf = Conference(
  Paper(
    title = "How to grow beans",
    authors = List(Smith, Peters),
    body = "..."
  ) -> 92,
  Paper(
    title = "Organic gardening",
    authors = List(Abel, Peters),
    body = "..."
  ) -> 83,
  Paper(
    title = "Composting done right",
    authors = List(Black, Smith),
    body = "..."
  ) -> 99,
  Paper(
    title = "The secret life of snails",
    authors = List(Ed),
    body = "..."
  ) -> 77,
)

def highlyRankedProlificAuthors(asking: Person): Set[Person] =
  def query(viewers: Viewers): Set[Person] =
    given Viewers = viewers
    val highlyRanked = conf.rankings.takeWhile(conf.score(_) > 80).toSet
    for
      p1 <- highlyRanked
      p2 <- highlyRanked
      author <- p1.authors
      if p1 != p2 && p2.authors.contains(author)
    yield author
  conf.ask(asking, query)

def testAs(person: Person) =
  highlyRankedProlificAuthors(asking = person)
    .map(_.name)
    .mkString(", ")


testAs(Black)
testAs(Smith)
testAs(Abel)
testAs(Ed)
