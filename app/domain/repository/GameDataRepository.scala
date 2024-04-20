package domain.repository

import models.{GMarketDT, GameTitleData}

import scala.concurrent.Future

trait GameDataRepository {
  def create(gameDataList: List[GMarketDT]): Future[_]

  def getById(gameTitleId: Int): Future[List[GMarketDT]]

  def getByTitle(gameTitle: String, page: Int, category: String, search: Option[String]): Future[List[GMarketDT]]

  def getGameDataSize(gameTitle: String, category: String, search: Option[String]): Future[Option[Int]]

  def getAllGameTitleData: Future[List[GameTitleData]]

  def getCategory(gameTitle: String): Future[List[String]]
}
