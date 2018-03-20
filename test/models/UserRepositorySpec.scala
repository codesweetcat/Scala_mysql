package models





import org.scalatest.concurrent.ScalaFutures
import org.scalatestplus.play._
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import org.scalatest.{AsyncFlatSpec, BeforeAndAfterAll}

import monix.execution.Scheduler.Implicits.global

// ***** NOTE: Do not remove this import! It won't compile without this
import monix.cats._
// *****

/**
  * test the user  UserRepository
  */


class UserRepositorySpec extends PlaySpec with GuiceOneAppPerSuite with ScalaFutures {

  import models.User
  import models.UserRepository

  import org.scalatest.{AsyncFlatSpec, BeforeAndAfterAll}
  import monix.execution.Scheduler.Implicits.global

  import scala.concurrent.Await
 // import scala.util.{Failure, Success, Try}
  import scala.concurrent.duration._

  // ***** NOTE: Do not remove this import! It won't compile without this
  import monix.cats._
  // *****
  def userService: UserRepository = app.injector.instanceOf(classOf[UserRepository])


  "be listed along its users" in {
    whenReady(userService.listAllUsers) { users =>
      users.length must equal(3)
    }
  }

  "find user by ID" in {
    whenReady(userService.findById(1)) {
      case Some(user) =>
        assert(user.firstName === "minghao")
        assert(user.lastName === "wong")
      case _ =>
        fail("expected the user with id 1 but was not found in the database")
    }
  }

    "delete user by ID" in {
      userService.delete(4)
        whenReady(userService.listAllUsers) { users=>
          users.length must equal(3)
        }
      }


"update user by User" in {

  userService.updateUser(1,"minghao","wong")
  whenReady(userService.findById(1)) {
    case Some(user) =>
      assert(user.firstName === "minghao" )
    case _ =>
      fail("expected the user with id 1 but was not found in the database")
  }
}

  }



