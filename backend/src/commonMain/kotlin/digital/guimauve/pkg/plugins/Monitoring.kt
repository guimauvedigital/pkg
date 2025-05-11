package digital.guimauve.pkg.plugins

import dev.kaccelero.plugins.Health
import dev.kaccelero.plugins.KtorSentry
import io.ktor.server.application.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.request.*
import org.slf4j.event.Level

fun Application.configureMonitoring() {
    install(CallLogging) {
        level = Level.INFO
    }
    install(Health)
}
