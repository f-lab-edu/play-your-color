package pyc.domain.usecase

import io.reactivex.Flowable
import pyc.domain.model.ColorInfoListItem
import pyc.domain.repository.ColorInfoRepository
import javax.inject.Inject

class GetColorInfoListUseCase @Inject constructor(
    private val colorRepository: ColorInfoRepository
) {
    operator fun invoke() : Flowable<List<ColorInfoListItem>> =
        colorRepository.getColorInfoList()
}