package models

import play.api.libs.json.{Json, OWrites, Reads}

case class GameTitle(gameTitleId: Int, gameTitle: String)

object GameTitle {
  implicit val jsonWrites: OWrites[GameTitle] = Json.writes[GameTitle]
  implicit val jsonReads: Reads[GameTitle] = Json.reads[GameTitle]
}
