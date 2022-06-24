package pyc.domain.model

data class Audio(
    val uri: String,
    val title: String = "",
    val artist: String = "",
    val duration: Int = 0,
    val imgUri: String = "",
    val mimeType: String = "",
    val colorInfoList: List<ColorInfo> = listOf(),
    val playPossible: Boolean = true,
)