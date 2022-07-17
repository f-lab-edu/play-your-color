package com.pyc.local.datasource

import com.github.ivanshafran.sharedpreferencesmock.SPMockBuilder
import com.pyc.local.sharedpref.PrefDatabaseImpl
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit

class LastPlayerStatusInformationDatasourceImplTest {

    private lateinit var sPMockBuilder : SPMockBuilder
    private lateinit var prefDatabase: PrefDatabaseImpl
    private lateinit var lastPlayerStatusInformationDatasourceImpl: LastPlayerStatusInformationDatasourceImpl
    private val waitTime = 500L

    @Before
    fun setup() {
        sPMockBuilder = SPMockBuilder()
        prefDatabase = PrefDatabaseImpl(sPMockBuilder.createContext())
        lastPlayerStatusInformationDatasourceImpl =
            LastPlayerStatusInformationDatasourceImpl(prefDatabase)
    }

    @Test
    fun setAndGet_currentPlayingListInfoId() {

        //given
        val id = 2

        //then
        lastPlayerStatusInformationDatasourceImpl.saveCurrentPlayingListInfoId(id).test()
            .awaitDone(waitTime, TimeUnit.MILLISECONDS)
            .assertComplete()
            .assertNoErrors()

        lastPlayerStatusInformationDatasourceImpl.getCurrentPlayingListInfoId().test()
            .awaitDone(waitTime, TimeUnit.MILLISECONDS)
            .assertOf { testObserver ->
            testObserver.assertValue { it == id }
        }
    }


    @Test
    fun setAndGet_currentPlayingItemId() {

        //given
        val id = 3

        //then
        lastPlayerStatusInformationDatasourceImpl.saveCurrentPlayingItemId(id).test()
            .awaitDone(waitTime, TimeUnit.MILLISECONDS)
            .assertComplete()
            .assertNoErrors()

        lastPlayerStatusInformationDatasourceImpl.getCurrentPlayingListInfoId().test()
            .awaitDone(waitTime, TimeUnit.MILLISECONDS)
            .assertOf { testObserver ->
                testObserver.assertValue { it == id }
            }
    }


    @Test
    fun setAndGet_currentPlayingItemAudioPlaybackTime() {

        //given
        val time = 300L

        //then
        lastPlayerStatusInformationDatasourceImpl.saveCurrentPlayingItemAudioPlaybackTime(time).test()
            .awaitDone(waitTime, TimeUnit.MILLISECONDS)
            .assertComplete()
            .assertNoErrors()

        lastPlayerStatusInformationDatasourceImpl.getCurrentPlayingItemAudioPlaybackTime().test()
            .awaitDone(waitTime, TimeUnit.MILLISECONDS)
            .assertOf { testObserver ->
                testObserver.assertValue { it == time }
            }
    }

    @Test
    fun setAndGet_isShuffleOn() {

        //given
        val isTrue = true

        //then
        lastPlayerStatusInformationDatasourceImpl.saveIsShuffleOn(isTrue).test()
            .awaitDone(waitTime, TimeUnit.MILLISECONDS)
            .assertComplete()
            .assertNoErrors()

        lastPlayerStatusInformationDatasourceImpl.getIsShuffleOn().test()
            .awaitDone(waitTime, TimeUnit.MILLISECONDS)
            .assertOf { testObserver ->
                testObserver.assertValue { it == isTrue}
            }
    }

    @Test
    fun setAndGet_isRepeatOn() {

        //given
        val isTrue = false

        //then
        lastPlayerStatusInformationDatasourceImpl.saveIsRepeatOn(isTrue).test()
            .awaitDone(waitTime, TimeUnit.MILLISECONDS)
            .assertComplete()
            .assertNoErrors()

        lastPlayerStatusInformationDatasourceImpl.getIsRepeatOn().test()
            .awaitDone(waitTime, TimeUnit.MILLISECONDS)
            .assertOf { testObserver ->
                testObserver.assertValue { it == isTrue}
            }
    }


}