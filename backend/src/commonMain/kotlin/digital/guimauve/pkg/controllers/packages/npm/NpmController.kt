package digital.guimauve.pkg.controllers.packages.npm

import dev.kaccelero.commons.exceptions.ControllerException
import digital.guimauve.pkg.models.packages.PackageFormat
import digital.guimauve.pkg.models.packages.npm.NpmPackage
import digital.guimauve.pkg.models.packages.npm.NpmVersion
import digital.guimauve.pkg.usecases.packages.IGetPackageByNameUseCase
import digital.guimauve.pkg.usecases.packages.IGetPackageVersionByNameUseCase
import io.ktor.http.*
import io.ktor.server.application.*

class NpmController(
    private val getPackageUseCase: IGetPackageByNameUseCase,
    private val getPackageVersionUseCase: IGetPackageVersionByNameUseCase,
) : INpmController {

    override suspend fun login(call: ApplicationCall): Map<String, String> {
        return mapOf() // TODO
    }

    override suspend fun put(call: ApplicationCall, packageName: String, payload: NpmPackage) {
        println(payload)
    }

    override suspend fun get(call: ApplicationCall, packageName: String): NpmPackage {
        val `package` = getPackageUseCase(packageName, PackageFormat.NPM)
            ?: throw ControllerException(HttpStatusCode.NotFound, "packages_not_found")
        return NpmPackage(
            id = `package`.name,
            name = `package`.name,
            description = null,
            versions = mapOf()
        )
    }

    override suspend fun getVersion(call: ApplicationCall, packageName: String, version: String): NpmVersion {
        val `package` = getPackageUseCase(packageName, PackageFormat.NPM)
            ?: throw ControllerException(HttpStatusCode.NotFound, "packages_not_found")
        val packageVersion = getPackageVersionUseCase(version, `package`.id)
            ?: throw ControllerException(HttpStatusCode.NotFound, "versions_not_found")
        return NpmVersion(
            name = `package`.name,
            version = packageVersion.version
        )
    }

}
