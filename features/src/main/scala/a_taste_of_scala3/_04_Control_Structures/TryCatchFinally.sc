import java.io.{IOException, FileWriter}

def writeTextToFile(text: String): Unit =
  // Штучно кидаємо виключення для демонстрації
  if text == "io" then throw new IOException("Disk error")
  else if text == "nfe" then throw new NumberFormatException("Not a number")
  else
    val writer = new FileWriter("output.txt", true)
    writer.write(text + "\n")
    writer.close()

val text = "io"



try
  writeTextToFile(text)
catch
  case ioe: IOException => println("Got an IOException.")
  case nfe: NumberFormatException => println("Got a NumberFormatException.")
finally
  println("Clean up your resources here.")
