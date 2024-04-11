package domain.repository

import models.{GMarketDT, GameTitle}

import scala.concurrent.Future

trait GameDataRepository {
  def create(gameDataList: List[GMarketDT]): Future[_]

  def getById(gameTitleId: Int): Future[List[GMarketDT]]

  def getByTitle(gameTitle: String): Future[List[GMarketDT]]

  def getAllGameTitle: Future[List[GameTitle]]
}
