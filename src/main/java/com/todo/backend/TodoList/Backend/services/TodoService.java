package com.todo.backend.TodoList.Backend.services;

import com.todo.backend.TodoList.Backend.models.TodoItem;
import com.todo.backend.TodoList.Backend.models.TodoRequest;
import com.todo.backend.TodoList.Backend.repository.TodoMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoMongoRepository todoMongoRepository;

    public List<TodoItem> getAllTodos(Boolean completed) {
        if (completed == null) {
            // If completed is null, return all Todo items ordered by creation date
            return todoMongoRepository.findAllByOrderByCreatedAtDesc();
        }
        return todoMongoRepository.findByCompleted(completed);
    }

    public void deleteTodoItem(String id) {
        todoMongoRepository.deleteById(id);
    }

    public TodoItem addTodoItem(TodoRequest request) {
        TodoItem todoItem = new TodoItem();
        todoItem.setTask(request.getTask());
        todoItem.setCompleted(false);
        todoItem.setCreatedAt(new java.util.Date());
        TodoItem savedTodoItem = todoMongoRepository.save(todoItem);
        return savedTodoItem;
    }

    public TodoItem setTaskCompletion(String id, boolean completed) throws Exception {
        TodoItem todoItem = todoMongoRepository.findById(id)
                .orElseThrow(() -> new Exception("Todo item not found"));
        todoItem.setCompleted(completed);
        return todoMongoRepository.save(todoItem);
    }
}
