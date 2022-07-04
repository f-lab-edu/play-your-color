package pyc.domain.usecase

import io.reactivex.Completable
import pyc.domain.repository.PlaylistRepository
import javax.inject.Inject

internal class UpdatePrimaryAudioOrderUseCase @Inject constructor(
    private val playlistRepository: PlaylistRepository
){
    operator fun invoke(currentIdList: MutableList<Int>, from: Int, to: Int): Completable {
        currentIdList.add(to, currentIdList.removeAt(from))
        return playlistRepository.updateAudioOrder(
            (from..to).map { Pair(currentIdList[it], it) }
        )
    }
}