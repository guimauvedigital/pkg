package digital.guimauve.pkg.controllers.packages

import dev.kaccelero.annotations.APIMapping
import dev.kaccelero.annotations.ListModelPath
import dev.kaccelero.annotations.ParentModel
import dev.kaccelero.annotations.TemplateMapping
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

}
