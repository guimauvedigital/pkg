package digital.guimauve.pkg.usecases.packages

import digital.guimauve.pkg.database.packages.IPackagesRepository
import digital.guimauve.pkg.models.packages.Package
import digital.guimauve.pkg.models.packages.PackageFormat
import digital.guimauve.pkg.models.users.User

class GetPackageByNameUseCase(
    private val repository: IPackagesRepository,
) : IGetPackageByNameUseCase {

    override suspend fun invoke(input1: String, input2: PackageFormat, input3: User?): Package? =
        repository.getByName(input1, input2)?.takeIf {
            it.isPublic || it.organizationId == input3?.organizationId
        }

}
