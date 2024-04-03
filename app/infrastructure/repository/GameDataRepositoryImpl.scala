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
             | game_title,
             | detail,
             | price,
             | url,
             | category,
             | site,
             | created_time,
             | updated_time
             | ) VALUES (
             | ${gameData.title},
             | ${gameData.imgSrc},
             | ${gameData.gameTitle},
             | ${gameData.detail},
             | ${gameData.price},
             | ${gameData.url},
             | ${gameData.category},
             | ${gameData.site},
             | ${LocalDateTime.now()},
             | ${LocalDateTime.now()}
             | ) ON DUPLICATE KEY UPDATE
             |  title = VALUES(title),
             |  img_src = VALUES(img_src),
             |  game_title = VALUES(game_title),
             |  detail = VALUES(detail),
             |  price = VALUES(price),
             |  category = VALUES(category),
             |  site = VALUES(site),
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
      rs.string("game_title"),
      rs.string("detail"),
      rs.int("price"),
      rs.string("url"),
      rs.string("category"),
      rs.string("site")
    )
}
