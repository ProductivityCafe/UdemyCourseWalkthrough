package course.intermediate.notes.tasks

import course.intermediate.notes.models.Task
import course.intermediate.notes.models.Todo

typealias SuccessCallback = (Boolean) -> Unit

interface ITaskModel {

    suspend fun addTask(task: Task, callback: SuccessCallback)
    suspend fun updateTask(task: Task, callback: SuccessCallback)
    suspend fun updateTodo(todo: Todo, callback: SuccessCallback)
    suspend fun deleteTask(task: Task, callback: SuccessCallback)
    fun retrieveTasks(callback: (List<Task>?) -> Unit)
}