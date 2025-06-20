package digital.guimauve.pkg.controllers.auth

import dev.kaccelero.commons.exceptions.ControllerException
import dev.kaccelero.commons.responses.RedirectResponse
import digital.guimauve.pkg.models.auth.LoginPayload
import digital.guimauve.pkg.models.auth.SessionPayload
import digital.guimauve.pkg.usecases.auth.IClearSessionForCallUseCase
import digital.guimauve.pkg.usecases.auth.ILoginUseCase
import digital.guimauve.pkg.usecases.auth.ISetSessionForCallUseCase
import io.ktor.http.*
import io.ktor.server.application.*

class AuthController(
    private val loginUseCase: ILoginUseCase,
    private val setSessionForCallUseCase: ISetSessionForCallUseCase,
    private val clearSessionForCallUseCase: IClearSessionForCallUseCase,
) : IAuthController {

    override fun login() {}

    override suspend fun login(call: ApplicationCall, payload: LoginPayload, redirect: String?): RedirectResponse {
        val user = loginUseCase(payload)
            ?: throw ControllerException(HttpStatusCode.Unauthorized, "auth_invalid_credentials")
        setSessionForCallUseCase(call, SessionPayload(user.id))
        return RedirectResponse(redirect ?: "/")
    }

    override suspend fun logout(call: ApplicationCall, redirect: String?): RedirectResponse {
        clearSessionForCallUseCase(call)
        return RedirectResponse(redirect ?: "/")
    }

}
