package pyc.domain.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import pyc.domain.model.PlaylistItem

interface PlaylistRepository {
    fun getPlaylist(playlistId: Int): Flowable<List<PlaylistItem>>

    fun getPlaylistByColor(playlistId: Int, colorInfoId: Int): Flowable<List<PlaylistItem>>

    fun deleteAudio(deletedIdList: List<Int>, changedOrderList: List<Pair<Int, Int>>): Completable

    fun insertAudio(startOrder: Int, uriList: List<String>): Completable

    //                                              id, to
    fun updateAudioOrder(changedOrderList: List<Pair<Int, Int>>): Completable
}