package pyc.domain.model

data class LastPlayerStatusInformation(
    val currentPlayingListInfoId: Int,
    val currentPlayingItemId: Int,
    val currentPlayingItemAudioPlaybackTime: Long,
    val isShuffleOn: Boolean,
    val isRepeatOn: Boolean
)
