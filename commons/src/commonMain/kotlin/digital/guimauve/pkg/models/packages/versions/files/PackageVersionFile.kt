package digital.guimauve.pkg.models.packages.versions.files

import dev.kaccelero.models.IChildModel
import dev.kaccelero.models.UUID
import digital.guimauve.zodable.Zodable
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Zodable
@JsExport
@Serializable
data class PackageVersionFile(
    override val id: UUID,
    val versionId: UUID,
    val name: String,
    val contentType: String,
    val size: Long,
    val path: String,
) : IChildModel<UUID, CreatePackageVersionFilePayload, Unit, UUID> {

    override val parentId: UUID
        get() = versionId

}
