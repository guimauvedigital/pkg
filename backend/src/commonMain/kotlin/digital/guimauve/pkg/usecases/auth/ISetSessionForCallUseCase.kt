package digital.guimauve.pkg.usecases.auth

import dev.kaccelero.usecases.IPairUseCase
import digital.guimauve.pkg.models.auth.SessionPayload
import io.ktor.server.application.*

interface ISetSessionForCallUseCase : IPairUseCase<ApplicationCall, SessionPayload, Unit>
