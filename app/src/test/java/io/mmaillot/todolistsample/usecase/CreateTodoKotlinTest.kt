package io.mmaillot.todolistsample.usecase

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import java.util.Date

import io.mmaillot.todolistsample.model.ToDo
import io.mmaillot.todolistsample.repository.ToDoRepository

import org.assertj.core.api.Java6Assertions.assertThat
import org.mockito.Mockito.`when`

@RunWith(MockitoJUnitRunner::class)
class CreateTodoKotlinTest {

    @Mock
    internal var toDoRepository: ToDoRepository? = null

    private var createTodo: CreateTodo? = null

    @Before
    fun setup() {
        createTodo = CreateTodo(toDoRepository!!)
    }

    @Test
    fun create_should_return_false_when_title_is_empty() {
        assertThat(createTodo!!.create(ToDo(Date(), Date(), "", "content"))).isFalse()
    }

    @Test
    fun create_should_return_false_when_content_is_empty() {
        assertThat(createTodo!!.create(ToDo(Date(), Date(), "title", ""))).isFalse()
    }

    @Test
    fun create_should_return_false_when_deadline_set_in_the_past() {
        assertThat(createTodo!!.create(ToDo(Date(), Date(System.currentTimeMillis() - 3600 * 1000), "title", ""))).isFalse()
    }

    @Test
    fun create_should_return_false_when_repository_creation_failed() {
        val todo = ToDo(Date(), Date(System.currentTimeMillis() + 3600 * 1000), "title", "content")
        `when`(toDoRepository!!.saveTodo(todo)).thenReturn(false)
        assertThat(createTodo!!.create(todo)).isFalse()
    }

    @Test
    fun create_should_return_true_when_repository_creation_success() {
        val todo = ToDo(Date(), Date(System.currentTimeMillis() + 3600 * 1000), "title", "content")
        `when`(toDoRepository!!.saveTodo(todo)).thenReturn(true)
        assertThat(createTodo!!.create(todo)).isTrue()
    }

}