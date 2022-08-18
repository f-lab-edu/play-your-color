package pyc.domain.usecase

import pyc.domain.model.ColorInfo
import pyc.domain.repository.PlaylistRepository
import javax.inject.Inject

class UpdateColorInfoUseCase @Inject constructor(
    private val playlistRepository: PlaylistRepository
) {
    operator fun invoke(
        id: Int,
        colorInfo: ColorInfo
    ) = playlistRepository.updateColorInfo(id, colorInfo)
}