package course.intermediate.notes.notes

import androidx.lifecycle.ViewModel
import course.intermediate.notes.models.Note

class NoteViewModel: ViewModel() {

    fun getFakeData(): MutableList<Note> = mutableListOf(
        Note("pi is not exactly 3.14"),
        Note("A double double is Canadian for coffee two cream two sugar")
    )

}