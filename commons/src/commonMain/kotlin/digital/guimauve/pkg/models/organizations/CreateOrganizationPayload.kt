package digital.guimauve.pkg.models.organizations

import dev.kaccelero.annotations.Schema
import digital.guimauve.zodable.Zodable
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Zodable
@JsExport
@Serializable
data class CreateOrganizationPayload(
    @Schema("Name of the organization", "Guimauve Digital")
    val name: String,
)
