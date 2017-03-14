package controllers

import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.MessagesApi
import play.api.mvc.{Action, Controller}
import sample.helloworld.api.HelloService

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by deepti on 9/3/17.
  */
class HelloController(val messagesApi: MessagesApi, helloService: HelloService)(implicit ec: ExecutionContext) extends Controller {

  val helloUserForm1 = Form(mapping(
    "name" -> nonEmptyText
  )(HelloUserForm.apply)(HelloUserForm.unapply))

  def sayHello()= Action.async{ implicit request =>
    helloUserForm1.bindFromRequest.fold(
      badForm => Future {BadRequest(" ")},
      validForm => {
        println("---------------"+validForm.name)
        val userName = validForm.name
        for {
          result <- helloService.hello(userName).invoke()
        }
          yield {
            Ok("-----------hello!!!------"+result)
          }
      }
    )



  }
}

