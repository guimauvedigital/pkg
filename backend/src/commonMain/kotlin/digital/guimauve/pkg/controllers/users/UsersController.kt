package digital.guimauve.pkg.controllers.users

import dev.kaccelero.commons.repositories.IListChildModelSuspendUseCase
import dev.kaccelero.models.UUID
import digital.guimauve.pkg.models.organizations.Organization
import digital.guimauve.pkg.models.users.User
import io.ktor.server.application.*

class UsersController(
    private val listUsersUseCase: IListChildModelSuspendUseCase<User, UUID>,
) : IUsersController {

    override suspend fun list(call: ApplicationCall, parent: Organization): List<User> {
        return listUsersUseCase(parent.id)
    }

}
