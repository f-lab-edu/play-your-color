package pyc.domain.usecase

import io.reactivex.Flowable
import pyc.domain.model.PlaylistItem
import pyc.domain.repository.PlaylistRepository
import javax.inject.Inject

internal class GetPlaylistByColorUseCase @Inject constructor(
    private val playlistRepository: PlaylistRepository
) {
    operator fun invoke(
        playlistId: Int,
        colorInfoId: Int
    ): Flowable<List<PlaylistItem>> =
        playlistRepository.getPlaylistByColor(playlistId, colorInfoId)
}