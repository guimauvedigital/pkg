package digital.guimauve.pkg.usecases.auth

import dev.kaccelero.usecases.IUseCase
import io.ktor.server.application.*
import io.ktor.server.auth.*

interface IGetUserIdPrincipalUseCase : IUseCase<ApplicationCall, UserIdPrincipal?>
