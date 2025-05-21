package digital.guimauve.pkg.services.storage

import java.io.InputStream

interface IStorageService {

    fun signUrl(path: String): String
    fun uploadStream(file: FileContext, path: String): String?
    fun downloadStream(path: String): InputStream?

}
