package digital.guimauve.pkg.plugins

import dev.kaccelero.routers.createRoutes
import dev.kaccelero.routers.info
import digital.guimauve.pkg.controllers.organizations.OrganizationsRouter
import digital.guimauve.pkg.controllers.packages.maven.MavenRouter
import digital.guimauve.pkg.controllers.packages.npm.NpmRouter
import digital.guimauve.pkg.controllers.packages.pypi.PyPiRouter
import digital.guimauve.pkg.controllers.users.UsersRouter
import digital.guimauve.pkg.models.application.PkgEnvironment
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.routing.*
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.servers.Server
import org.koin.ktor.ext.get

fun Application.configureRouting() {
    install(IgnoreTrailingSlash)
    install(CORS) {
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Patch)
        allowHeader(HttpHeaders.ContentType)
        allowHeader(HttpHeaders.Authorization)
        anyHost()
    }
    routing {
        val openAPI = OpenAPI().info {
            this.title = "PKG API"
            this.description = "PKG API"
            this.version = "1.0.0"
        }
        openAPI.servers(
            listOf(
                Server().description("Production server").url(PkgEnvironment.PRODUCTION.baseUrl),
            )
        )

        authenticate("api-jwt", optional = true) {
            listOf(
                get<OrganizationsRouter>(),
                get<UsersRouter>(),
                get<MavenRouter>(),
                get<NpmRouter>(),
                get<PyPiRouter>(),
            ).forEach {
                it.createRoutes(this) //, openAPI)
            }
        }
        //OpenAPIRouter().createRoutes(this, openAPI)

        staticResources("", "static")
    }
}
