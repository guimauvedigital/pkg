package digital.guimauve.pkg.controllers.auth

import dev.kaccelero.commons.responses.RedirectResponse
import digital.guimauve.pkg.models.auth.LoginPayload
import io.ktor.server.application.*

class AuthController(

) : IAuthController {

    override fun login() {}

    override suspend fun login(call: ApplicationCall, payload: LoginPayload, redirect: String?): RedirectResponse {
        TODO("Not yet implemented")
    }

    override suspend fun logout(call: ApplicationCall, redirect: String?): RedirectResponse {
        TODO("Not yet implemented")
    }

}
