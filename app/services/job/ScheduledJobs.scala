package services.job

import domain.repository.GameDataRepository
import org.apache.pekko.actor.{ActorRef, ActorSystem, Props}
import play.api.libs.json.Json
import services.actor.GMarketActor

import javax.inject.{Inject, Named, Singleton}
import scala.concurrent.ExecutionContext
import scala.concurrent.duration.DurationInt
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class ScheduledJobs @Inject()(
  @Named("gmarketactor") gmarketActor: ActorRef
) {
  val actorSystem = ActorSystem("GMarketSystem")
  actorSystem.scheduler.scheduleOnce(0.seconds, gmarketActor, "start")
}
