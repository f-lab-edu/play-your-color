package pyc.domain.usecase

import pyc.domain.model.ColorInfo
import pyc.domain.repository.ColorInfoRepository
import pyc.domain.repository.PlaylistRepository
import javax.inject.Inject

class UpdateColorInfoUseCase @Inject constructor(
    private val colorInfoRepository: ColorInfoRepository
) {
    operator fun invoke(
        id: Int,
        colorInfo: ColorInfo
    ) = colorInfoRepository.updateColorInfo(id, colorInfo)
}