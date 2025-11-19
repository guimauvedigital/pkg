package digital.guimauve.pkg.controllers.packages.versions

import dev.kaccelero.annotations.*
import dev.kaccelero.controllers.IChildModelController
import dev.kaccelero.models.UUID
import digital.guimauve.pkg.models.packages.Package
import digital.guimauve.pkg.models.packages.versions.CreatePackageVersionPayload
import digital.guimauve.pkg.models.packages.versions.PackageVersion
import io.ktor.server.application.*

interface IPackageVersionsController :
    IChildModelController<PackageVersion, UUID, CreatePackageVersionPayload, Unit, Package, UUID> {

    @APIMapping
    @TemplateMapping("public/packages/versions/detail.ftl")
    @GetModelPath
    @DocumentedError(404, "package_versions_not_found")
    suspend fun get(call: ApplicationCall, @ParentModel parent: Package, @Id id: UUID): Map<String, Any>

}
