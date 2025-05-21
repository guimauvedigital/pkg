package digital.guimauve.pkg.usecases.packages.versions.files

import dev.kaccelero.commons.responses.BytesResponse
import dev.kaccelero.usecases.ISuspendUseCase
import digital.guimauve.pkg.models.packages.versions.files.PackageVersionFile

interface IDownloadFileUseCase : ISuspendUseCase<PackageVersionFile, BytesResponse>
