package digital.guimauve.pkg.controllers.packages.npm

import dev.kaccelero.routers.APIUnitRouter

class NpmRouter(
    controller: INpmController,
) : APIUnitRouter(
    controller,
    INpmController::class,
    route = "npm",
    prefix = "/"
)
