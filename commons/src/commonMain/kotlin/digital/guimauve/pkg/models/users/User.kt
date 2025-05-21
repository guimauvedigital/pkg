package digital.guimauve.pkg.models.users

import dev.kaccelero.annotations.ModelProperty
import dev.kaccelero.annotations.Schema
import dev.kaccelero.models.IChildModel
import dev.kaccelero.models.IUser
import dev.kaccelero.models.UUID
import digital.guimauve.zodable.Zodable
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Zodable
@JsExport
@Serializable
data class User(
    @ModelProperty("id")
    @Schema("Id of the User", "123abc")
    override val id: UUID,
    @Schema("Id of the organization the user is in", "123abc")
    val organizationId: UUID,
    @ModelProperty("email", "12", visibleOnUpdate = true)
    @Schema("Email of the User", "nathan@guimauve.digital")
    val email: String,
    val password: String?,
) : IChildModel<UUID, CreateUserPayload, Unit, UUID>, IUser {

    override val parentId: UUID
        get() = organizationId

    companion object {

        const val EMAIL_REGEX = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"

    }

}
