package pyc.domain.usecase

import io.reactivex.Flowable
import pyc.domain.model.PlaylistItem
import pyc.domain.repository.PlaylistRepository
import javax.inject.Inject

internal class GetPrimaryAudioListUseCase @Inject constructor(
    private val playlistRepository: PlaylistRepository
) {
    operator fun invoke(playListId: Int): Flowable<List<PlaylistItem>> =
        playlistRepository.getPlaylist(playListId)
}