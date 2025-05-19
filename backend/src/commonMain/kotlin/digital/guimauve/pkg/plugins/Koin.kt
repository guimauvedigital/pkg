package digital.guimauve.pkg.plugins

import dev.kaccelero.commons.auth.*
import dev.kaccelero.commons.localization.GetLocaleForCallUseCase
import dev.kaccelero.commons.localization.IGetLocaleForCallUseCase
import dev.kaccelero.commons.localization.ITranslateUseCase
import dev.kaccelero.commons.localization.TranslateFromPropertiesUseCase
import dev.kaccelero.commons.repositories.*
import dev.kaccelero.commons.users.IGetUserForCallUseCase
import dev.kaccelero.commons.users.IRequireUserForCallUseCase
import dev.kaccelero.commons.users.RequireUserForCallUseCase
import dev.kaccelero.database.IDatabase
import dev.kaccelero.models.UUID
import digital.guimauve.pkg.controllers.organizations.IOrganizationsController
import digital.guimauve.pkg.controllers.organizations.OrganizationsController
import digital.guimauve.pkg.controllers.organizations.OrganizationsRouter
import digital.guimauve.pkg.controllers.packages.maven.IMavenController
import digital.guimauve.pkg.controllers.packages.maven.MavenController
import digital.guimauve.pkg.controllers.packages.maven.MavenRouter
import digital.guimauve.pkg.controllers.users.IUsersController
import digital.guimauve.pkg.controllers.users.UsersController
import digital.guimauve.pkg.controllers.users.UsersRouter
import digital.guimauve.pkg.database.Database
import digital.guimauve.pkg.database.organizations.IOrganizationsRepository
import digital.guimauve.pkg.database.organizations.OrganizationDatabaseRepository
import digital.guimauve.pkg.database.packages.IPackagesRepository
import digital.guimauve.pkg.database.packages.PackagesDatabaseRepository
import digital.guimauve.pkg.database.packages.versions.IPackageVersionsRepository
import digital.guimauve.pkg.database.packages.versions.PackageVersionsDatabaseRepository
import digital.guimauve.pkg.database.packages.versions.files.IPackageVersionFilesRepository
import digital.guimauve.pkg.database.packages.versions.files.PackageVersionFilesDatabaseRepository
import digital.guimauve.pkg.database.users.IUsersRepository
import digital.guimauve.pkg.database.users.UsersDatabaseRepository
import digital.guimauve.pkg.models.organizations.CreateOrganizationPayload
import digital.guimauve.pkg.models.organizations.Organization
import digital.guimauve.pkg.models.users.CreateUserPayload
import digital.guimauve.pkg.models.users.User
import digital.guimauve.pkg.services.jwt.IJWTService
import digital.guimauve.pkg.services.jwt.JWTService
import digital.guimauve.pkg.usecases.auth.*
import digital.guimauve.pkg.usecases.packages.GetPackageByNameUseCase
import digital.guimauve.pkg.usecases.packages.GetPackageVersionByNameUseCase
import digital.guimauve.pkg.usecases.packages.IGetPackageByNameUseCase
import digital.guimauve.pkg.usecases.packages.IGetPackageVersionByNameUseCase
import digital.guimauve.pkg.usecases.packages.maven.IParseMavenPathUseCase
import digital.guimauve.pkg.usecases.packages.maven.ParseMavenPathUseCase
import digital.guimauve.pkg.usecases.users.*
import io.ktor.server.application.*
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin

