package course.intermediate.notes.notes

import course.intermediate.notes.models.Note

typealias SuccessCallback = (Boolean) -> Unit

interface INoteModel {

    suspend fun addNote(note: Note, callback: SuccessCallback)
    suspend fun updateNote(note: Note, callback: SuccessCallback)
    suspend fun deleteNote(note: Note, callback: SuccessCallback)
    suspend fun retrieveNotes(callback: (List<Note>?) -> Unit)

}