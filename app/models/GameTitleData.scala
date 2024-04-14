package models

import play.api.libs.json.{Json, OWrites, Reads}

case class GameTitleData(gameTitleId: Int, gameTitle: String, gameImg: String)

object GameTitleData {
  implicit val jsonWrites: OWrites[GameTitleData] = Json.writes[GameTitleData]
  implicit val jsonReads: Reads[GameTitleData] = Json.reads[GameTitleData]
}
