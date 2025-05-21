package digital.guimauve.pkg.usecases.packages

import dev.kaccelero.usecases.ITripleSuspendUseCase
import digital.guimauve.pkg.models.packages.Package
import digital.guimauve.pkg.models.packages.PackageFormat
import digital.guimauve.pkg.models.users.User

interface IGetOrCreatePackageUseCase : ITripleSuspendUseCase<String, PackageFormat, User, Package?>
