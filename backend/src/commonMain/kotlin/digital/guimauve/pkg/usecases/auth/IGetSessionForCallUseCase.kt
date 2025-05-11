package digital.guimauve.pkg.usecases.auth

import dev.kaccelero.usecases.IUseCase
import digital.guimauve.pkg.models.auth.SessionPayload
import io.ktor.server.application.*

interface IGetSessionForCallUseCase : IUseCase<ApplicationCall, SessionPayload?>
