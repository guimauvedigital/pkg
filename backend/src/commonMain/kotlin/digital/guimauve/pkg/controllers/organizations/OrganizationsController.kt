package digital.guimauve.pkg.controllers.organizations

import dev.kaccelero.commons.exceptions.ControllerException
import dev.kaccelero.commons.repositories.IGetModelSuspendUseCase
import dev.kaccelero.commons.repositories.IListModelSuspendUseCase
import dev.kaccelero.commons.users.IRequireUserForCallUseCase
import dev.kaccelero.models.UUID
import digital.guimauve.pkg.models.organizations.Organization
import digital.guimauve.pkg.models.users.User
import io.ktor.http.*
import io.ktor.server.application.*

class OrganizationsController(
    private val requireUserForCallUseCase: IRequireUserForCallUseCase,
    private val listOrganizationsUseCase: IListModelSuspendUseCase<Organization>,
    private val getOrganizationUseCase: IGetModelSuspendUseCase<Organization, UUID>,
) : IOrganizationsController {

    override suspend fun list(call: ApplicationCall): List<Organization> {
        return listOrganizationsUseCase()
    }

    override suspend fun get(call: ApplicationCall, id: UUID): Organization {
        val organization = getOrganizationUseCase(id)
            ?: throw ControllerException(HttpStatusCode.NotFound, "organizations_not_found")
        val user = requireUserForCallUseCase(call) as User
        if (user.organizationId != organization.id)
            throw ControllerException(HttpStatusCode.Forbidden, "organizations_not_allowed")
        return organization
    }

}
