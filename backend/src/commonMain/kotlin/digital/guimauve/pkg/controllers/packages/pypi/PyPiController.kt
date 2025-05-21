package digital.guimauve.pkg.controllers.packages.pypi

import dev.kaccelero.commons.exceptions.ControllerException
import digital.guimauve.pkg.models.packages.PackageFormat
import digital.guimauve.pkg.models.packages.versions.files.PackageVersionFile
import digital.guimauve.pkg.usecases.packages.IGetPackageByNameUseCase
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*

class PyPiController(
    private val getPackageUseCase: IGetPackageByNameUseCase,
) : IPyPiController {

    override suspend fun root(): Map<String, Any> {

        return mapOf(
            "packages" to listOf<Package>()
        )
    }

    override suspend fun packageInfo(packageName: String): Map<String, Any> {
        val `package` = getPackageUseCase(packageName, PackageFormat.PYPI)
            ?: throw ControllerException(HttpStatusCode.NotFound, "packages_not_found")
        return mapOf(
            "package" to `package`,
            "files" to listOf<PackageVersionFile>()
        )
    }

    override suspend fun upload(call: ApplicationCall) {
        val t = call.receiveMultipart()
        t.forEachPart { part ->
            println(part.name)
        }
    }

}
