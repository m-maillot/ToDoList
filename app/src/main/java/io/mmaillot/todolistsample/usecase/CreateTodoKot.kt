package io.mmaillot.todolistsample.usecase

import java.util.Date

import io.mmaillot.todolistsample.model.ToDo
import io.mmaillot.todolistsample.repository.ToDoRepository

class CreateTodoKot(private val toDoRepository: ToDoRepository) {

    fun create(pToDo: ToDo): Boolean {
        val currentDate = Date(System.currentTimeMillis())
        if (pToDo.deadline.before(currentDate)) {
            return false
        }
        if (pToDo.title == "") {
            return false
        }
        return if (pToDo.content == "") {
            false
        } else toDoRepository.saveTodo(pToDo)
    }
}
