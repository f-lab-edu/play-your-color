package pyc.domain.usecase

import io.reactivex.Completable
import pyc.domain.repository.ColorInfoRepository
import javax.inject.Inject

class DeleteColorInfoUseCase @Inject constructor(
    private val colorInfoRepository: ColorInfoRepository
) {
    operator fun invoke(id: Int) : Completable =
        colorInfoRepository.deleteInfoColor(id)
}