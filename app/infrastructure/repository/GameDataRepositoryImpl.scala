package infrastructure.repository

import domain.repository.GameDataRepository
import models.GMarketDT
import utils.DBUtils.localTx
import scalikejdbc._

import scala.concurrent.Future

class GameDataRepositoryImpl extends GameDataRepository {
  def create(gameDataList: List[GMarketDT]): Future[_] =
    localTx { implicit session =>
      gameDataList.map { gameData =>
        sql"""INSERT INTO game_data (
             | title,
             | img_src,
             | game_title,
             | detail,
             | price,
             | url,
             | category,
             | site
             | ) VALUES (
             | ${gameData.title}
             | ${gameData.imgSrc}
             | ${gameData.gameTitle}
             | ${gameData.detail}
             | ${gameData.price}
             | ${gameData.url}
             | ${gameData.category}
             | ${gameData.site}
             | )
           """.stripMargin
      }.foreach(_.update.apply())
    }
}
