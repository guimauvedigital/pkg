package digital.guimauve.pkg.services.tokens

import io.ktor.server.auth.*

interface ITokensService {

    val basicAuthenticationFunction: AuthenticationFunction<UserPasswordCredential>
    val bearerAuthenticationFunction: AuthenticationFunction<BearerTokenCredential>

}
