package digital.guimauve.pkg.controllers.packages.pypi

import dev.kaccelero.annotations.APIMapping
import dev.kaccelero.annotations.Path
import dev.kaccelero.annotations.PathParameter
import dev.kaccelero.annotations.TemplateMapping
import dev.kaccelero.controllers.IUnitController
import io.ktor.server.application.*

// Spec: https://peps.python.org/pep-0503/

interface IPyPiController : IUnitController {

    @TemplateMapping("pypi/root.ftl")
    @Path("GET", "/simple")
    suspend fun root(): Map<String, Any>

    @TemplateMapping("pypi/package.ftl")
    @Path("GET", "/simple/{packageName}")
    suspend fun packageInfo(@PathParameter packageName: String): Map<String, Any>

    @APIMapping
    @Path("POST", "/")
    suspend fun upload(call: ApplicationCall)

}
