import com.google.inject.AbstractModule
import domain.repository.GameDataRepository
import infrastructure.repository.GameDataRepositoryImpl
import play.api.libs.concurrent.PekkoGuiceSupport
import play.api.{Configuration, Environment}
import services.actor.GMarketActor
import services.job.ScheduledJobs

class Module(environment: Environment, configuration: Configuration) extends AbstractModule with PekkoGuiceSupport {
  override def configure() = {
    bind(classOf[GameDataRepository]).to(classOf[GameDataRepositoryImpl])
    bindActor[GMarketActor]("gmarketactor")
    bind(classOf[ScheduledJobs]).asEagerSingleton()
  }
}
