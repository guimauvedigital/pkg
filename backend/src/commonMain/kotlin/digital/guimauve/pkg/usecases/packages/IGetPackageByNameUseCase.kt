package digital.guimauve.pkg.usecases.packages

import dev.kaccelero.usecases.IPairSuspendUseCase
import digital.guimauve.pkg.models.packages.Package
import digital.guimauve.pkg.models.packages.PackageFormat

interface IGetPackageByNameUseCase : IPairSuspendUseCase<String, PackageFormat, Package?>
