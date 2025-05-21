package digital.guimauve.pkg.services.storage

import dev.kaccelero.models.IContext
import io.ktor.http.*
import java.net.URL

data class FileFromUrlContext(
    val url: URL,
    val contentType: ContentType,
) : IContext
