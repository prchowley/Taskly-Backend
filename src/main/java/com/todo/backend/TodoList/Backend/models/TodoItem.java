package com.todo.backend.TodoList.Backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoItem {

    @Id
    public String id;
    public String task;
    public boolean completed;
    public Date createdAt;

}
