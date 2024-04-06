package models

import play.api.libs.json.{Json, OWrites, Reads}

case class GMarketDT(
  title: String,
  imgSrc: String,
  gameTitleId: String,
  detail: String,
  price: Int,
  url: String,
  categoryId: String,
  siteId: String
)

object GMarketDT {
  implicit val jsonWrites: OWrites[GMarketDT] = Json.writes[GMarketDT]
  implicit val jsonReads: Reads[GMarketDT] = Json.reads[GMarketDT]
}
