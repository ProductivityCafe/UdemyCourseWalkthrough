package course.intermediate.notes.foundations

import course.intermediate.notes.create.StateModel
import course.intermediate.notes.notes.INoteModel
import course.intermediate.notes.notes.NoteLocalModel
import course.intermediate.notes.tasks.ITaskModel
import course.intermediate.notes.tasks.TaskLocalModel
import toothpick.Toothpick
import toothpick.config.Module

object ApplicationScope {
    val scope = Toothpick.openScope(this).apply {
        installModules(ApplicationModule)
    }
}

object ApplicationModule: Module() {
    init {
        bind(INoteModel::class.java).toInstance(NoteLocalModel())
        bind(ITaskModel::class.java).toInstance(TaskLocalModel())
    }
}

object CreateActivityScope {
    val scope = Toothpick.openScope(this).apply {
        installModules(CreateActivityModule)
    }
}

object CreateActivityModule: Module() {
    init {
        bind(INoteModel::class.java).to(NoteLocalModel::class.java)
        bind(ITaskModel::class.java).to(TaskLocalModel::class.java)
        bind(StateModel::class.java).toInstance(StateModel())
    }
}