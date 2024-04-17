package controllers

import domain.repository.GameDataRepository
import models.{GMarketDT, GameTitleData}

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
  def getGameDataListById(gameTitleId: Int) = Action.async { implicit request =>
    gameDataRepository.getById(gameTitleId).map { list =>
      Ok(Json.toJson(list))
    }
  }

  def getGameDataListByTitle(gameTitle: String, page:Int, category: String) = Action.async { implicit request =>
    gameDataRepository.getByTitle(gameTitle, page, category).map { list =>
      Ok(Json.toJson(list))
    }
  }

  def getGameDataPage(gameTitle: String) = Action.async { implicit request =>
    gameDataRepository.getGameDataSize(gameTitle).map( size =>
      Ok(Json.toJson(scala.math.ceil(size.getOrElse(0) / 22.0).toInt))
    )
  }

  def getAllGameTitleData() = Action.async { implicit request =>
    gameDataRepository.getAllGameTitleData.map { list =>
      Ok(Json.toJson(list))
    }
  }

  def getSearchOptions() = Action.async { implicit request =>
    gameDataRepository.getAllGameTitleData.map { list =>
      Ok(Json.toJson(list.map(data => Json.obj("label" -> data.gameTitle))))
    }
  }

  def getCategory(gameTitle: String) = Action.async { implicit request =>
    gameDataRepository.getCategory(gameTitle).map { list =>
      Ok(Json.toJson(list))
    }
  }
}
