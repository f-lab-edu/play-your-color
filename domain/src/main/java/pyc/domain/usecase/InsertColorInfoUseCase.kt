package pyc.domain.usecase

import io.reactivex.Completable
import pyc.domain.model.ColorInfo
import pyc.domain.repository.ColorInfoRepository
import javax.inject.Inject

class InsertColorInfoUseCase @Inject constructor(
    private val colorRepository: ColorInfoRepository
) {
    operator fun invoke(color: ColorInfo) : Completable =
        colorRepository.insertColorInfo(color)
}