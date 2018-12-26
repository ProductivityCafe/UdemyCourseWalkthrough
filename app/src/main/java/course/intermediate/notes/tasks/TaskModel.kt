package course.intermediate.notes.tasks

import course.intermediate.notes.models.Task
import course.intermediate.notes.models.Todo
import javax.inject.Inject

class TaskModel @Inject constructor() {

    fun getFakeData(): MutableList<Task> = mutableListOf(
        Task(
            "Testing One!", mutableListOf(
                Todo("Test One!", true),
                Todo("Test Two!")
            )
        ),
        Task("Testing Two!"),
        Task(
            "Testing Three!", mutableListOf(
                Todo("Test A!"),
                Todo("Test B!")
            )
        )
    )

}