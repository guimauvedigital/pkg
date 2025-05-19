package digital.guimauve.pkg.models.packages.versions

import dev.kaccelero.models.IChildModel
import dev.kaccelero.models.UUID
import digital.guimauve.zodable.Zodable
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Zodable
@JsExport
@Serializable
data class PackageVersion(
    override val id: UUID,
    val packageId: UUID,
    val version: String,
    val publishedBy: UUID,
    val publishedAt: Instant,
    val metadata: String,
    val yanked: Boolean,
) : IChildModel<UUID, CreatePackageVersionPayload, Unit, UUID> {

    override val parentId: UUID
        get() = packageId

}
