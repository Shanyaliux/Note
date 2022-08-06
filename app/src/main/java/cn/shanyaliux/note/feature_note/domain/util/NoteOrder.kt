package cn.shanyaliux.note.feature_note.domain.util

sealed class NoteOrder(val orderType: OrderType) {
    class BookName(orderType: OrderType): NoteOrder(orderType)
    class Date(orderType: OrderType): NoteOrder(orderType)
    class Color(orderType: OrderType): NoteOrder(orderType)

    fun copy(orderType: OrderType): NoteOrder {
        return when (this) {
            is BookName -> BookName(orderType)
            is Date -> Date(orderType)
            is Color -> Color(orderType)
        }
    }
}

