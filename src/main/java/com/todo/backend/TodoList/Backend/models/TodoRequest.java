package com.todo.backend.TodoList.Backend.models;

import lombok.Data;

import java.util.Date;

@Data
public class TodoRequest {
    private String task = "";
    private boolean completed = false;
}
