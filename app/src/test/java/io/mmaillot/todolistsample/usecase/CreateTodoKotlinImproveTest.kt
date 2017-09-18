package io.mmaillot.todolistsample.usecase

import io.mmaillot.todolistsample.model.ToDo
import io.mmaillot.todolistsample.repository.ToDoRepository
import org.amshove.kluent.When
import org.amshove.kluent.`should equal to`
import org.amshove.kluent.calling
import org.amshove.kluent.itReturns
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@Suppress("IllegalIdentifier")
@RunWith(MockitoJUnitRunner::class)
class CreateTodoKotlinImproveTest {

    @Mock
    private lateinit var toDoRepository: ToDoRepository

    private lateinit var createTodo: CreateTodo

    @Before
    fun setup() {
        createTodo = CreateTodo(toDoRepository)
    }

    @Test
    fun `create should return false when title is empty`() {
        createTodo.create(ToDo(Date(), Date(), "", "content")) `should equal to` false
    }

    @Test
    fun `create should return false when content is empty`() {
        createTodo.create(ToDo(Date(), Date(), "title", "")) `should equal to` false
    }

    @Test
    fun `create should return false when deadline set in the past`() {
        createTodo.create(ToDo(Date(), Date(System.currentTimeMillis() - 3600 * 1000), "title", "")) `should equal to` false
    }

    @Test
    fun `create should return false when repository creation failed`() {
        val todo = ToDo(Date(), Date(System.currentTimeMillis() + 3600 * 1000), "title", "content")
        When calling toDoRepository.saveTodo(todo) itReturns false
        createTodo.create(todo) `should equal to` false
    }

    @Test
    fun `create should return true when repository creation success`() {
        val todo = ToDo(Date(), Date(System.currentTimeMillis() + 3600 * 1000), "title", "content")
        When calling toDoRepository.saveTodo(todo) itReturns true
        createTodo.create(todo) `should equal to` true
    }

}