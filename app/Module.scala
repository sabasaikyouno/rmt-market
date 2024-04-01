import com.google.inject.AbstractModule
import domain.repository.GameDataRepository
import infrastructure.repository.GameDataRepositoryImpl
import play.api.{Configuration, Environment}

class Module(environment: Environment, configuration: Configuration) extends AbstractModule {
  override def configure() = {
    bind(classOf[GameDataRepository]).to(classOf[GameDataRepositoryImpl])
  }
}
