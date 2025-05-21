package digital.guimauve.pkg.controllers.packages.pypi

import dev.kaccelero.routers.APIUnitRouter
import dev.kaccelero.routers.ConcatUnitRouter
import dev.kaccelero.routers.TemplateUnitRouter
import io.ktor.server.freemarker.*

class PyPiRouter(
    controller: IPyPiController,
) : ConcatUnitRouter(
    TemplateUnitRouter(
        controller,
        IPyPiController::class,
        { template, model -> respondTemplate(template, model) },
        errorTemplate = null,
        route = "pypi",
        prefix = "/"
    ),
    APIUnitRouter(
        controller,
        IPyPiController::class,
        route = "pypi",
        prefix = "/"
    )
)
