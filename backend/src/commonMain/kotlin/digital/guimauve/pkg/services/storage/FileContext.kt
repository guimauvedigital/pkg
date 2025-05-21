package digital.guimauve.pkg.services.storage

import dev.kaccelero.models.IContext
import io.ktor.http.*
import java.io.InputStream

class FileContext(
    val inputStream: InputStream,
    val contentType: ContentType,
    val contentLength: Long,
) : IContext
