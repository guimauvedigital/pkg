package digital.guimauve.pkg.services.storage

import dev.kaccelero.models.IContext
import io.ktor.http.*

data class FileFromBytesContext(
    val bytes: ByteArray,
    val contentType: ContentType,
) : IContext
