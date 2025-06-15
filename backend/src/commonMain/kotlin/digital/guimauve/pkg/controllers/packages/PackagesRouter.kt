package digital.guimauve.pkg.controllers.packages

import dev.kaccelero.models.UUID
import dev.kaccelero.routers.APIChildModelRouter
import digital.guimauve.pkg.controllers.organizations.OrganizationsRouter
import digital.guimauve.pkg.models.organizations.Organization
import digital.guimauve.pkg.models.packages.CreatePackagePayload
import digital.guimauve.pkg.models.packages.Package
import digital.guimauve.pkg.models.packages.UpdatePackagePayload
import io.ktor.util.reflect.*

class PackagesRouter(
    controller: IPackagesController,
    organizationsRouter: OrganizationsRouter,
) : APIChildModelRouter<Package, UUID, CreatePackagePayload, UpdatePackagePayload, Organization, UUID>(
    typeInfo<Package>(),
    typeInfo<CreatePackagePayload>(),
    typeInfo<UpdatePackagePayload>(),
    controller,
    IPackagesController::class,
    organizationsRouter,
    prefix = "/api/v1"
)
