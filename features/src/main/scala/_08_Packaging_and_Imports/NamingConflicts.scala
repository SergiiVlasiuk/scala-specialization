package _08_Packaging_and_Imports

import java.util.{Date => UtilDate}
import java.sql.{Date => SqlDate}

object NamingConflicts:
  val d1: UtilDate = new UtilDate()
  val d2: SqlDate = SqlDate.valueOf("2024-01-01")
