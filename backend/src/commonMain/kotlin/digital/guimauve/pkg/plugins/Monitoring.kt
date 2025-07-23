package digital.guimauve.pkg.plugins

import dev.kaccelero.plugins.Health
import io.ktor.server.application.*
import io.ktor.server.plugins.calllogging.*
import io.ktor.server.request.*
import org.slf4j.event.Level

fun Application.configureMonitoring() {
    install(CallLogging) {
        level = Level.INFO
        filter { call ->
            call.request.path() != "/healthz" && call.request.path() != "/readyz"
        }
    }
    install(Health)
}
