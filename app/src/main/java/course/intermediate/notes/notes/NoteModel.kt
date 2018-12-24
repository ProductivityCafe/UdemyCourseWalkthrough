package course.intermediate.notes.notes

import course.intermediate.notes.models.Note

class NoteModel {

    fun getFakeData(): MutableList<Note> = mutableListOf(
        Note("pi is not exactly 3.14"),
        Note("A double double is Canadian for coffee two cream two sugar")
    )

}