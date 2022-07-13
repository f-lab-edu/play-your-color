package com.pyc.local.sharedpref

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PrefDatabaseImpl @Inject constructor(@ApplicationContext val context: Context) : PrefDatabase {

    private val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    override var currentPlayingListInfoId: Int
        get() = sharedPreferences.getValue(CURRENT_PLAYLIST_INFO_ID, -1)
        set(value) {
            sharedPreferences.setValue(CURRENT_PLAYLIST_INFO_ID, value)
        }

    override var currentPlayingItemId: Int
        get() = sharedPreferences.getValue(CURRENT_PLAYING_ITEM_ID, -1)
        set(value) {
            sharedPreferences.setValue(CURRENT_PLAYING_ITEM_ID, value)
        }

    override var currentPlayingItemAudioPlaybackTime: Long
        get() = sharedPreferences.getValue(CURRENT_PLAYING_ITEM_AUDIO_PLAYBACK_TIME, 0L)
        set(value) {
            sharedPreferences.setValue(CURRENT_PLAYING_ITEM_AUDIO_PLAYBACK_TIME, value)
        }

    override var isShuffleOn: Boolean
        get() = sharedPreferences.getValue(IS_SHUFFLE_ON, false)
        set(value) {
            sharedPreferences.setValue(IS_SHUFFLE_ON, value)
        }

    override var isRepeatOn: Boolean
        get() = sharedPreferences.getValue(IS_REPEAT_ON, false)
        set(value) {
            sharedPreferences.setValue(IS_REPEAT_ON, value)
        }





    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val edit = this.edit()
        operation(edit)
        edit.apply()
    }

    private fun SharedPreferences.setValue(key: String, value: Any?) {
        when (value) {
            is String? -> edit { it.putString(key, value) }
            is Int -> edit { it.putInt(key, value) }
            is Boolean -> edit { it.putBoolean(key, value) }
            is Float -> edit { it.putFloat(key, value) }
            is Long -> edit { it.putLong(key, value) }
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    private inline fun <reified T : Any> SharedPreferences.getValue(key: String, defaultValue: T? = null): T {
        return when (T::class) {
            String::class -> getString(key, defaultValue as? String ?: "") as T
            Int::class -> getInt(key, defaultValue as? Int ?: -1) as T
            Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T
            Float::class -> getFloat(key, defaultValue as? Float ?: 0f) as T
            Long::class -> getLong(key, defaultValue as? Long ?: 0L) as T
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }


    companion object {
        private const val PREF_NAME = "play_your_color_shared_pref"
        private const val CURRENT_PLAYLIST_INFO_ID = "current_playlist_info_id"
        private const val CURRENT_PLAYING_ITEM_ID = "current_playing_item_id"
        private const val CURRENT_PLAYING_ITEM_AUDIO_PLAYBACK_TIME = "current_playing_item_audio_playback_time"
        private const val IS_SHUFFLE_ON = "is_shuffle_on"
        private const val IS_REPEAT_ON = "is_repeat_on"
    }
}