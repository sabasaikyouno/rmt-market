package services.gmarket

import cats.effect.IO
import cats.syntax.all._
import com.microsoft.playwright.{Locator, Page}
import models.GameTradeDT

import scala.jdk.CollectionConverters._

object GetGameTradeData {
  def getGameTradeData(url: String, page: Page) = {
    val itemEleList = for {
      categoryUrls <- getCategoryUrlList(url, page)
      itemLists <- categoryUrls.traverse(categoryUrl => getItemEleList(categoryUrl, page))
    } yield itemLists.flatten

    itemEleList.flatMap { list =>
      list.filter(_.isVisible).traverse { ele =>
        for {
          title <- IO(ele.locator(".detail h3").innerHTML())
          imgSrc <- IO(getImg(ele))
          gameTitle <- IO(parseGameTitle(ele.locator(".game-image img").getAttribute("alt")))
          detail <- IO(ele.locator(".detail .description p").innerHTML())
          price <- IO(parsePrice(ele.locator(".detail .price .current_price .amount").innerHTML()))
          url <- IO("https://gametrade.jp" + ele.locator(".exhibit-link").getAttribute("href"))
          category <- IO(parseCategory(ele.page().url()))
        } yield GameTradeDT(title,imgSrc, gameTitle, detail, price, url, category)
      }
    }
  }

  private def getImg(ele: Locator) =
    ele.locator(".game-image img").getAttribute("src") match {
      case "https://www.appelsiini.net/projects/lazyload/img/grey.gif" =>
        ele.locator(".game-image img").getAttribute("data-original")
      case imgSrc =>
        imgSrc
    }

  private def getItemEleList(url: String, page: Page) = for {
    _ <- IO(page.navigate(url))
    itemEle <- IO(page.locator("//ul[@class='exhibits clearfix']/li[@data-index]").all().asScala.toList)
  } yield itemEle

  private def getCategoryUrlList(url: String, page: Page) = for {
    _ <- IO(page.navigate(url))
    categoryUrlList <- IO(page.locator(".tabs a").all().asScala.map("https://gametrade.jp" + _.getAttribute("href")).toList)
  } yield categoryUrlList

  private def parseGameTitle(alt: String) = {
    val p = """.*\|(.*)""".r

    p.findFirstMatchIn(alt).map(_.group(1)).get
  }

  private def parsePrice(price: String) = price.replace("¥", "").replace(",", "").toInt

  private def parseCategory(url: String) = url.split('/').last
}

