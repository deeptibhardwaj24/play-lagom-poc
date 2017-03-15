package controllers

import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.MessagesApi
import play.api.mvc.{Action, AnyContent, Controller}
import sample.helloworld.api.HelloService
import sample.helloworld.api.model.GreetingMessage

import scala.concurrent.{ExecutionContext, Future}

class HelloController(val messagesApi: MessagesApi, helloService: HelloService)(implicit ec: ExecutionContext) extends Controller {

  val helloUserForm = Form(mapping(
    "name" -> nonEmptyText
  )(HelloUserForm.apply)(HelloUserForm.unapply))

  val helloForm = Form(mapping(
    "name" -> nonEmptyText,
    "message" -> nonEmptyText
  )(HelloForm.apply)(HelloForm.unapply))

  def sayHello(): Action[AnyContent] = Action.async { implicit request =>
    helloUserForm.bindFromRequest.fold(
      badForm => Future {
        BadRequest(" ")
      },
      validForm => {
        for {
          result <- helloService.hello(validForm.name).invoke()
        }
          yield {
            Ok(result)
          }
      }
    )
  }

  def changeMessage(): Action[AnyContent] = Action.async { implicit request =>
    helloForm.bindFromRequest.fold(
      badForm => Future {
        BadRequest(" ")
      },
      validForm => {
        for{
          result <- helloService.useGreeting(validForm.name).invoke(GreetingMessage(validForm.message))
        }
          yield{
            Ok("Message successfully changed !!")
          }
      }
    )
  }
}


