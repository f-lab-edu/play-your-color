package com.pyc.local.datasource

import com.pyc.data.datasource.LastPlayerStatusInformationDatasource
import com.pyc.local.sharedpref.PrefDatabase
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class LastPlayerStatusInformationDatasourceImpl @Inject constructor
    (private val prefDatabase: PrefDatabase) : LastPlayerStatusInformationDatasource {
    override fun getCurrentPlayingListInfoId(): Single<Int> {
        return Single.just(prefDatabase.currentPlayingListInfoId)
    }

    override fun saveCurrentPlayingListInfoId(id: Int): Completable {
        return Completable.fromAction {
            prefDatabase.currentPlayingListInfoId = id
        }
    }

    override fun getCurrentPlayingItemId(): Single<Int> {
        return Single.just(prefDatabase.currentPlayingItemId)
    }

    override fun saveCurrentPlayingItemId(id: Int): Completable {
        return Completable.fromAction {
            prefDatabase.currentPlayingItemId = id
        }
    }

    override fun getCurrentPlayingItemAudioPlaybackTime(): Single<Long> {
        return Single.just(prefDatabase.currentPlayingItemAudioPlaybackTime)
    }

    override fun saveCurrentPlayingItemAudioPlaybackTime(playbackTime: Long): Completable {
        return Completable.fromAction {
            prefDatabase.currentPlayingItemAudioPlaybackTime = playbackTime
        }
    }

    override fun getIsShuffleOn(): Single<Boolean> {
        return Single.just(prefDatabase.isShuffleOn)
    }

    override fun saveIsShuffleOn(shuffleOn: Boolean): Completable {
        return Completable.fromAction {
            prefDatabase.isShuffleOn = shuffleOn
        }
    }

    override fun getIsRepeatOn(): Single<Boolean> {
        return Single.just(
            prefDatabase.isRepeatOn
        )
    }

    override fun saveIsRepeatOn(repeatOn: Boolean): Completable {
        return Completable.fromAction {
            prefDatabase.isRepeatOn = repeatOn
        }
    }


}