package io.mmaillot.todolistsample.repository;

import io.mmaillot.todolistsample.model.ToDo;

public interface ToDoRepository {

    boolean saveTodo(ToDo pToDo);

}
