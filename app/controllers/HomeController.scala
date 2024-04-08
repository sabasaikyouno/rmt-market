package controllers

import domain.repository.GameDataRepository
import models.{GMarketDT, GameTitle}

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
  def get(gameTitleId: Int) = Action.async { implicit request =>
    gameDataRepository.get(gameTitleId).map { list =>
      Ok(Json.toJson(list))
    }
  }

  def getAllGameTitle() = Action.async { implicit request =>
    gameDataRepository.getAllGameTitle.map { list =>
      Ok(Json.toJson(list))
    }
  }
}
