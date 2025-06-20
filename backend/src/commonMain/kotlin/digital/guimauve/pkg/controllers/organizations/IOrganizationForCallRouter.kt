package digital.guimauve.pkg.controllers.organizations

import dev.kaccelero.models.UUID
import dev.kaccelero.routers.IModelRouter
import digital.guimauve.pkg.models.organizations.CreateOrganizationPayload
import digital.guimauve.pkg.models.organizations.Organization

interface IOrganizationForCallRouter : IModelRouter<Organization, UUID, CreateOrganizationPayload, Unit>
