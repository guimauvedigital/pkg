package digital.guimauve.pkg.usecases.organizations

import dev.kaccelero.usecases.ISuspendUseCase
import digital.guimauve.pkg.models.organizations.Organization
import io.ktor.server.application.*

interface IRequireOrganizationForCallUseCase : ISuspendUseCase<ApplicationCall, Organization>
