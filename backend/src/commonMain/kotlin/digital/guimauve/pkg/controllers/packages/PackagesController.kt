package digital.guimauve.pkg.controllers.packages

import dev.kaccelero.commons.repositories.IListChildModelSuspendUseCase
import dev.kaccelero.models.UUID
import digital.guimauve.pkg.models.organizations.Organization
import digital.guimauve.pkg.models.packages.Package
import io.ktor.server.application.*

class PackagesController(
    private val listPackagesUseCase: IListChildModelSuspendUseCase<Package, UUID>,
) : IPackagesController {

    override suspend fun list(call: ApplicationCall, parent: Organization): List<Package> {
        return listPackagesUseCase(parent.id)
    }

}
