package models




import javax.inject._

import play.api.Play
import play.api.Application
import play.api.data.Form
import play.api.data.Forms._
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider

import slick.backend.DatabaseConfig
import slick.jdbc.JdbcProfile
import slick.driver.MySQLDriver.api._

import scala.concurrent.{ ExecutionContext, Future }
import monix.eval.Task

import play.api.libs.json._

import models.User


//use default DB
@javax.inject.Singleton
class UserRepository @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider,
  )(implicit ec: ExecutionContext)
  extends  HasDatabaseConfigProvider[JdbcProfile] {


  import profile.api._

  private val UsersSQL = TableQuery[UsersTable]

  //list of all users
  def listAllUsers: Future[Seq[User]] = {
    db.run(UsersSQL.result)
  }

  //add a new user
  def insert(firstName: String, lastName:String): Future[User] = db.run {
    (UsersSQL.map(p=>(p.firstName,p.lastName))
      returning UsersSQL.map(_.id)
      into((fullName,id) => User(id,fullName._1,fullName._2))
      )+=(firstName,lastName)
  }

  //find user by ID
  def findById(id: Long): Future[Option[User]] =  db.run ( UsersSQL.filter(_.id === id).result.headOption)
//delete user
  def delete(userId:Long): Future[Unit] = {
    db.run( UsersSQL.filter(_.id === userId ).delete).map(_=>())
    }
  //update user
  def updateUser(userId:Long, fName: String, lName: String):Future[Unit] = {
    db.run(UsersSQL.filter(_.id === userId).map(r => (r.firstName,r.lastName)).update(fName,lName)).map(_=>())
  }


  //define the table it will have a name of people
  private  class UsersTable(tag:Tag) extends  Table[User](tag, "user1") {

    def id = column[Long]("id", O.PrimaryKey,O.AutoInc)
    def firstName = column[String]("first_name")
    def lastName = column[String]("last_name")

    def * = (id, firstName, lastName) <> ((User.apply _).tupled,User.unapply)
  }




}
