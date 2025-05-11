package digital.guimauve.pkg.controllers.users

import dev.kaccelero.models.UUID
import dev.kaccelero.routers.APIChildModelRouter
import digital.guimauve.pkg.controllers.organizations.OrganizationsRouter
import digital.guimauve.pkg.models.organizations.Organization
import digital.guimauve.pkg.models.users.CreateUserPayload
import digital.guimauve.pkg.models.users.User
import io.ktor.util.reflect.*

class UsersRouter(
    controller: IUsersController,
    organizationsRouter: OrganizationsRouter,
) : APIChildModelRouter<User, UUID, CreateUserPayload, Unit, Organization, UUID>(
    typeInfo<User>(),
    typeInfo<CreateUserPayload>(),
    typeInfo<Unit>(),
    controller,
    IUsersController::class,
    organizationsRouter,
    prefix = "/api/v1"
)

