package digital.guimauve.pkg.usecases.packages.versions

import dev.kaccelero.models.UUID
import digital.guimauve.pkg.database.packages.versions.IPackageVersionsRepository
import digital.guimauve.pkg.models.packages.versions.PackageVersion

class GetPackageVersionByNameUseCase(
    private val repository: IPackageVersionsRepository,
) : IGetPackageVersionByNameUseCase {

    override suspend fun invoke(input1: String, input2: UUID): PackageVersion? =
        repository.getByName(input1, input2)

}
