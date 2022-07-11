package pyc.domain.usecase

import io.reactivex.Completable
import pyc.domain.repository.PlaylistRepository
import javax.inject.Inject

class InsertPrimaryAudioUseCase @Inject constructor(
    private val playlistRepository: PlaylistRepository
) {
    operator fun invoke(
        playlistId: Int,
        startOrder: Int,
        uriList: List<String>
    ): Completable =
        playlistRepository.insertAudio(playlistId, startOrder, uriList)
}