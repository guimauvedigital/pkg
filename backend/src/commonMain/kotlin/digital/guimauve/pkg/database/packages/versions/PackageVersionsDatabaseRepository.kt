package digital.guimauve.pkg.database.packages.versions

import dev.kaccelero.database.IDatabase
import dev.kaccelero.database.eq
import dev.kaccelero.models.UUID
import digital.guimauve.pkg.models.packages.versions.PackageVersion
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.selectAll

class PackageVersionsDatabaseRepository(
    private val database: IDatabase,
) : IPackageVersionsRepository {

    init {
        database.transaction {
            SchemaUtils.create(PackageVersions)
        }
    }

    override suspend fun getByName(name: String, packageId: UUID): PackageVersion? =
        database.suspendedTransaction {
            PackageVersions
                .selectAll()
                .where { PackageVersions.version eq name and (PackageVersions.packageId eq packageId) }
                .map(PackageVersions::toPackageVersion)
                .singleOrNull()
        }

}
