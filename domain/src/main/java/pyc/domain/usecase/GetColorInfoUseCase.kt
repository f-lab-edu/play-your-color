package pyc.domain.usecase

import io.reactivex.Flowable
import pyc.domain.model.ColorInfo
import pyc.domain.repository.ColorInfoRepository
import javax.inject.Inject

class GetColorInfoUseCase @Inject constructor(
    private val colorInfoRepository: ColorInfoRepository
) {
    operator fun invoke(id: Int): Flowable<ColorInfo> =
        colorInfoRepository.getColorInfo(id)
}