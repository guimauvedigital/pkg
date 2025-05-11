package digital.guimauve.pkg.usecases.users

import dev.kaccelero.usecases.ISuspendUseCase
import digital.guimauve.pkg.models.users.User

interface IGetUserForRefreshTokenUseCase : ISuspendUseCase<String, User?>
