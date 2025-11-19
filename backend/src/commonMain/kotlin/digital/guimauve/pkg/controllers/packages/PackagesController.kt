package digital.guimauve.pkg.controllers.packages

import dev.kaccelero.commons.exceptions.ControllerException
import dev.kaccelero.commons.repositories.IGetChildModelSuspendUseCase
import dev.kaccelero.commons.repositories.IListChildModelSuspendUseCase
import dev.kaccelero.models.UUID
import digital.guimauve.pkg.models.organizations.Organization
import digital.guimauve.pkg.models.packages.Package
import digital.guimauve.pkg.models.packages.versions.PackageVersion
import io.ktor.http.*
import io.ktor.server.application.*

class PackagesController(
    private val listPackagesUseCase: IListChildModelSuspendUseCase<Package, UUID>,
    private val getPackageUseCase: IGetChildModelSuspendUseCase<Package, UUID, UUID>,
    private val listPackageVersionsUseCase: IListChildModelSuspendUseCase<PackageVersion, UUID>,
) : IPackagesController {

    override suspend fun list(call: ApplicationCall, parent: Organization): List<Package> {
        return listPackagesUseCase(parent.id)
    }

    override suspend fun get(call: ApplicationCall, parent: Organization, id: UUID): Map<String, Any> {
        val pkg = getPackageUseCase(id, parent.id)
            ?: throw ControllerException(HttpStatusCode.NotFound, "packages_not_found")
        if (pkg.organizationId != parent.id)
            throw ControllerException(HttpStatusCode.Forbidden, "packages_not_allowed")
        val versions = listPackageVersionsUseCase(pkg.id)
            .sortedByDescending { it.publishedAt }
        return mapOf(
            "item" to pkg,
            "versions" to versions
        )
    }

}
