package digital.guimauve.pkg.controllers.packages.maven

import dev.kaccelero.commons.exceptions.ControllerException
import dev.kaccelero.commons.responses.BytesResponse
import digital.guimauve.pkg.models.packages.PackageFormat
import digital.guimauve.pkg.usecases.packages.IGetPackageByNameUseCase
import digital.guimauve.pkg.usecases.packages.IGetPackageVersionByNameUseCase
import digital.guimauve.pkg.usecases.packages.maven.IParseMavenPathUseCase
import io.ktor.http.*
import io.ktor.server.application.*

class MavenController(
    private val parseMavenPathUseCase: IParseMavenPathUseCase,
    private val getPackageUseCase: IGetPackageByNameUseCase,
    private val getPackageVersionUseCase: IGetPackageVersionByNameUseCase,
) : IMavenController {

    override suspend fun get(call: ApplicationCall): BytesResponse {
        val mavenPath = parseMavenPathUseCase(call.parameters.getAll("path") ?: emptyList())
        val `package` = getPackageUseCase("${mavenPath.groupId}:${mavenPath.artifactId}", PackageFormat.MAVEN)
            ?: throw ControllerException(HttpStatusCode.NotFound, "packages_not_found")
        val version = mavenPath.version?.let { getPackageVersionUseCase(it, `package`.id) }
        return BytesResponse(
            bytes = ByteArray(0),
            contentType = ContentType.Application.Xml
        )
    }

    override suspend fun put(call: ApplicationCall) {
        val mavenPath = parseMavenPathUseCase(call.parameters.getAll("path") ?: emptyList())
        //val `package` = getPackageUseCase("${mavenPath.groupId}:${mavenPath.artifactId}", PackageFormat.MAVEN)
        //    ?: throw ControllerException(HttpStatusCode.NotFound, "packages_not_found")
    }

}
