package hello.todolist.repository;

import hello.todolist.domain.Todo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class TodoRepositoryTest {

    TodoRepository todoRepository = new TodoRepository();

    @AfterEach
    void afterEach() {
        todoRepository.clearTodo();
    }

    @Test
    void save() {
        //given
        Todo todo = new Todo("homework");

        //when
        Todo savedTodo = todoRepository.save(todo);

        //then
        Todo findTodo = todoRepository.findById(todo.getId());
        assertThat(findTodo).isEqualTo(savedTodo);
    }

    @Test
    void delete() {
        //given
        Todo todo1 = new Todo("homework1");
        Todo todo2 = new Todo("homework2");
        Todo todo3 = new Todo("homework3");
        todoRepository.save(todo1);
        todoRepository.save(todo2);
        todoRepository.save(todo3);

        //when
        todoRepository.deleteById(todo1.getId());

        //then
        Assertions.assertNull(todoRepository.findById(1L), "1key delete");
        List<Todo> result = todoRepository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void done() {
        //given
        Todo todo1 = new Todo("homework1");
        todoRepository.save(todo1);

        //when
        todoRepository.done(todo1.getId());

        //then
        assertThat(todo1.getStatus()).isEqualTo(Todo.TodoStatus.Yes);
    }

    @Test
    void findAll() {
        Todo todo1 = new Todo("homework1");
        Todo todo2 = new Todo("homework2");

        todoRepository.save(todo1);
        todoRepository.save(todo2);

        List<Todo> result = todoRepository.findAll();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(todo1, todo2);
    }

    @Test
    void updateItem() {
        //given
        Todo todo1 = new Todo("homework1");

        Todo savedTodo = todoRepository.save(todo1);
        Long todoId = savedTodo.getId();

        //when
        Todo updateParam = new Todo("homework2");
        todoRepository.update(todoId, updateParam.getTodoName());

        //then
        Todo findTodo = todoRepository.findById(todoId);
        assertThat(findTodo.getTodoName()).isEqualTo(updateParam.getTodoName());

    }
}