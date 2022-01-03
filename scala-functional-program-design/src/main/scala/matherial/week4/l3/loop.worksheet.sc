def repeatUntil(command: => Unit)(condition: => Boolean): Unit =
  command
  if !condition then repeatUntil(command)(condition)
  else ()

var x = 0
var y = 2

repeatUntil {
  x = x + 1
  y = y * 2
} (x == 5)
y



class Until(body: => Unit):
  infix def until(cond: => Boolean): Unit =
    if !cond then
      body
      until(cond)

def repeat(body: => Unit) = Until(body)

x = 0
y = 2
repeat {
  x = x + 1
  y = y * 2
} until(x == 5)
y


for
  i <- 1 until 3
  j <- "abc"
do println(s"$i $j")
// translates to
(1 until 3).foreach(i => "abc".foreach(j => println(s"$i $j")))

