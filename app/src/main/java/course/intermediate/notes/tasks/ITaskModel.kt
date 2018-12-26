package course.intermediate.notes.tasks

import course.intermediate.notes.models.Task

typealias SuccessCallback = (Boolean) -> Unit

interface ITaskModel {

    fun addTask(note: Task, callback: SuccessCallback)
    fun updateTask(note: Task, callback: SuccessCallback)
    fun deleteTask(note: Task, callback: SuccessCallback)
    fun retrieveTasks(): List<Task>

    fun getFakeData(): MutableList<Task>

}