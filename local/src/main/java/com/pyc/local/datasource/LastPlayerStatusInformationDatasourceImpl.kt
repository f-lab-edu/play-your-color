package com.pyc.local.datasource

import com.pyc.data.datasource.LastPlayerStatusInformationDatasource
import com.pyc.local.sharedpref.PrefDatabase
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class LastPlayerStatusInformationDatasourceImpl @Inject constructor
    (private val prefDatabase: PrefDatabase) : LastPlayerStatusInformationDatasource {
    override suspend fun getCurrentPlayingListInfoId(): Single<Int> {
        return Single.just(prefDatabase.currentPlayingListInfoId)
    }

    override suspend fun saveCurrentPlayingListInfoId(id: Int): Completable {
        return Completable.fromAction {
            prefDatabase.currentPlayingListInfoId = id
        }
    }

    override suspend fun getCurrentPlayingItemId(): Single<Int> {
        return Single.just(prefDatabase.currentPlayingItemId)
    }

    override suspend fun saveCurrentPlayingItemId(id: Int): Completable {
        return Completable.fromAction {
            prefDatabase.currentPlayingItemId = id
        }
    }

    override suspend fun getCurrentPlayingItemAudioPlaybackTime(): Single<Long> {
        return Single.just(prefDatabase.currentPlayingItemAudioPlaybackTime)
    }

    override suspend fun saveCurrentPlayingItemAudioPlaybackTime(playbackTime: Long): Completable {
        return Completable.fromAction {
            prefDatabase.currentPlayingItemAudioPlaybackTime = playbackTime
        }
    }

    override suspend fun getIsShuffleOn(): Single<Boolean> {
        return Single.just(prefDatabase.isShuffleOn)
    }

    override suspend fun saveIsShuffleOn(shuffleOn: Boolean): Completable {
        return Completable.fromAction {
            prefDatabase.isShuffleOn = shuffleOn
        }
    }

    override suspend fun getIsRepeatOn(): Single<Boolean> {
        return Single.just(
            prefDatabase.isRepeatOn
        )
    }

    override suspend fun saveIsRepeatOn(repeatOn: Boolean): Completable {
        return Completable.fromAction {
            prefDatabase.isRepeatOn = repeatOn
        }
    }


}