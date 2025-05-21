package digital.guimauve.pkg.database.packages.versions.files

import dev.kaccelero.database.IDatabase
import dev.kaccelero.database.eq
import dev.kaccelero.database.set
import dev.kaccelero.models.IContext
import dev.kaccelero.models.UUID
import digital.guimauve.pkg.database.packages.versions.PackageVersions
import digital.guimauve.pkg.models.packages.versions.files.CreatePackageVersionFilePayload
import digital.guimauve.pkg.models.packages.versions.files.PackageVersionFile
import digital.guimauve.pkg.services.storage.FileContext
import org.jetbrains.exposed.sql.*

class PackageVersionFilesDatabaseRepository(
    private val database: IDatabase,
) : IPackageVersionFilesRepository {

    init {
        database.transaction {
            SchemaUtils.create(PackageVersionFiles)
        }
    }

    override suspend fun getByName(name: String, parentId: UUID): PackageVersionFile? =
        database.suspendedTransaction {
            PackageVersionFiles
                .selectAll()
                .where { PackageVersionFiles.name eq name and (PackageVersionFiles.versionId eq parentId) }
                .map(PackageVersionFiles::toPackageVersionFile)
                .singleOrNull()
        }

    override suspend fun getLatestByName(name: String, packageId: UUID): PackageVersionFile? =
        database.suspendedTransaction {
            PackageVersionFiles
                .join(PackageVersions, JoinType.INNER, PackageVersionFiles.versionId, PackageVersions.id)
                .selectAll()
                .where { PackageVersionFiles.name eq name and (PackageVersions.packageId eq packageId) }
                .orderBy(PackageVersions.publishedAt to SortOrder.DESC)
                .limit(1)
                .map(PackageVersionFiles::toPackageVersionFile)
                .singleOrNull()
        }

    override suspend fun create(
        payload: CreatePackageVersionFilePayload,
        parentId: UUID,
        context: IContext?,
    ): PackageVersionFile? {
        val fileContext = context as? FileContext ?: return null
        return database.suspendedTransaction {
            PackageVersionFiles.insert {
                it[versionId] = parentId
                it[name] = payload.name
                it[path] = payload.path
                it[contentType] = fileContext.contentType.toString()
                it[size] = fileContext.contentLength
            }
        }.resultedValues?.map(PackageVersionFiles::toPackageVersionFile)?.singleOrNull()
    }

}
