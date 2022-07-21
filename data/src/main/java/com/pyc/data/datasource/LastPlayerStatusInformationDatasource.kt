package com.pyc.data.datasource

import io.reactivex.Completable
import io.reactivex.Single

interface LastPlayerStatusInformationDatasource {

    fun getCurrentPlayingListInfoId(): Single<Int>

    fun saveCurrentPlayingListInfoId(id: Int): Completable

    fun getCurrentPlayingItemId(): Single<Int>

    fun saveCurrentPlayingItemId(id: Int): Completable

    fun getCurrentPlayingItemAudioPlaybackTime(): Single<Long>

    fun saveCurrentPlayingItemAudioPlaybackTime(playbackTime: Long): Completable

    fun getIsShuffleOn(): Single<Boolean>

    fun saveIsShuffleOn(shuffleOn: Boolean): Completable

    fun getIsRepeatOn(): Single<Boolean>

    fun saveIsRepeatOn(repeatOn: Boolean): Completable
}