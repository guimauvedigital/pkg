package digital.guimauve.pkg.usecases.packages.maven

import dev.kaccelero.usecases.IUseCase
import digital.guimauve.pkg.models.packages.maven.MavenPath

interface IParseMavenPathUseCase : IUseCase<List<String>, MavenPath>
