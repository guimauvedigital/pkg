package digital.guimauve.pkg.usecases.auth

import dev.kaccelero.usecases.ISuspendUseCase
import digital.guimauve.pkg.models.auth.LoginPayload
import digital.guimauve.pkg.models.users.User

interface ILoginUseCase : ISuspendUseCase<LoginPayload, User?>
