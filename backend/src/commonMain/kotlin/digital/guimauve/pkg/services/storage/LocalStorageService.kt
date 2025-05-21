package digital.guimauve.pkg.services.storage

import java.io.File

class LocalStorageService : IStorageService {

    override fun signUrl(path: String): String {
        return path
    }

    override fun uploadStream(file: FileContext, path: String): String? {
        val file = File(File("pkg_data"), path)
        file.parentFile.mkdirs()
        file.outputStream().use { output ->
            file.inputStream().copyTo(output)
        }
        return file.path
    }

}
