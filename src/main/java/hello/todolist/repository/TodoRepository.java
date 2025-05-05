package hello.todolist.repository;

import hello.todolist.domain.Todo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository //아이템 저장소
public class TodoRepository {

    private static final Map<Long, Todo> TodoItem = new HashMap<>();
    private static long sequence = 0L;

    //Todo 저장
    public Todo save(Todo todo) {
        todo.setId(++sequence);
        todo.setStatus(Todo.TodoStatus.No);
        TodoItem.put(todo.getId(), todo);
        return todo;
    }

    //Todo 삭제
    public void deleteById(Long todoId) {
        TodoItem.remove(todoId);
    }

    //Todo 수정
    public void update(Long todoId, String todoNameParam) {
        Todo findTodo = findById(todoId);
        findTodo.setTodoName(todoNameParam);
    }

    //Todo 완료
    public void done(Long todoId) {
        Todo findTodo = findById(todoId);
        findTodo.setStatus(Todo.TodoStatus.Yes);
    }

    // id로 아이템 찾기
    public Todo findById(Long id) {
        return TodoItem.get(id);
    }

    public List<Todo> findAll() {
        return new ArrayList<>(TodoItem.values());
    }


    public void clearTodo() {
        TodoItem.clear();
    }
}
