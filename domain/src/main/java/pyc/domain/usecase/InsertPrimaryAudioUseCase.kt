package pyc.domain.usecase

import io.reactivex.Completable
import pyc.domain.repository.PrimaryPlaylistRepository
import javax.inject.Inject

internal class InsertPrimaryAudioUseCase @Inject constructor(
    private val primaryPlaylistRepository: PrimaryPlaylistRepository
) {
    operator fun invoke(startOrder: Int, uriList: List<String>): Completable =
        primaryPlaylistRepository.insertAudio(startOrder, uriList)
}