package models





import org.scalatest.concurrent.ScalaFutures
import org.scalatestplus.play._
import org.scalatestplus.play.guice.GuiceOneAppPerSuite

/**
  * test the user haoming database
  */


class UserRepositorySpec extends PlaySpec with GuiceOneAppPerSuite with ScalaFutures {

  import models.User
  import models.UserRepository

  import scala.concurrent.ExecutionContext.Implicits.global


  def userService: UserRepository = app.injector.instanceOf(classOf[UserRepository])


  "be listed along its users" in {
    whenReady(userService.listAllUsers) { users =>
      users.length must equal(4)
    }
  }



}