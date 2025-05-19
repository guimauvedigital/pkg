package digital.guimauve.pkg.usecases.packages

import digital.guimauve.pkg.database.packages.IPackagesRepository
import digital.guimauve.pkg.models.packages.Package
import digital.guimauve.pkg.models.packages.PackageFormat

class GetPackageByNameUseCase(
    private val repository: IPackagesRepository,
) : IGetPackageByNameUseCase {

    override suspend fun invoke(input1: String, input2: PackageFormat): Package? =
        repository.getByName(input1, input2)

}
