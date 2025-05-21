package digital.guimauve.pkg.controllers.packages.maven

import dev.kaccelero.commons.exceptions.ControllerException
import dev.kaccelero.commons.responses.BytesResponse
import dev.kaccelero.commons.users.IGetUserForCallUseCase
import dev.kaccelero.commons.users.IRequireUserForCallUseCase
import digital.guimauve.pkg.models.packages.PackageFormat
import digital.guimauve.pkg.models.users.User
import digital.guimauve.pkg.usecases.packages.IGetOrCreatePackageUseCase
import digital.guimauve.pkg.usecases.packages.IGetPackageByNameUseCase
import digital.guimauve.pkg.usecases.packages.maven.IParseMavenPathUseCase
import digital.guimauve.pkg.usecases.packages.versions.IGetPackageVersionByNameUseCase
import io.ktor.http.*
import io.ktor.server.application.*

class MavenController(
    private val getUserUseCase: IGetUserForCallUseCase,
    private val requireUserUseCase: IRequireUserForCallUseCase,
    private val parseMavenPathUseCase: IParseMavenPathUseCase,
    private val getPackageUseCase: IGetPackageByNameUseCase,
    private val getOrCreatePackageUseCase: IGetOrCreatePackageUseCase,
    private val getPackageVersionUseCase: IGetPackageVersionByNameUseCase,
) : IMavenController {

    override suspend fun get(call: ApplicationCall): BytesResponse {
        val user = getUserUseCase(call) as? User
        val mavenPath = parseMavenPathUseCase(call.parameters.getAll("path") ?: emptyList())
        val `package` = getPackageUseCase(mavenPath.packageName, PackageFormat.MAVEN, user)
            ?: throw ControllerException(HttpStatusCode.NotFound, "packages_not_found")
        val version = mavenPath.version?.let { getPackageVersionUseCase(it, `package`.id) }
        return BytesResponse(
            bytes = ByteArray(0),
            contentType = ContentType.Application.Xml
        )
    }

    override suspend fun put(call: ApplicationCall) {
        val user = requireUserUseCase(call) as User
        val mavenPath = parseMavenPathUseCase(call.parameters.getAll("path") ?: emptyList())
        val `package` = getOrCreatePackageUseCase(mavenPath.packageName, PackageFormat.MAVEN, user)
            ?: throw ControllerException(HttpStatusCode.NotFound, "packages_not_found")
    }

}
