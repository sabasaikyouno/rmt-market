package models

import play.api.libs.json.{Json, OWrites, Reads}

case class GMarketDT(
  title: String,
  imgSrc: String,
  gameTitle: String,
  detail: String,
  price: Int,
  url: String,
  category: String,
  site: String
)

object GMarketDT {
  implicit val jsonWrites: OWrites[GMarketDT] = Json.writes[GMarketDT]
  implicit val jsonReads: Reads[GMarketDT] = Json.reads[GMarketDT]
}
