package digital.guimauve.pkg.services.jwt

import com.auth0.jwt.interfaces.DecodedJWT
import com.auth0.jwt.interfaces.JWTVerifier
import dev.kaccelero.models.UUID
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

interface IJWTService {

    val verifier: JWTVerifier
    val authenticationFunction: AuthenticationFunction<JWTCredential>
    val challenge: JWTAuthChallengeFunction

    fun generateJWT(userId: UUID, type: String): String
    fun verifyJWT(token: String): DecodedJWT?

}
