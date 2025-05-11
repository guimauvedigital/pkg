package digital.guimauve.pkg.usecases.users

import dev.kaccelero.commons.auth.IGetJWTPrincipalForCallUseCase
import dev.kaccelero.commons.users.IGetUserForCallUseCase
import dev.kaccelero.models.IUser
import dev.kaccelero.models.UUID
import digital.guimauve.pkg.models.users.User
import digital.guimauve.pkg.usecases.auth.IGetSessionForCallUseCase
import io.ktor.server.application.*
import io.ktor.util.*

class GetUserForCallUseCase(
    private val getJWTPrincipalForCall: IGetJWTPrincipalForCallUseCase,
    private val getSessionForCallUseCase: IGetSessionForCallUseCase,
    private val getUserUseCase: IGetUserUseCase,
) : IGetUserForCallUseCase {

    private data class UserForCall(
        val user: User?,
    )

    private val userKey = AttributeKey<UserForCall>("user")

    override suspend fun invoke(input: ApplicationCall): IUser? {
        // Note: we cannot use `computeIfAbsent` because it does not support suspending functions
        return input.attributes.getOrNull(userKey)?.user ?: run {
            val id = getJWTPrincipalForCall(input)?.subject?.let(::UUID) ?: getSessionForCallUseCase(input)?.userId
            val computed = UserForCall(id?.let { getUserUseCase(it) })
            input.attributes.put(userKey, computed)
            computed.user
        }
    }

}
