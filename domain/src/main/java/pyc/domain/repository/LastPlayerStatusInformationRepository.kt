package pyc.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import pyc.domain.model.LastPlayerStatusInformation

interface LastPlayerStatusInformationRepository {
    fun getCurrentPlayingListInfoId(): Single<Int>

    fun saveCurrentPlayingListInfoId(id: Int): Completable

    fun getCurrentPlayingItemPosition(): Single<Int>

    fun saveCurrentPlayingItemPosition(position: Int): Completable

    fun getCurrentPlayingItemAudioPlaybackTime(): Single<Long>

    fun saveCurrentPlayingItemAudioPlaybackTime(playbackTime: Long): Completable

    fun getIsShuffleOn(): Single<Boolean>

    fun saveIsShuffleOn(shuffleOn: Boolean): Completable

    fun getIsRepeatOn(): Single<Boolean>

    fun saveIsRepeatOn(repeatOn: Boolean): Completable

    fun getLastPlayerStatusInformation() : Single<LastPlayerStatusInformation>

    fun saveLastPlayerStatusInformation(lastPlayerStatusInformation: LastPlayerStatusInformation) : Completable
}