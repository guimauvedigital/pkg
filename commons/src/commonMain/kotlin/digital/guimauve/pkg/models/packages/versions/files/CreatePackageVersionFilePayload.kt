package digital.guimauve.pkg.models.packages.versions.files

import digital.guimauve.zodable.Zodable
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Zodable
@JsExport
@Serializable
data class CreatePackageVersionFilePayload(
    val name: String,
    val path: String,
)
