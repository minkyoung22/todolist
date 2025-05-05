package hello.todolist.controller;

import hello.todolist.domain.Todo;
import hello.todolist.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/todoMain")
@RequiredArgsConstructor
public class TodoController {

    private final TodoRepository todoRepository;

    @GetMapping
    public String todos(Model model) {
        List<Todo> todos = todoRepository.findAll();
        model.addAttribute("todos", todos); //model에 넣어줘야 뷰로 전달
        return "todoMain";
    }

    @PostMapping("/add")
    public String addTodo(Todo todo) {
        todoRepository.save(todo);
        return "redirect:/todoMain";
    }

    @PostMapping("/delete")
    public String deleteTodo(@RequestParam Long todoId) {
        todoRepository.deleteById(todoId);
        return "redirect:/todoMain";
    }

    @GetMapping("/{todoId}/update")
    public String updateTodo(@PathVariable("todoId") long todoId, Model model) {
        Todo todo = todoRepository.findById(todoId);
        model.addAttribute("todo", todo);
        return "todoUpdate";
    }

    @PostMapping("/{todoId}/update")
    public String updateTodo(@PathVariable("todoId") long todoId, @RequestParam("todoName") String todoName) {
        todoRepository.update(todoId, todoName);
        return "redirect:/todoMain";
    }

    @PostMapping("done")
    public String doneTodo(@RequestParam Long todoId) {
        todoRepository.done(todoId);
        return "redirect:/todoMain";
    }

}
