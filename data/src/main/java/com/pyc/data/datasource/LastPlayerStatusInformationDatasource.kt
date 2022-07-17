package com.pyc.data.datasource

import io.reactivex.Completable
import io.reactivex.Single

interface LastPlayerStatusInformationDatasource {

    suspend fun getCurrentPlayingListInfoId(): Single<Int>

    suspend fun saveCurrentPlayingListInfoId(id: Int): Completable

    suspend fun getCurrentPlayingItemId(): Single<Int>

    suspend fun saveCurrentPlayingItemId(id: Int): Completable

    suspend fun getCurrentPlayingItemAudioPlaybackTime(): Single<Long>

    suspend fun saveCurrentPlayingItemAudioPlaybackTime(playbackTime: Long): Completable

    suspend fun getIsShuffleOn(): Single<Boolean>

    suspend fun saveIsShuffleOn(shuffleOn: Boolean): Completable

    suspend fun getIsRepeatOn(): Single<Boolean>

    suspend fun saveIsRepeatOn(repeatOn: Boolean): Completable
}