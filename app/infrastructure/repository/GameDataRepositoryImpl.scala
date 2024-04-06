package infrastructure.repository

import domain.repository.GameDataRepository
import models.GMarketDT
import utils.DBUtils.{localTx, readOnly}
import scalikejdbc._

import java.time.LocalDateTime
import scala.concurrent.Future

class GameDataRepositoryImpl extends GameDataRepository {
  def create(gameDataList: List[GMarketDT]): Future[_] =
    localTx { implicit session =>
      gameDataList.map { gameData =>
        sql"""INSERT INTO game_data (
             | title,
             | img_src,
             | game_title_id,
             | detail,
             | price,
             | url,
             | category_id,
             | site_id,
             | created_time,
             | updated_time
             | ) VALUES (
             | ${gameData.title},
             | ${gameData.imgSrc},
             | ${gameData.gameTitleId},
             | ${gameData.detail},
             | ${gameData.price},
             | ${gameData.url},
             | ${gameData.categoryId},
             | ${gameData.siteId},
             | ${LocalDateTime.now()},
             | ${LocalDateTime.now()}
             | ) ON DUPLICATE KEY UPDATE
             |  title = VALUES(title),
             |  img_src = VALUES(img_src),
             |  game_title = VALUES(game_title_id),
             |  detail = VALUES(detail),
             |  price = VALUES(price),
             |  category = VALUES(category_id),
             |  site = VALUES(site_id),
             |  updated_time = ${LocalDateTime.now()}
           """.stripMargin
      }.foreach(_.update())
    }

  def get(gameTitle: String): Future[List[GMarketDT]] = {
    readOnly { implicit session =>
      val sql = sql"""SELECT
           | *
           | FROM game_data
           | WHERE game_title = $gameTitle
         """.stripMargin
      sql.map(resultSetToGMarketData).list.apply()
    }
  }

  private[this] def resultSetToGMarketData(rs: WrappedResultSet): GMarketDT =
    GMarketDT(
      rs.string("title"),
      rs.string("img_src"),
      rs.string("game_title_id"),
      rs.string("detail"),
      rs.int("price"),
      rs.string("url"),
      rs.string("category_id"),
      rs.string("site_id")
    )
}
