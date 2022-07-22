package pyc.domain.usecase

import io.reactivex.Completable
import pyc.domain.model.LastPlayerStatusInformation
import pyc.domain.repository.LastPlayerStatusInformationRepository
import javax.inject.Inject

class SaveLastPlayerStatusInformationUseCase @Inject constructor(
    private val lastPlayerStatusInformationRepository: LastPlayerStatusInformationRepository
) {
    operator fun invoke(
        information: LastPlayerStatusInformation
    ): Completable =
        lastPlayerStatusInformationRepository.saveLastPlayerStatusInformation(information)

}