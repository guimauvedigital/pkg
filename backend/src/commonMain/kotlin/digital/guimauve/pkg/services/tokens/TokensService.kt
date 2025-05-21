package digital.guimauve.pkg.services.tokens

import digital.guimauve.pkg.models.auth.LoginPayload
import digital.guimauve.pkg.usecases.auth.ILoginUseCase
import io.ktor.server.auth.*

class TokensService(
    private val loginUseCase: ILoginUseCase,
) : ITokensService {

    override val basicAuthenticationFunction: AuthenticationFunction<UserPasswordCredential> = {
        loginUseCase(LoginPayload(it.name, it.password))?.let { user ->
            UserIdPrincipal(user.id.toString())
        }
    }

    override val bearerAuthenticationFunction: AuthenticationFunction<BearerTokenCredential> = {
        // TODO (for npm)
        //UserIdPrincipal()
        null
    }

}
