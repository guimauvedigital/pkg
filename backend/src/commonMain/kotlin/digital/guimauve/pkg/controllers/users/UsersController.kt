package digital.guimauve.pkg.controllers.users

import dev.kaccelero.commons.exceptions.ControllerException
import dev.kaccelero.commons.repositories.IGetChildModelSuspendUseCase
import dev.kaccelero.commons.repositories.IListChildModelSuspendUseCase
import dev.kaccelero.models.UUID
import digital.guimauve.pkg.models.organizations.Organization
import digital.guimauve.pkg.models.users.User
import io.ktor.http.*
import io.ktor.server.application.*

class UsersController(
    private val listUsersUseCase: IListChildModelSuspendUseCase<User, UUID>,
    private val getUserUseCase: IGetChildModelSuspendUseCase<User, UUID, UUID>,
) : IUsersController {

    override suspend fun list(call: ApplicationCall, parent: Organization): List<User> {
        return listUsersUseCase(parent.id)
    }

    override suspend fun get(call: ApplicationCall, parent: Organization, id: UUID): Map<String, Any> {
        val user = getUserUseCase(id, parent.id)
            ?: throw ControllerException(HttpStatusCode.NotFound, "users_not_found")
        if (user.organizationId != parent.id)
            throw ControllerException(HttpStatusCode.Forbidden, "users_not_allowed")
        return mapOf(
            "item" to user,
            "organization" to parent
        )
    }

}
