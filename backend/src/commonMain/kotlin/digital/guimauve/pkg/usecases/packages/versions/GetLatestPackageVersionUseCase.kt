package digital.guimauve.pkg.usecases.packages.versions

import dev.kaccelero.models.UUID
import digital.guimauve.pkg.database.packages.versions.IPackageVersionsRepository
import digital.guimauve.pkg.models.packages.versions.PackageVersion

class GetLatestPackageVersionUseCase(
    private val repository: IPackageVersionsRepository,
) : IGetLatestPackageVersionUseCase {

    override suspend fun invoke(input: UUID): PackageVersion? = repository.getLatest(input)

}
