package com.todo.backend.TodoList.Backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseObject<T> {

    private String message;
    private T data;

}
