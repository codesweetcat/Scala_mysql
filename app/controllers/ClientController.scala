package controllers

import java.lang.ProcessBuilder.Redirect
import java.nio.file.Paths

import javax.inject._
import play.api.mvc._

import play.api.libs.json._


import scala.concurrent.{ Future, ExecutionContext }

import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints._
import play.api.i18n._
import models._

@Singleton
class ClientController @Inject()(
                                 repo: UserRepository,
                                 cc: MessagesControllerComponents)
                                (implicit ec: ExecutionContext)
                                extends MessagesAbstractController(cc) with I18nSupport {

  def upload = Action(parse.multipartFormData) { request =>
    request.body.file("picture").map { pic =>
      val filename = Paths.get(pic.filename).getFileName

      pic.ref.moveTo(Paths.get(s"/RA_temp/$filename"),replace = true)
      Ok("File upload")
    }.getOrElse {
      Redirect(routes.HomeController.index).flashing(
        "error" -> "Missing file"
      )
    }
  }


  val userForm :Form[UserForm] = Form(
   //input and validation
    mapping(
      "firstName" -> text.verifying(nonEmpty),
      "lastName" -> text.verifying(nonEmpty)
    )(UserForm.apply)(UserForm.unapply)
  )

  //list all users
  def index1 = Action{ implicit request =>
    // repo.listAllUsers.map { users  => Ok(views.html.allUser(users))}
    Ok(views.html.hello(userForm))
  }

  // insert a new user, list all users
def insertUser = Action.async { implicit request =>
   userForm.bindFromRequest.fold(
   errorForm => {
     Future.successful(Ok(views.html.hello(errorForm)))
   },
   user => {
     //with out id , using new form model.
     repo.insert(user.firstName,user.lastName).map { _ =>
       Redirect(routes.ClientController.index1)
         .flashing("success" -> "user.created")
     }
   }
 )
}

//  def edit(id) = Action.async { implicit request =>
//    repo.findById(id).flatMap {
//      case Some(user) => repo.updateUser(user)
//    }
//  }

  /**
    * A REST endpoint that gets all the people as JSON.
    */
  def getPersons = Action.async { implicit request =>
    repo.listAllUsers.map { users =>
     // Ok(Json.toJson(user))
      Ok(views.html.allUser(users))
    }
  }
}




case class UserForm(firstName: String, lastName: String)



