package hello.todolist.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Todo {

    private Long id;
    private String todoName;
    private TodoStatus status;

    public Todo(String todoName) {
        this.todoName = todoName;
    }

    public enum TodoStatus {
        No, Yes
    }
}
