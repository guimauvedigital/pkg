import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.RepositoryHandler

fun RepositoryHandler.pkg(
    project: Project,
    name: String = "pkg",
    url: String = "https://pkg.guimauve.digital/maven2",
    username: String? = null,
    password: String? = null,
) = maven {
    this.name = name
    this.url = project.uri(url)
    credentials {
        this.username = username
            ?: project.findProperty("pkgUsername") as String?
                    ?: System.getenv("PKG_USERNAME")
        this.password = password
            ?: project.findProperty("pkgPassword") as String?
                    ?: System.getenv("PKG_PASSWORD")
    }
}
