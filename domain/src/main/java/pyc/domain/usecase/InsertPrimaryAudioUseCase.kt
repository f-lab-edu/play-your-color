package pyc.domain.usecase

import io.reactivex.Completable
import pyc.domain.repository.PlaylistRepository
import javax.inject.Inject

internal class InsertPrimaryAudioUseCase @Inject constructor(
    private val playlistRepository: PlaylistRepository
) {
    operator fun invoke(startOrder: Int, uriList: List<String>): Completable =
        playlistRepository.insertAudio(startOrder, uriList)
}