package digital.guimauve.pkg.database.packages.versions

import dev.kaccelero.models.UUID
import dev.kaccelero.repositories.IChildModelSuspendRepository
import digital.guimauve.pkg.models.packages.versions.CreatePackageVersionPayload
import digital.guimauve.pkg.models.packages.versions.PackageVersion

interface IPackageVersionsRepository :
    IChildModelSuspendRepository<PackageVersion, UUID, CreatePackageVersionPayload, Unit, UUID>
