package digital.guimauve.pkg.controllers.packages.maven

import dev.kaccelero.routers.APIUnitRouter

class MavenRouter(
    controller: IMavenController,
) : APIUnitRouter(
    controller,
    IMavenController::class,
    route = "maven",
    prefix = "/"
)
