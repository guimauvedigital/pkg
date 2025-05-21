package digital.guimauve.pkg.models.auth

import dev.kaccelero.annotations.StringPropertyValidator
import digital.guimauve.pkg.models.users.User
import digital.guimauve.zodable.Zodable
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Zodable
@JsExport
@Serializable
data class LoginPayload(
    @StringPropertyValidator(regex = User.EMAIL_REGEX)
    val email: String,
    val password: String,
)
