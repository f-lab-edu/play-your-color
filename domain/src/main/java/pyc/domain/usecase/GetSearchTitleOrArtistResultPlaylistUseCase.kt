package pyc.domain.usecase


import io.reactivex.Flowable
import pyc.domain.common.containsIgnoreCase
import pyc.domain.model.PlaylistItem

class GetSearchTitleOrArtistResultPlaylistUseCase {

    operator fun invoke(word: String, list: List<PlaylistItem>): List<PlaylistItem> {

        return list.filter { item ->
            item.audio.title.containsIgnoreCase(word) || item.audio.artist.containsIgnoreCase(word)
        }
    }
}