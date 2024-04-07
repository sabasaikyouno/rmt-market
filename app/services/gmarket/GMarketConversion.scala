package services.gmarket

import models.{GMarketDT, GameClubDT, GameTradeDT, RmtClubDT}

/**
 * category id
 * 1はアカウント 2はアイテム 3は代行 4はその他
 * site id
 * 1はRmtClub 2はGameClub 3はGameTrade
 */
object GMarketConversion {
  implicit class ConvertOps[A: Conversion](a: A) {
    def toDt(gameTitleId: Int) = {
      implicitly[Conversion[A]].toGMarket(a, gameTitleId)
    }
  }

  implicit val rmtClubDT: Conversion[RmtClubDT] = new Conversion[RmtClubDT] {
    def toGMarket(dt: RmtClubDT, gameTitleId: Int) = GMarketDT(
      dt.title,
      dt.imgSrc,
      gameTitleId,
      dt.detail,
      dt.price,
      dt.url,
      toGMarketCategory(dt.category),
      1
    )

    def toGMarketCategory: String => Int = {
      case "1" => 1
      case "2" => 2
      case "4" => 3
    }
  }

  implicit val gameClubDT: Conversion[GameClubDT] = new Conversion[GameClubDT] {
    def toGMarket(dt: GameClubDT, gameTitleId: Int) = GMarketDT(
      dt.title,
      dt.imgSrc,
      gameTitleId,
      dt.detail,
      dt.price,
      dt.url,
      toGMarketCategory(dt.category),
      2
    )

    def toGMarketCategory: String => Int = {
      case "引退垢" => 1
      case "リセマラ・初期垢" => 1
      case "アイテム・通貨" => 2
      case "代行" => 3
      case _ => 4
    }
  }

  implicit val gameTradeDT: Conversion[GameTradeDT] = new Conversion[GameTradeDT] {
    def toGMarket(dt: GameTradeDT, gameTitleId: Int) = GMarketDT(
      dt.title,
      dt.imgSrc,
      gameTitleId,
      dt.detail,
      dt.price,
      dt.url,
      toGMarketCategory(dt.category),
      3
    )
    def toGMarketCategory: String => Int = {
      case "exhibits" => 1
      case "golds" => 2
      case "items" => 2
      case "agencies" => 3
      case _ => 4
    }
  }
}


