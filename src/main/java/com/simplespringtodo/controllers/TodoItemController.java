package com.simplespringtodo.controllers;

import com.simplespringtodo.exceptions.CustomException;
import com.simplespringtodo.models.TodoItem;
import com.simplespringtodo.services.TodoItemService;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * The type Todo item controller.
 */
@RestController
public class TodoItemController {

    private TodoItemService todoItemService;

    /**
     * Instantiates a new Todo item controller.
     *
     * @param todoItemService the todo item service
     */
    public TodoItemController(TodoItemService todoItemService) {
        this.todoItemService = todoItemService;
    }

    /**
     * Gets todo item service.
     *
     * @return the todo item service
     */
    public TodoItemService getTodoItemService() {
        return todoItemService;
    }


    /**
     * Create todo item todo item.
     *
     * @param todoItem the todo item
     * @param id       the id
     * @return the todo item
     */
    @PostMapping(value = "todos/{id}/todoItems", consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    public TodoItem createTodoItem(@RequestBody TodoItem todoItem, @PathVariable("id") long id) {
        todoItem.setTodoId(id);
        return this.getTodoItemService().create(todoItem);
    }


    /**
     * Find todo item todo item.
     *
     * @param id the id
     * @return the todo item
     */
    @GetMapping(value = "/todoItems/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TodoItem findTodoItem(@PathVariable("id") long id) {
        return this.getTodoItemService().findOne(id);
    }

    /**
     * Gets all todo items.
     *
     * @return the all todo items
     */
    @GetMapping(path = "/todoItems")
    @ResponseStatus(HttpStatus.OK)
    public List<TodoItem> getAllTodoItems(@RequestParam(name = "completed") Optional<String> complete) {
        return complete.isPresent()
                ? this.getTodoItemService().filter(Boolean.parseBoolean(complete.get()))
                : this.getTodoItemService().list();
    }


    /**
     * Update todo item.
     *
     * @param todoItem the todo item
     * @param id       the id
     * @return the todo item
     */
    @PutMapping(value = "/todoItems/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TodoItem update(@RequestBody TodoItem todoItem, @PathVariable("id") long id) {
        todoItem.setCompleted(false);
        return this.getTodoItemService().update(todoItem, id);
    }


    /**
     * Delete todo item.
     *
     * @param id the id
     */
    @DeleteMapping(value = "/todoItems/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodoItem (@PathVariable("id") long id) {
        this.getTodoItemService().delete(id);
    }


    /**
     * Mark todo item as completed.
     *
     * @param id the id
     * @return the todo item
     */
    @PutMapping(value = "/todoItems/{id}/completed")
    @ResponseStatus(HttpStatus.OK)
    public TodoItem completed(@PathVariable("id") long id) {
        return this.getTodoItemService().completeTodoItem(id);
    }
}
