package pyc.domain.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import pyc.domain.model.Audio

interface PrimaryPlaylistRepository {
    fun getAudioList(): Flowable<List<Audio>>

    fun getAudioList(colorInfoId: Int): Flowable<List<Audio>>

    fun deleteAudio(deletedOrderList: List<Int>): Completable

    fun insertAudio(startOrder: Int, uriList: List<String>): Completable

    //                                              from, to
    fun updateAudioOrder(changedOrderList: List<Pair<Int, Int>>): Completable
}