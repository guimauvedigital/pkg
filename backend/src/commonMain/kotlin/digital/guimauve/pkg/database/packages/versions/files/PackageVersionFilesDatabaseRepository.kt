package digital.guimauve.pkg.database.packages.versions.files

import dev.kaccelero.database.IDatabase
import org.jetbrains.exposed.sql.SchemaUtils

class PackageVersionFilesDatabaseRepository(
    private val database: IDatabase,
) : IPackageVersionFilesRepository {

    init {
        database.transaction {
            SchemaUtils.create(PackageVersionFiles)
        }
    }

}
