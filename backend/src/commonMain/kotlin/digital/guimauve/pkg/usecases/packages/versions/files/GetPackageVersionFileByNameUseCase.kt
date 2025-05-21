package digital.guimauve.pkg.usecases.packages.versions.files

import dev.kaccelero.models.UUID
import digital.guimauve.pkg.database.packages.versions.files.IPackageVersionFilesRepository
import digital.guimauve.pkg.models.packages.versions.files.PackageVersionFile

class GetPackageVersionFileByNameUseCase(
    private val repository: IPackageVersionFilesRepository,
) : IGetPackageVersionFileByNameUseCase {

    override suspend fun invoke(input1: String, input2: UUID): PackageVersionFile? =
        repository.getByName(input1, input2)

}
