package models

import play.api.libs.json.{Json, OWrites, Reads}

case class GMarketDT(
  title: String,
  imgSrc: String,
  gameTitleId: Int,
  detail: String,
  price: Int,
  url: String,
  categoryId: Int,
  siteId: Int
)

object GMarketDT {
  implicit val jsonWrites: OWrites[GMarketDT] = Json.writes[GMarketDT]
  implicit val jsonReads: Reads[GMarketDT] = Json.reads[GMarketDT]
}
