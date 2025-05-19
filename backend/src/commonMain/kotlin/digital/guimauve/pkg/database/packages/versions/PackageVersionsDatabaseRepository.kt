package digital.guimauve.pkg.database.packages.versions

import dev.kaccelero.database.IDatabase
import org.jetbrains.exposed.sql.SchemaUtils

class PackageVersionsDatabaseRepository(
    private val database: IDatabase,
) : IPackageVersionsRepository {

    init {
        database.transaction {
            SchemaUtils.create(PackageVersions)
        }
    }

}
