package pyc.domain.usecase

import io.reactivex.Completable
import pyc.domain.common.getChangedOrderList
import pyc.domain.repository.PrimaryPlaylistRepository
import javax.inject.Inject

internal class UpdatePrimaryAudioOrderUseCase @Inject constructor(
    private val primaryPlaylistRepository: PrimaryPlaylistRepository
){
    operator fun invoke(from: Int, to: Int): Completable =
        primaryPlaylistRepository.updateAudioOrder(getChangedOrderList(from, to))
}