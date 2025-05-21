package digital.guimauve.pkg.services.storage

interface IStorageService {

    fun signUrl(path: String): String
    fun uploadStream(file: FileContext, path: String): String?

}
