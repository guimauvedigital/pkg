package digital.guimauve.pkg.usecases.auth

import dev.kaccelero.usecases.IUseCase
import io.ktor.server.application.*

interface IClearSessionForCallUseCase : IUseCase<ApplicationCall, Unit>
