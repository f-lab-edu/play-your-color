package com.pyc.local.datasource

import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import com.github.ivanshafran.sharedpreferencesmock.SPMockBuilder
import com.pyc.local.sharedpref.PrefDatabaseImpl
import org.junit.Before
import org.junit.Test

class LastPlayerStatusInformationDatasourceImplTest {

    private lateinit var sPMockBuilder: SPMockBuilder
    private lateinit var prefDatabase: PrefDatabaseImpl
    private lateinit var lastPlayerStatusInformationDatasourceImpl: LastPlayerStatusInformationDatasourceImpl

    @Before
    fun setup() {
        sPMockBuilder = SPMockBuilder()
        prefDatabase = PrefDatabaseImpl(sPMockBuilder.createContext())
        lastPlayerStatusInformationDatasourceImpl =
            LastPlayerStatusInformationDatasourceImpl(prefDatabase)
    }

    @Test
    fun saveAndGet_currentPlayingListInfoId() {

        //given
        val id = 2

        lastPlayerStatusInformationDatasourceImpl.saveCurrentPlayingListInfoId(id).test()
            .assertComplete()
            .assertNoErrors()

        //then
        val testObserver =
            lastPlayerStatusInformationDatasourceImpl.getCurrentPlayingListInfoId().test()
                .assertOf { testObserver ->
                    testObserver.assertComplete()
                    testObserver.assertValueCount(1)
                    testObserver.assertValue { it == id }
                }
        assertThat(testObserver.values()[0], equalTo(id))
    }


    @Test
    fun saveAndGet_currentPlayingItemId() {

        //given
        val id = 3

        //when
        lastPlayerStatusInformationDatasourceImpl.saveCurrentPlayingItemId(id).test()
            .assertComplete()
            .assertNoErrors()

        //then
        val testObserver = lastPlayerStatusInformationDatasourceImpl.getCurrentPlayingListInfoId().test()
            .assertOf { testObserver ->
                testObserver.assertComplete()
                testObserver.assertValueCount(1)
                testObserver.assertValue { it == id }
            }
        assertThat(testObserver.values()[0], equalTo(id))
    }


    @Test
    fun saveAndGet_currentPlayingItemAudioPlaybackTime() {

        //given
        val time = 300L

        //when
        lastPlayerStatusInformationDatasourceImpl.saveCurrentPlayingItemAudioPlaybackTime(time)
            .test()
            .assertComplete()
            .assertNoErrors()

        //then
        val testObserver = lastPlayerStatusInformationDatasourceImpl.getCurrentPlayingItemAudioPlaybackTime().test()
            .assertOf { testObserver ->
                testObserver.assertComplete()
                testObserver.assertValueCount(1)
                testObserver.assertValue { it == time }
            }
        assertThat(testObserver.values()[0], equalTo(time))
    }

    @Test
    fun saveAndGetIsShuffleOn_setFalse_getFalse() {

        //given
        val shuffleOn = true

        //when
        lastPlayerStatusInformationDatasourceImpl.saveIsShuffleOn(shuffleOn).test()
            .assertComplete()
            .assertNoErrors()

        //then
        val testObserver = lastPlayerStatusInformationDatasourceImpl.getIsShuffleOn().test()
            .assertOf { testObserver ->
                testObserver.assertComplete()
                testObserver.assertValueCount(1)
                testObserver.assertValue { it == shuffleOn }
            }
        assertThat(testObserver.values()[0], equalTo(shuffleOn))
    }

    @Test
    fun saveAndGetIsRepeatOn_setFalse_getFalse() {

        //given
        val repeatOn = false

        //when
        lastPlayerStatusInformationDatasourceImpl.saveIsRepeatOn(repeatOn).test()
            .assertComplete()
            .assertNoErrors()

        //then
        val testObserver = lastPlayerStatusInformationDatasourceImpl.getIsRepeatOn().test()
            .assertOf { testObserver ->
                testObserver.assertComplete()
                testObserver.assertValueCount(1)
                testObserver.assertValue { it == repeatOn }
            }
        assertThat(testObserver.values()[0], equalTo(repeatOn))
    }
}