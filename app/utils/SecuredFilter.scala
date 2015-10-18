package utils

import com.google.inject.Inject
import com.mohiva.play.silhouette.api.{Environment, Silhouette}
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import models.User
import play.api.i18n.MessagesApi
import play.api.mvc.{Filter, RequestHeader, Result}

import scala.concurrent.Future

class SecuredFilter @Inject()(
  implicit val env: Environment[User, CookieAuthenticator],
  val messagesApi: MessagesApi)
  extends Filter with Silhouette[User, CookieAuthenticator] {

  override def apply(next: RequestHeader => Future[Result])(
    request: RequestHeader): Future[Result] = {

    val action = UserAwareAction.async { r =>
      request.path match {
        case "/admin" if r.identity.isEmpty => Future.successful(Unauthorized)
        case _ => next(request)
      }
    }

    action(request).run
  }
}