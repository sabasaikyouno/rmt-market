package domain.repository

import models.{GMarketDT, GameTitle}

import scala.concurrent.Future

trait GameDataRepository {
  def create(gameDataList: List[GMarketDT]): Future[_]

  def get(gameTitleId: Int): Future[List[GMarketDT]]

  def getAllGameTitle: Future[List[GameTitle]]
}
