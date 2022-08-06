package cn.shanyaliux.note.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import cn.shanyaliux.note.ui.theme.*

@Entity
data class Note(
    val content: String,
    val bookName: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidNoteException(message: String) : Exception(message) {

}
