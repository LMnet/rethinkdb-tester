package tester

object Main extends App {

  Execute("localhost", 28015, "test") { dbContext ⇒
    import dbContext._

    db.tableList.runA.map(println(_))
  }
}
