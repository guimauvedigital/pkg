package digital.guimauve.pkg.controllers.packages.npm

import dev.kaccelero.annotations.APIMapping
import dev.kaccelero.annotations.Path
import dev.kaccelero.annotations.PathParameter
import dev.kaccelero.annotations.Payload
import dev.kaccelero.controllers.IUnitController
import digital.guimauve.pkg.models.packages.npm.NpmPackage
import digital.guimauve.pkg.models.packages.npm.NpmVersion
import io.ktor.server.application.*

// Spec: https://github.com/npm/registry/blob/main/docs/REGISTRY-API.md

interface INpmController : IUnitController {

    // Implement web login: https://marmelab.com/blog/2022/12/22/how-to-implement-web-login-in-a-private-npm-registry.html

    @APIMapping
    @Path("POST", "/-/v1/login")
    suspend fun login(call: ApplicationCall): Map<String, String>

    @APIMapping
    @Path("PUT", "/{packageName}")
    suspend fun put(call: ApplicationCall, @PathParameter packageName: String, @Payload payload: NpmPackage)

    @APIMapping
    @Path("GET", "/{packageName}")
    suspend fun get(call: ApplicationCall, @PathParameter packageName: String): NpmPackage

    @APIMapping
    @Path("GET", "/{packageName}/{version}")
    suspend fun getVersion(
        call: ApplicationCall,
        @PathParameter packageName: String,
        @PathParameter version: String,
    ): NpmVersion

}