fun Application.configureKoin() {
    install(Koin) {
        val databaseModule = module {
            single<IDatabase> {
                Database(
                    environment.config.property("database.protocol").getString(),
                    environment.config.property("database.host").getString(),
                    environment.config.property("database.name").getString(),
                    environment.config.property("database.user").getString(),
                    environment.config.property("database.password").getString()
                )
            }
        }
        val serviceModule = module {
            single<IJWTService> {
                JWTService(
                    environment.config.property("jwt.secret").getString(),
                    environment.config.property("jwt.issuer").getString(),
                    environment.config.property("jwt.audience").getString()
                )
            }
        }
        val repositoryModule = module {
            single<IOrganizationsRepository> { OrganizationDatabaseRepository(get()) }
            single<IUsersRepository> { UsersDatabaseRepository(get()) }
            single<IPackagesRepository> { PackagesDatabaseRepository(get()) }
            single<IPackageVersionsRepository> { PackageVersionsDatabaseRepository(get()) }
            single<IPackageVersionFilesRepository> { PackageVersionFilesDatabaseRepository(get()) }
        }
        val useCaseModule = module {
            // Application
            single<ITranslateUseCase> { TranslateFromPropertiesUseCase() }
            single<IGetLocaleForCallUseCase> { GetLocaleForCallUseCase() }
            single<IGetJWTPrincipalForCallUseCase> { GetJWTPrincipalForCallUseCase() }

            // Auth
            single<IHashPasswordUseCase> { HashPasswordUseCase() }
            single<IVerifyPasswordUseCase> { VerifyPasswordUseCase() }
            single<IGetSessionForCallUseCase> { GetSessionForCallUseCase() }
            single<ISetSessionForCallUseCase> { SetSessionForCallUseCase() }
            single<IClearSessionForCallUseCase> { ClearSessionForCallUseCase() }

            // Organizations
            single<IListModelSuspendUseCase<Organization>>(named<Organization>()) {
                ListModelFromRepositorySuspendUseCase(get<IOrganizationsRepository>())
            }
            single<IGetModelSuspendUseCase<Organization, UUID>>(named<Organization>()) {
                GetModelFromRepositorySuspendUseCase(get<IOrganizationsRepository>())
            }
            single<ICreateModelSuspendUseCase<Organization, CreateOrganizationPayload>>(named<Organization>()) {
                CreateModelFromRepositorySuspendUseCase(get<IOrganizationsRepository>())
            }

            // Users
            single<IListChildModelSuspendUseCase<User, UUID>>(named<User>()) {
                ListChildModelFromRepositorySuspendUseCase(get<IUsersRepository>())
            }
            single<IGetChildModelSuspendUseCase<User, UUID, UUID>>(named<User>()) {
                GetChildModelFromRepositorySuspendUseCase(get<IUsersRepository>())
            }
            single<ICreateChildModelSuspendUseCase<User, CreateUserPayload, UUID>>(named<User>()) {
                CreateUserUseCase(get(), get())
            }
            single<IGetUserUseCase> { GetUserUseCase(get()) }
            single<IGetUserForEmailUseCase> { GetUserForEmailUseCase(get()) }
            single<IGetUserForCallUseCase> { GetUserForCallUseCase(get(), get(), get()) }
            single<IGetUserForRefreshTokenUseCase> { GetUserForRefreshTokenUseCase(get(), get()) }
            single<IRequireUserForCallUseCase> { RequireUserForCallUseCase(get()) }

            // Packages
            single<IGetPackageByNameUseCase> { GetPackageByNameUseCase(get()) }
            single<IGetPackageVersionByNameUseCase> { GetPackageVersionByNameUseCase(get()) }

            // Maven
            single<IParseMavenPathUseCase> { ParseMavenPathUseCase() }
        }
        val controllerModule = module {
            single<IOrganizationsController> {
                OrganizationsController(
                    get(),
                    get(named<Organization>()),
                    get(named<Organization>())
                )
            }
            single<IUsersController> {
                UsersController(
                    get(named<User>())
                )
            }
            single<IMavenController> { MavenController(get(), get(), get()) }
        }
        val routerModule = module {
            single { OrganizationsRouter(get()) }
            single { UsersRouter(get(), get()) }
            single { MavenRouter(get()) }
        }

        modules(
            databaseModule,
            serviceModule,
            repositoryModule,
            useCaseModule,
            controllerModule,
            routerModule
        )
    }
}
