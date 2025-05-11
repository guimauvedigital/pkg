package digital.guimauve.pkg.plugins

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun Application.configureSecurity() {


    authentication {
        jwt("api-jwt") {

        }
    }
}
