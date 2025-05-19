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
    val sha1 = varchar("sha1", 255)
    val sha256 = varchar("sha256", 255)
    val sha512 = varchar("sha512", 255)
    val path = varchar("path", 255)
    val signature = varchar("signature", 255).nullable()

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
        row[sha1],
        row[sha256],
        row[sha512],
        row[path],
        row[signature],
    )

}
