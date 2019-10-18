package course.intermediate.notes.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import course.intermediate.notes.foundations.ApplicationScope
import course.intermediate.notes.models.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import toothpick.Toothpick
import javax.inject.Inject

class NoteViewModel : ViewModel(), NoteListViewContract {

    @Inject
    lateinit var model: INoteModel

    private val _noteListLiveData: MutableLiveData<List<Note>> = MutableLiveData()
    val noteListLiveData: LiveData<List<Note>> = _noteListLiveData

    init {
        Toothpick.inject(this, ApplicationScope.scope)
        loadData()
    }

    fun loadData() {
        GlobalScope.launch(Dispatchers.Default) {
            model.retrieveNotes {nullableList ->
                nullableList?.let {
                    _noteListLiveData.postValue(it)
                }
            }
        }
    }

    override fun onDeleteNote(note: Note) {
        GlobalScope.launch {
            model.deleteNote(note) {
                if (it) {
                    loadData()
                }
            }
        }
    }
}