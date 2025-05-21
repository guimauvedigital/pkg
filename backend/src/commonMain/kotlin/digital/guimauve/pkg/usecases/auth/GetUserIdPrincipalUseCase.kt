package digital.guimauve.pkg.usecases.auth

import io.ktor.server.application.*
import io.ktor.server.auth.*

class GetUserIdPrincipalUseCase : IGetUserIdPrincipalUseCase {

    override fun invoke(input: ApplicationCall): UserIdPrincipal? = input.principal()

}
