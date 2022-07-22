package com.pyc.local.sharedpref

import android.content.SharedPreferences

interface PrefDatabase {

    var currentPlayingListInfoId: Int
    var currentPlayingItemPosition: Int
    var currentPlayingItemAudioPlaybackTime: Long
    var isShuffleOn: Boolean
    var isRepeatOn: Boolean
}