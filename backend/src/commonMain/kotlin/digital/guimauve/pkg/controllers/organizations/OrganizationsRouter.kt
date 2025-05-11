package digital.guimauve.pkg.controllers.organizations

import dev.kaccelero.models.UUID
import dev.kaccelero.routers.APIModelRouter
import digital.guimauve.pkg.models.organizations.CreateOrganizationPayload
import digital.guimauve.pkg.models.organizations.Organization
import io.ktor.util.reflect.*

class OrganizationsRouter(
    controller: IOrganizationsController,
) : APIModelRouter<Organization, UUID, CreateOrganizationPayload, Unit>(
    typeInfo<Organization>(),
    typeInfo<CreateOrganizationPayload>(),
    typeInfo<Unit>(),
    controller,
    IOrganizationsController::class,
    prefix = "/api/v1"
)
