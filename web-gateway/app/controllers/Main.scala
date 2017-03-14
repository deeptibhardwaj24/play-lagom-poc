package controllers

import javax.inject.Inject

import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc.{Action, AnyContent}
import sample.helloworld.api.HelloService

import scala.concurrent.{ExecutionContext, Future}

class Main @Inject()(val messagesApi: MessagesApi, helloService: HelloService)(implicit ec: ExecutionContext)extends AbstractController(messagesApi, helloService) with I18nSupport {

  val helloUserForm = Form(mapping(
    "name" -> nonEmptyText
  )(HelloUserForm.apply)(HelloUserForm.unapply))

  def index: Action[AnyContent] = Action.async{ implicit request =>
    Future(Ok(views.html.index(helloUserForm)))
  }
}

case class HelloUserForm(name: String)