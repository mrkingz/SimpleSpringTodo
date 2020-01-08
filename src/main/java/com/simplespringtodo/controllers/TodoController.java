package com.simplespringtodo.controllers;

import com.simplespringtodo.models.Todo;
import com.simplespringtodo.services.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Todo controller.
 */
@RestController
public class TodoController {

    private final TodoService todoService;

    /**
     * Instantiates a new Todo controller.
     *
     * @param todoService the todo service
     */
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    /**
     * Gets todo service.
     *
     * @return the todo service
     */
    public TodoService getTodoService() {
        return todoService;
    }

    /**
     * Create todo.
     *
     * @param todo the todo
     * @return the todo
     */
    @PostMapping(value = "/todos", consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    public Todo create(@RequestBody Todo todo) {

        this.getTodoService().create(todo);
        return todo;
    }

    /**
     * List todos list.
     *
     * @return the list
     */
    @GetMapping(value = "/todos")
    @ResponseStatus(HttpStatus.OK)
    public List<Todo> listTodos() {
        return this.getTodoService().list();
    }

    /**
     * Find todo todo.
     *
     * @param id the id
     * @return the todo
     */
    @GetMapping(value = "/todos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Todo findTodo(@PathVariable("id") long id) {
        return this.getTodoService().findOne(id);
    }

    /**
     * Update todo todo.
     *
     * @param todo the todo
     * @param id   the id
     * @return the todo
     */
    @PutMapping(value = "todos/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Todo updateTodo(@RequestBody Todo todo, @PathVariable("id") long id) {
        return this.getTodoService().update(todo, id);
    }

    /**
     * Delete todo.
     *
     * @param id the id
     */
    @DeleteMapping(value = "todos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo (@PathVariable("id") long id) {
        this.getTodoService().delete(id);
    }

}
