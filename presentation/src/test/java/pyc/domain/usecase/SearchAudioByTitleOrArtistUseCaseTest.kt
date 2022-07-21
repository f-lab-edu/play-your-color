package pyc.domain.usecase

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.CoreMatchers.*


import org.junit.Assert.*
import org.junit.Test
import pyc.domain.model.Audio
import pyc.domain.model.PlaylistItem

class SearchAudioByTitleOrArtistUseCaseTest() {

    private val list = listOf<PlaylistItem>(
        PlaylistItem(id = 1, Audio(uri = "sdfdf", title = "abcsss", "dkdk")),
        PlaylistItem(id = 2, Audio(uri = "ssss", title = "ddd", "ABCaaaa")),
        PlaylistItem(id = 3, Audio(uri = "sdfdf", title = "aaa", "aaaa")),
        PlaylistItem(id = 4, Audio(uri = "sdfdf", title = "ssff", "aaaa")),
        PlaylistItem(id = 3, Audio(uri = "sdfdf", title = "하하", "aaaa"))
    )

    @Test
    fun searchAudioByTitleOrArtistUseCase_alphabet_ignoreCase_containWordList() {
        //Given
        val word = "abc"

        //When
        val resultList = SearchAudioByTitleOrArtistUseCase().invoke(word, list)

        //Then
        assertThat(
            resultList, equalTo(
                listOf<PlaylistItem>(
                    PlaylistItem(id = 1, Audio(uri = "sdfdf", title = "abcsss", "dkdk")),
                    PlaylistItem(id = 2, Audio(uri = "ssss", title = "ddd", "ABCaaaa"))
                )
            )
        )
    }


    @Test
    fun searchAudioByTitleOrArtistUseCase_hangeul_containWordList() {
        //Given
        val word = "하하"

        //When
        val resultList = SearchAudioByTitleOrArtistUseCase().invoke(word, list)

        //Then
        assertThat(
            resultList, equalTo(
                listOf<PlaylistItem>(
                    PlaylistItem(id = 3, Audio(uri = "sdfdf", title = "하하", "aaaa"))
                )
            )
        )
    }
}