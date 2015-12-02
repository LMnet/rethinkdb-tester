package tester

import java.net.InetSocketAddress

import akka.actor.ActorSystem
import reql.akka.ReqlTcpConnection

import scala.concurrent.Future

object Execute {

  def apply(host: String, port: Int, dbName: String)(query: (DbContext) ⇒ Future[Unit]) = {
    implicit val system = ActorSystem("rethinkdb-tester")

    val dbConnection = {
      val address = new InetSocketAddress(host, port)
      system.actorOf(ReqlTcpConnection.props(address), "db-connection")
    }

    val executor = system.actorOf(Executor.props(dbConnection, dbName: String, query))

    executor ! DbContext.ExecuteQuery
  }
}
