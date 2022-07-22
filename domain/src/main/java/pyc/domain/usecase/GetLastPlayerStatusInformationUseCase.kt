package pyc.domain.usecase

import io.reactivex.Single
import pyc.domain.model.LastPlayerStatusInformation
import pyc.domain.repository.LastPlayerStatusInformationRepository
import javax.inject.Inject

class GetLastPlayerStatusInformationUseCase @Inject constructor(
    private val lastPlayerStatusInformationRepository: LastPlayerStatusInformationRepository
) {
    operator fun invoke() : Single<LastPlayerStatusInformation> =
        lastPlayerStatusInformationRepository.getLastPlayerStatusInformation()
}