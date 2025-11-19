package digital.guimauve.pkg.controllers.packages

import dev.kaccelero.annotations.*
import dev.kaccelero.controllers.IChildModelController
import dev.kaccelero.models.UUID
import digital.guimauve.pkg.models.organizations.Organization
import digital.guimauve.pkg.models.packages.CreatePackagePayload
import digital.guimauve.pkg.models.packages.Package
import digital.guimauve.pkg.models.packages.UpdatePackagePayload
import io.ktor.server.application.*

interface IPackagesController :
    IChildModelController<Package, UUID, CreatePackagePayload, UpdatePackagePayload, Organization, UUID> {

    @APIMapping
    @TemplateMapping("public/packages/list.ftl")
    @ListModelPath
    suspend fun list(call: ApplicationCall, @ParentModel parent: Organization): List<Package>

    @APIMapping
    @TemplateMapping("public/packages/detail.ftl")
    @GetModelPath
    @DocumentedError(404, "packages_not_found")
    suspend fun get(call: ApplicationCall, @ParentModel parent: Organization, @Id id: UUID): Map<String, Any>

}
