package io.mmaillot.todolistsample.usecase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import io.mmaillot.todolistsample.model.ToDo;
import io.mmaillot.todolistsample.repository.ToDoRepository;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CreateTodoTest {

    @Mock
    ToDoRepository toDoRepository;

    private CreateTodo createTodo;

    @Before
    public void setup() {
        createTodo = new CreateTodo(toDoRepository);
    }

    @Test
    public void create_should_return_false_when_title_is_empty() {
        assertThat(createTodo.create(new ToDo(new Date(), new Date(), "", "content"))).isFalse();
    }

    @Test
    public void create_should_return_false_when_content_is_empty() {
        assertThat(createTodo.create(new ToDo(new Date(), new Date(), "title", ""))).isFalse();
    }

    @Test
    public void create_should_return_false_when_deadline_set_in_the_past() {
        assertThat(createTodo.create(new ToDo(new Date(), new Date(System.currentTimeMillis() - 3600 * 1000), "title", ""))).isFalse();
    }

    @Test
    public void create_should_return_false_when_repository_creation_failed() {
        ToDo todo = new ToDo(new Date(), new Date(System.currentTimeMillis() + 3600 * 1000), "title", "content");
        when(toDoRepository.saveTodo(todo)).thenReturn(false);
        assertThat(createTodo.create(todo)).isFalse();
    }

    @Test
    public void create_should_return_true_when_repository_creation_success() {
        ToDo todo = new ToDo(new Date(), new Date(System.currentTimeMillis() + 3600 * 1000), "title", "content");
        when(toDoRepository.saveTodo(todo)).thenReturn(true);
        assertThat(createTodo.create(todo)).isTrue();
    }

}