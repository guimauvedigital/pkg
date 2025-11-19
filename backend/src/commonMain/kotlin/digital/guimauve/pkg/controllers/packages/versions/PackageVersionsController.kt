package digital.guimauve.pkg.controllers.packages.versions

import dev.kaccelero.commons.exceptions.ControllerException
import dev.kaccelero.commons.repositories.IGetChildModelSuspendUseCase
import dev.kaccelero.commons.repositories.IListChildModelSuspendUseCase
import dev.kaccelero.models.UUID
import digital.guimauve.pkg.models.packages.Package
import digital.guimauve.pkg.models.packages.versions.PackageVersion
import digital.guimauve.pkg.models.packages.versions.files.PackageVersionFile
import io.ktor.http.*
import io.ktor.server.application.*

class PackageVersionsController(
    private val getPackageVersionUseCase: IGetChildModelSuspendUseCase<PackageVersion, UUID, UUID>,
    private val listPackageVersionFilesUseCase: IListChildModelSuspendUseCase<PackageVersionFile, UUID>,
) : IPackageVersionsController {

    override suspend fun get(call: ApplicationCall, parent: Package, id: UUID): Map<String, Any> {
        val version = getPackageVersionUseCase(id, parent.id)
            ?: throw ControllerException(HttpStatusCode.NotFound, "package_versions_not_found")
        if (version.packageId != parent.id)
            throw ControllerException(HttpStatusCode.Forbidden, "package_versions_not_allowed")
        val files = listPackageVersionFilesUseCase(version.id)
            .sortedBy { it.name }
        return mapOf(
            "package" to parent,
            "item" to version,
            "files" to files
        )
    }

}
