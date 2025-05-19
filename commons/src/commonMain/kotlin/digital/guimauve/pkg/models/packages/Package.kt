package digital.guimauve.pkg.models.packages

import dev.kaccelero.models.IChildModel
import dev.kaccelero.models.UUID
import digital.guimauve.zodable.Zodable
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Zodable
@JsExport
@Serializable
data class Package(
    override val id: UUID,
    val name: String,
    val format: PackageFormat,
    val organizationId: UUID,
    val isPublic: Boolean,
    val createdAt: Instant,
) : IChildModel<UUID, CreatePackagePayload, UpdatePackagePayload, UUID> {

    override val parentId: UUID
        get() = organizationId

}
