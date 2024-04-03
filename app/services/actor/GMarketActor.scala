package services.actor

import cats.effect.unsafe.implicits.global
import cats.effect.{IO, IOApp}
import domain.repository.GameDataRepository
import org.apache.pekko.actor.Actor
import services.gmarket.GetGMarket.getGMarket

import javax.inject.{Inject, Singleton}

class GMarketActor @Inject()(
  val gameDataRepository: GameDataRepository
) extends Actor {
  def receive = {
    case "start" =>
      val action = for {
        gmarketList <- getGMarket
        _ <- IO(gameDataRepository.create(gmarketList))
      } yield ()

      action.unsafeRunSync()
  }
}
