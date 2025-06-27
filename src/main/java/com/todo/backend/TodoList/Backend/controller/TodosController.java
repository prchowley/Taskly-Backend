package com.todo.backend.TodoList.Backend.controller;

import com.todo.backend.TodoList.Backend.models.TodoRequest;
import com.todo.backend.TodoList.Backend.models.ResponseObject;
import com.todo.backend.TodoList.Backend.models.TodoItem;
import com.todo.backend.TodoList.Backend.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.Thread.sleep;

@RestController
@RequestMapping("/todos")
public class TodosController {

    @Autowired
    private TodoService todoService;

    @PostMapping
    public ResponseEntity<ResponseObject<TodoItem>> addTodo(@RequestBody TodoRequest task) {
        if (task.getTask() == null || task.getTask().isEmpty()) {
            return ResponseEntity.badRequest().body(
                new ResponseObject<>("Task cannot be empty", null)
            );
        }
        // This method will add a new Todo item
        TodoItem id = todoService.addTodoItem(task);
        return ResponseEntity.ok(
            new ResponseObject<>("Todo item added successfully", id)
        );
    }

    @GetMapping
    public ResponseEntity<List<TodoItem>> getTodos(@RequestParam (required = false) Boolean completed) throws InterruptedException {
        return ResponseEntity.ok(
            todoService.getAllTodos(completed)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject<String>> deleteTodo(@PathVariable String id) throws InterruptedException {
        long sleepTime = 5 * 1000;
        sleep(sleepTime);
        todoService.deleteTodoItem(id);
        return ResponseEntity.ok(
            new ResponseObject<>("Todo item deleted successfully", id)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseObject<TodoItem>> toggleTodo(@PathVariable String id, @RequestBody TodoRequest request) {
        // This method will toggle the completed status of a Todo item
        try {
            TodoItem updatedTodo = todoService.setTaskCompletion(id, request.isCompleted());
            return ResponseEntity.ok(
                new ResponseObject<>("Todo item updated successfully", updatedTodo)
            );
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                new ResponseObject<>("Error updating Todo item: " + e.getMessage(), null)
            );
        }
    }

    @GetMapping("/health")
    public ResponseEntity<ResponseObject<String>> healthCheck() {
        return ResponseEntity.ok(
            new ResponseObject<>("Service is running", "OK")
        );
    }
}
