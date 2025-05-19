package digital.guimauve.pkg.controllers.packages.maven

import dev.kaccelero.annotations.APIMapping
import dev.kaccelero.annotations.Path
import dev.kaccelero.commons.responses.BytesResponse
import dev.kaccelero.controllers.IUnitController
import io.ktor.server.application.*

interface IMavenController : IUnitController {

    @APIMapping
    @Path("GET", "/{path...}")
    suspend fun get(call: ApplicationCall): BytesResponse

    @APIMapping
    @Path("PUT", "/{path...}")
    suspend fun put(call: ApplicationCall)

}
