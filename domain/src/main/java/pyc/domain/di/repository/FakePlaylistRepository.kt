package pyc.domain.di.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import pyc.domain.model.Audio
import pyc.domain.model.ColorInfo
import pyc.domain.model.PlaylistItem
import pyc.domain.repository.PlaylistRepository
import javax.inject.Inject

class FakePlaylistRepository @Inject constructor() : PlaylistRepository {

    private val dummyData = listOf<PlaylistItem>(
        PlaylistItem(1, Audio("", "붉은노을", "빅뱅", 140, "https://picsum.photos/id/237/200/300", "", emptyList(), true)),
        PlaylistItem(2, Audio("", "붉은노을2", "빅뱅", 140, "", "", emptyList(), true)),
        PlaylistItem(3, Audio("", "붉은노을3", "빅뱅", 140, "", "", emptyList(), true)),
        PlaylistItem(4, Audio("", "붉은노을4", "빅뱅", 140, "", "", emptyList(), true)),
        PlaylistItem(5, Audio("", "붉은노을5", "빅뱅", 140, "", "", emptyList(), true)),
        PlaylistItem(6, Audio("", "붉은노을6", "빅뱅", 140, "", "", emptyList(), true)),
        PlaylistItem(7, Audio("", "붉은노을7", "빅뱅", 140, "", "", emptyList(), true)),
        PlaylistItem(8, Audio("", "붉은노을8", "빅뱅", 140, "", "", emptyList(), true)),
        PlaylistItem(9, Audio("", "붉은노을9", "빅뱅", 140, "", "", emptyList(), true)),
        PlaylistItem(10, Audio("", "붉은노을10", "빅뱅", 140, "", "", emptyList(), true)),
        PlaylistItem(11, Audio("", "붉은노을11", "빅뱅", 140, "", "", emptyList(), true)),
    )

    private val dummyData2 = ColorInfo(1, "0xFFFFFFFF", "검은")

    override fun getPlaylist(playlistId: Int): Flowable<List<PlaylistItem>> {
        return Flowable.just(dummyData)
    }

    override fun getPlaylistByColor(
        playlistId: Int,
        colorInfoId: Int
    ): Flowable<List<PlaylistItem>> {
        return Flowable.just(dummyData)
    }

    override fun deleteAudio(
        playlistId: Int,
        deletedIdList: List<Int>,
        changedOrderList: List<Pair<Int, Int>>
    ): Completable {
        return Completable.complete()
    }

    override fun insertAudio(playlistId: Int, startOrder: Int, uriList: List<String>): Completable {
        return Completable.complete()
    }

    override fun updateAudioOrder(
        playlistId: Int,
        changedOrderList: List<Pair<Int, Int>>
    ): Completable {
        return Completable.complete()
    }

    override fun updateColorInfo(id: Int, colorInfo: ColorInfo): Completable {
        return Completable.complete()
    }

    override fun getColorInfo(id: Int): Flowable<ColorInfo> {
        return Flowable.just(dummyData2)
    }
}