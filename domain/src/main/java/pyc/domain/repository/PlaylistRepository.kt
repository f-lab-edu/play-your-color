package pyc.domain.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import pyc.domain.model.ColorInfo
import pyc.domain.model.PlaylistItem

interface PlaylistRepository {
    fun getPlaylist(playlistId: Int): Flowable<List<PlaylistItem>>

    fun getPlaylistByColor(
        playlistId: Int,
        colorInfoId: Int
    ): Flowable<List<PlaylistItem>>

    fun deleteAudio(
        playlistId: Int,
        deletedIdList: List<Int>,
        changedOrderList: List<Pair<Int, Int>>
    ): Completable

    fun insertAudio(
        playlistId: Int,
        startOrder: Int,
        uriList: List<String>
    ): Completable

    fun updateAudioOrder(
        playlistId: Int,
        changedOrderList: List<Pair<Int, Int>>
    ): Completable
}