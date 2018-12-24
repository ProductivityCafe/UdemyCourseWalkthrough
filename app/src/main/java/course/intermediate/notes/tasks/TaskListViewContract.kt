package course.intermediate.notes.tasks

interface TaskListViewContract {
    fun onTodoUpdated(taskIndex: Int, todoIndex: Int, isComplete: Boolean)
}