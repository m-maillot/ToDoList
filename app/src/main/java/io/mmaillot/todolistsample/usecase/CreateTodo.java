package io.mmaillot.todolistsample.usecase;

import android.support.annotation.NonNull;

import java.util.Date;

import io.mmaillot.todolistsample.model.ToDo;
import io.mmaillot.todolistsample.repository.ToDoRepository;

public class CreateTodo {

    @NonNull
    private final ToDoRepository toDoRepository;

    public CreateTodo(@NonNull ToDoRepository pToDoRepository) {
        toDoRepository = pToDoRepository;
    }

    public boolean create(@NonNull ToDo pToDo) {
        Date currentDate = new Date(System.currentTimeMillis());
        if (pToDo.getDeadline().before(currentDate)) {
            return false;
        }
        if (pToDo.getTitle().isEmpty()) {
            return false;
        }
        if (pToDo.getContent().isEmpty()) {
            return false;
        }
        return toDoRepository.saveTodo(pToDo);
    }
}
