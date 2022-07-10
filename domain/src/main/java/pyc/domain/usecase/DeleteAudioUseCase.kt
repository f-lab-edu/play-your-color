package pyc.domain.usecase

import io.reactivex.Completable
import pyc.domain.repository.PlaylistRepository
import javax.inject.Inject

internal class DeleteAudioUseCase @Inject constructor(
    private val playlistRepository: PlaylistRepository
) {
    operator fun invoke(
        playlistId: Int,
        currentIdList: List<Int>,
        deleteIdList: List<Int>
    ): Completable =
        playlistRepository.deleteAudio(
            playlistId,
            deleteIdList,
            currentIdList
                .filterNot { deleteIdList.contains(it) }
                .mapIndexed { index, i -> Pair(i, index) }
        )
}