package pyc.domain.di.usecase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pyc.domain.repository.PlaylistRepository
import pyc.domain.usecase.GetPlaylistUseCase

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetPlaylistUseCase(playlistRepository: PlaylistRepository): GetPlaylistUseCase =
        GetPlaylistUseCase(playlistRepository)
}