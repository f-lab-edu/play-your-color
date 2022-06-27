package pyc.domain.usecase

import io.reactivex.Completable
import pyc.domain.common.getChangedOrderList
import pyc.domain.repository.PrimaryPlaylistRepository
import javax.inject.Inject

internal class DeletePrimaryAudioUseCase @Inject constructor(
    private val primaryPlaylistRepository: PrimaryPlaylistRepository
) {
    operator fun invoke(playListSize: Int, deleteOrderList: List<Int>): Completable {
        var changedOrderList : List<Pair<Int, Int>> = emptyList()
        return primaryPlaylistRepository.deleteAudio(deleteOrderList)
            .andThen { changedOrderList = getChangedOrderList(playListSize, deleteOrderList) }
            .andThen(primaryPlaylistRepository.updateAudioOrder(changedOrderList))
    }
}