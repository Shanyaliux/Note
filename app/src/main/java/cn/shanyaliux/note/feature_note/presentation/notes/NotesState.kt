package cn.shanyaliux.note.feature_note.presentation.notes

import cn.shanyaliux.note.feature_note.domain.model.Note
import cn.shanyaliux.note.feature_note.domain.util.NoteOrder
import cn.shanyaliux.note.feature_note.domain.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
