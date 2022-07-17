package com.pyc.data.datasource

import io.reactivex.Completable
import io.reactivex.Single

interface LastPlayerStatusInformationDatasource {

    suspend fun getCurrentPlayingListInfoId(): Single<Int>

    suspend fun saveCurrentPlayingListInfoId(): Completable

    suspend fun getCurrentPlayingItemId(): Single<Int>

    suspend fun saveCurrentPlayingItemId(): Completable

    suspend fun getCurrentPlayingItemAudioPlaybackTime(): Single<Long>

    suspend fun saveCurrentPlayingItemAudioPlaybackTime(): Completable

    suspend fun getIsShuffleOn(): Single<Boolean>

    suspend fun saveIsShuffleOn(): Completable

    suspend fun getIsRepeatOn(): Single<Boolean>

    suspend fun saveIsRepeatOn(): Completable
}