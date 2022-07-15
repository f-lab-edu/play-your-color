package pyc.domain.model

data class PlaylistItem(
    val id: Int,
    val audio: Audio
) {

    override fun hashCode(): Int {
        return id.hashCode() * 31
    }

    override fun equals(other: Any?): Boolean {
        if(other == null || other !is PlaylistItem) return false
        return this.id == other.id
    }
}
