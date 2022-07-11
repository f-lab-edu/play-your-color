package pyc.domain.usecase

import io.reactivex.Flowable
import pyc.domain.model.PlaylistItem
import pyc.domain.repository.PlaylistRepository
import javax.inject.Inject

class GetPlaylistUseCase @Inject constructor(
    private val playlistRepository: PlaylistRepository
) {
    operator fun invoke(playlistId: Int): Flowable<List<PlaylistItem>> =
        playlistRepository.getPlaylist(playlistId)
}