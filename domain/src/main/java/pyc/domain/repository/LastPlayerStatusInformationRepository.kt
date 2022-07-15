package pyc.domain.repository

interface LastPlayerStatusInformationRepository {
    var currentPlayingListInfoId: Int
    var currentPlayingItemId: Int
    var currentPlayingItemAudioPlaybackTime: Long
    var isShuffleOn: Boolean
    var isRepeatOn: Boolean
}