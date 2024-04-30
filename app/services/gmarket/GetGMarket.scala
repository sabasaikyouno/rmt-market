package services.gmarket

import cats.effect.IO
import cats.syntax.all._
import com.microsoft.playwright._
import services.gmarket.GMarketConversion._
import services.gmarket.GameUrls.getGameUrls
import services.gmarket.GetGameClubData.getGameClubData
import services.gmarket.GetGameTradeData.getGameTradeData
import services.gmarket.GetRmtClubData.getRmtClubData

import java.time.Duration
import scala.jdk.CollectionConverters._

object GetGMarket {
  def getGMarket = for {
    playwright <- IO(Playwright.create())
    browser <- IO(playwright.chromium().launch(new BrowserType.LaunchOptions().setArgs(List("--blink-settings=imagesEnabled=false", "--disable-remote-fonts").asJava)))
    page <- IO(browser.newContext(new Browser.NewContextOptions().setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:109.0) Gecko/20100101 Firefox/109.0")).newPage())
    _ <- IO(page.setDefaultNavigationTimeout(10000))
    urls = getGameUrls(page)
    gMarketDtList <- urls.traverse { case (gameTitleId, urls) =>
      urls match {
        case List(gameTradeUrl, gameClubUrl, rmtClubUrl) => for {
          gameTradeDtList <- getGameTradeData(gameTradeUrl, page).handleErrorWith(_ => IO.println("gameTrade error") *> IO.pure(Nil))
          gameClubDtList <- getGameClubData(gameClubUrl, page).handleErrorWith(_ => IO.println("gameClub error") *> IO.pure(Nil))
          rmtClubDtList <- getRmtClubData(rmtClubUrl, page).handleErrorWith(_ => IO.println("rmtClub error") *> IO.pure(Nil))
        } yield gameTradeDtList.map(_.toDt(gameTitleId)) ::: gameClubDtList.map(_.toDt(gameTitleId)) ::: rmtClubDtList.map(_.toDt(gameTitleId))
      }
    }
    _ <- IO(playwright.close())
  } yield gMarketDtList.flatten
}
