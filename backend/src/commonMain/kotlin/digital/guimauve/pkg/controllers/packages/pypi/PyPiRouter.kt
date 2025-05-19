package digital.guimauve.pkg.controllers.packages.pypi

import dev.kaccelero.routers.APIUnitRouter

class PyPiRouter(
    controller: IPyPiController,
) : APIUnitRouter(
    controller,
    IPyPiController::class,
    route = "pypi",
    prefix = "/"
)
