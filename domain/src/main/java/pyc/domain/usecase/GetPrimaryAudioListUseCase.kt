package pyc.domain.usecase

import io.reactivex.Flowable
import pyc.domain.model.Audio
import pyc.domain.repository.PrimaryPlaylistRepository
import javax.inject.Inject

internal class GetPrimaryAudioListUseCase @Inject constructor(
    private val primaryPlaylistRepository: PrimaryPlaylistRepository
) {
    operator fun invoke(): Flowable<List<Audio>> =
        primaryPlaylistRepository.getAudioList()
}