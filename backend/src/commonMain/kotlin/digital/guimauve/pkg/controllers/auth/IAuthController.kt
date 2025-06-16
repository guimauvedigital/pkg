package digital.guimauve.pkg.controllers.auth

import dev.kaccelero.annotations.Path
import dev.kaccelero.annotations.Payload
import dev.kaccelero.annotations.QueryParameter
import dev.kaccelero.annotations.TemplateMapping
import dev.kaccelero.commons.responses.RedirectResponse
import dev.kaccelero.controllers.IUnitController
import digital.guimauve.pkg.models.auth.LoginPayload
import io.ktor.server.application.*

interface IAuthController : IUnitController {

    @TemplateMapping("public/auth/login.ftl")
    @Path("GET", "/login")
    fun login()

    @TemplateMapping("public/auth/login.ftl")
    @Path("POST", "/login")
    suspend fun login(
        call: ApplicationCall,
        @Payload payload: LoginPayload,
        @QueryParameter redirect: String?,
    ): RedirectResponse

    @TemplateMapping("public/auth/login.ftl")
    @Path("GET", "/logout")
    suspend fun logout(
        call: ApplicationCall,
        @QueryParameter redirect: String?,
    ): RedirectResponse

}
