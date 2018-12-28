package course.intermediate.notes.notes

import course.intermediate.notes.models.Note

interface NoteListViewContract {
    fun onDeleteNote(note: Note)
}