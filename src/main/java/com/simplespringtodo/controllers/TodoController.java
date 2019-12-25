package com.simplespringtodo.controllers;

import com.simplespringtodo.models.Todo;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Todo controller.
 */
@RestController
public class TodoController {

    /**
     * Create todo.
     *
     * @param todo the todo
     * @return the todo
     */
    @PostMapping(value = "/todos",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    public Todo create(@RequestBody Todo todo) {

        return todo;
    }
}
