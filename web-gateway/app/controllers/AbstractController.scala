
package controllers

import javax.inject.Inject

import play.api.i18n.MessagesApi
import play.api.mvc.Controller
import sample.helloworld.api.HelloService

import scala.concurrent.ExecutionContext

abstract class AbstractController @Inject()(messagesApi: MessagesApi, helloService: HelloService)(implicit ec: ExecutionContext) extends Controller {

}







