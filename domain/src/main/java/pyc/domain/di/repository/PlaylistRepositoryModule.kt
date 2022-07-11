package pyc.domain.di.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pyc.domain.repository.PlaylistRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class PlaylistRepositoryModule {

    @Binds
    abstract fun bindPlaylistRepository(fakePlaylistRepository: FakePlaylistRepository): PlaylistRepository
}