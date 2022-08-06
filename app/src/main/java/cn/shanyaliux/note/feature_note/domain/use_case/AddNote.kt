package cn.shanyaliux.note.feature_note.domain.use_case

import cn.shanyaliux.note.feature_note.domain.model.InvalidNoteException
import cn.shanyaliux.note.feature_note.domain.model.Note
import cn.shanyaliux.note.feature_note.domain.repository.NoteRepository

class AddNote(
    private val repository: NoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.content.isBlank()) {
            throw InvalidNoteException("The content of the note can't be empty.")
        }
        if (note.bookName.isBlank()) {
            throw InvalidNoteException("The bookName of the note can't be empty.")
        }
        repository.insertNote(note)
    }
}