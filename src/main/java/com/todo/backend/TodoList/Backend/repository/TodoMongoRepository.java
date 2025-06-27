package com.todo.backend.TodoList.Backend.repository;

import com.todo.backend.TodoList.Backend.models.TodoItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoMongoRepository extends MongoRepository<TodoItem, String> {

    /// This method retrieves all Todo items that are completed or not completed
    List<TodoItem> findByCompleted(boolean completed);

    /// This method retrieves all Todo items ordered by creation date in descending order
    List<TodoItem> findAllByOrderByCreatedAtDesc();
}
