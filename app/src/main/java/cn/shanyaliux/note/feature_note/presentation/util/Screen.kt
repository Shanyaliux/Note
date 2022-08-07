package cn.shanyaliux.note.feature_note.presentation.util

sealed class Screen(val title:String, val route: String) {
    object NotesScreen: Screen("书笺", "notes_screen")
    object AddEditNoteScreen: Screen("新建", "add_edit_note_screen")
    object About : Screen( "关于", "about")
}
