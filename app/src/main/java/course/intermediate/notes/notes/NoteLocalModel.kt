package course.intermediate.notes.notes

import android.util.Log
import course.intermediate.notes.models.Note
import javax.inject.Inject

class NoteLocalModel @Inject constructor() : INoteModel {

    override fun getFakeData(): MutableList<Note> = mutableListOf(
        Note("pi is not exactly 3.14"),
        Note("A double double is Canadian for coffee two cream two sugar")
    )

    override fun addNote(note: Note, callback: SuccessCallback) {
        Log.d("UdemyCourse", note.toString())
        callback.invoke(true)
    }

    override fun updateNote(note: Note, callback: SuccessCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteNote(note: Note, callback: SuccessCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun retrieveNotes(): List<Note> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}