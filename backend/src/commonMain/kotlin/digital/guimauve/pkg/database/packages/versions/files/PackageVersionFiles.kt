package digital.guimauve.pkg.database.packages.versions.files

import dev.kaccelero.models.UUID
import digital.guimauve.pkg.models.packages.versions.files.PackageVersionFile
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ResultRow

object PackageVersionFiles : UUIDTable() {

    val versionId = uuid("version_id").index()
    val name = varchar("name", 255)
    val contentType = varchar("content_type", 255)
    val size = long("size")
    val path = varchar("path", 255)

    init {
        uniqueIndex(versionId, name)
    }

    fun toPackageVersionFile(
        row: ResultRow,
    ) = PackageVersionFile(
        UUID(row[id].value),
        UUID(row[versionId]),
        row[name],
        row[contentType],
        row[size],
        row[path],
    )

}
