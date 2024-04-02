package controllers

import domain.repository.GameDataRepository
import models.GMarketDT

import javax.inject._
import play.api._
import play.api.libs.json._
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class HomeController @Inject()(
  val controllerComponents: ControllerComponents,
  val gameDataRepository: GameDataRepository
) extends BaseController {

  def get(title: String) = Action.async { implicit request =>
    gameDataRepository.get(title).map { list =>
      Ok(Json.toJson(list))
    }
  }
}
