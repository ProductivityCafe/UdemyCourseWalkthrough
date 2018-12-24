package course.intermediate.notes.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import course.intermediate.notes.models.Task
import course.intermediate.notes.models.Todo

class TaskViewModel : ViewModel() {

    private val _taskListLiveData: MutableLiveData<MutableList<Task>> = MutableLiveData()
    val taskListLiveData: LiveData<MutableList<Task>> = _taskListLiveData

    init {

        _taskListLiveData.postValue(getFakeData())

    }

    fun getFakeData(): MutableList<Task> = mutableListOf(
        Task("Testing One!", mutableListOf(
            Todo("Test One!", true),
            Todo("Test Two!")
        )),
        Task("Testing Two!"),
        Task("Testing Three!", mutableListOf(
            Todo("Test A!"),
            Todo("Test B!")
        ))
    )

}