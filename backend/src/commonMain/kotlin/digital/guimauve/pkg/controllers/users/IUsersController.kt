package digital.guimauve.pkg.controllers.users

import dev.kaccelero.annotations.APIMapping
import dev.kaccelero.annotations.ListModelPath
import dev.kaccelero.annotations.ParentModel
import dev.kaccelero.controllers.IChildModelController
import dev.kaccelero.models.UUID
import digital.guimauve.pkg.models.organizations.Organization
import digital.guimauve.pkg.models.users.CreateUserPayload
import digital.guimauve.pkg.models.users.User
import io.ktor.server.application.*

interface IUsersController : IChildModelController<User, UUID, CreateUserPayload, Unit, Organization, UUID> {

    @APIMapping
    @ListModelPath
    suspend fun list(call: ApplicationCall, @ParentModel parent: Organization): List<User>

}
