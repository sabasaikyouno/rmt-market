package infrastructure.repository

import domain.repository.GameDataRepository
import models.{GMarketDT, GameTitleData}
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
             | game_title_data_id,
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
             | ${gameData.gameTitleDataId},
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
             |  game_title_data_id = VALUES(game_title_data_id),
             |  detail = VALUES(detail),
             |  price = VALUES(price),
             |  category_id = VALUES(category_id),
             |  site_id = VALUES(site_id),
             |  updated_time = ${LocalDateTime.now()}
           """.stripMargin
      }.foreach(_.update())
    }

  def getById(gameTitleId: Int): Future[List[GMarketDT]] = {
    readOnly { implicit session =>
      val sql = sql"""SELECT
           | *
           | FROM game_data
           | WHERE game_title_data_id = $gameTitleId
         """.stripMargin
      sql.map(resultSetToGMarketData).list.apply()
    }
  }

  def getByTitle(gameTitle: String, page: Int): Future[List[GMarketDT]] = {
    readOnly { implicit session =>
      val sql =
        sql"""SELECT
          | game_data.*
          | FROM game_data
          | INNER JOIN game_title_data ON game_title_data.game_title_id = game_data.game_title_data_id
          | WHERE game_title_data.game_title = $gameTitle
          | ORDER BY updated_time DESC
          | LIMIT ${(page - 1).max(0) * 21}, 21
           """.stripMargin
      sql.map(resultSetToGMarketData).list.apply()
    }
  }

  def getGameDataSize(gameTitle: String): Future[Option[Int]] = {
    readOnly { implicit session =>
      val sql =
        sql"""SELECT
             | COUNT(*)
             | FROM game_data
             | INNER JOIN game_title_data ON game_title_data.game_title_id = game_data.game_title_data_id
             | WHERE game_title_data.game_title = $gameTitle
           """.stripMargin
      sql.map(_.int("COUNT(*)")).single.apply()
    }
  }

  def getAllGameTitleData: Future[List[GameTitleData]] = readOnly { implicit session =>
    val sql =
      sql"""SELECT
        | *
        | FROM game_title_data
         """.stripMargin
    sql.map(resultSetToGameTitleData).list.apply()
  }

  def getCategory(gameTitle: String): Future[List[String]] = readOnly { implicit session =>
    val sql =
      sql"""SELECT
           | DISTINCT category.category
           | FROM game_data
           | INNER JOIN category ON category.category_id = game_data.category_id
           | INNER JOIN game_title_data ON game_title_data.game_title_id = game_data.game_title_data_id
           | WHERE game_title_data.game_title = $gameTitle
         """.stripMargin
    sql.map(_.string("category")).list.apply()
  }

  private[this] def resultSetToGMarketData(rs: WrappedResultSet): GMarketDT =
    GMarketDT(
      rs.string("title"),
      rs.string("img_src"),
      rs.int("game_title_data_id"),
      rs.string("detail"),
      rs.int("price"),
      rs.string("url"),
      rs.int("category_id"),
      rs.int("site_id")
    )

  private[this] def resultSetToGameTitleData(rs: WrappedResultSet): GameTitleData =
    GameTitleData(
      rs.int("game_title_id"),
      rs.string("game_title"),
      rs.string("game_img")
    )
}
