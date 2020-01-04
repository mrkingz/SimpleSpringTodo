package com.simplespringtodo.services;

import com.simplespringtodo.exceptions.CustomException;
import com.simplespringtodo.interfaces.ICRUD;
import com.simplespringtodo.models.Todo;
import com.simplespringtodo.models.TodoItem;
import com.simplespringtodo.repositories.TodoItemRepository;
import com.simplespringtodo.utils.CustomDate;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * The type Todo item service.
 */
public class TodoItemService extends CustomDate implements ICRUD<TodoItem> {

    private TodoItemRepository todoItemRepository;

    private TodoService todoService;

    /**
     * Instantiates a new Todo item service.
     *
     * @param todoItemRepository the todoItem repository
     * @param todoService        the todo service
     */
    public TodoItemService(TodoItemRepository todoItemRepository, TodoService todoService) {
        this.todoItemRepository = todoItemRepository;
        this.todoService = todoService;
    }

    /**
     * Instantiates a new Todo item service.
     *
     * @param todoItemRepository the todo i tem repository
     */
    public TodoItemService(TodoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }

    private TodoService getTodoService() {
        return this.todoService;
    }

    /**
     * Gets todo i tem repository.
     *
     * @return the todo i tem repository
     */
    public TodoItemRepository getTodoItemRepository() {
        return todoItemRepository;
    }

    @Override
    public TodoItem create(TodoItem todoItem) {
        // Check if todoId exist; otherwise throw a not found exception
        this.getTodoService().findOne(todoItem.getTodoId());
        try {
            todoItem.setCreatedAt(this.getTimestamp());
            todoItem.setUpdatedAt(this.getTimestamp());
            return this.getTodoItemRepository().create(todoItem);
        } catch (Exception ex) {
            throw new CustomException("Could not create todo item. Please, try again.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<TodoItem> list() {
        try {
            return this.getTodoItemRepository().list();
        } catch (Exception ex) {
            throw new CustomException("An error occurred, could not retrieve items", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public TodoItem findOne(long id) {
        TodoItem todoItem = this.getTodoItemRepository().findOne(id);
        if (todoItem.getId() == 0) {
            throw new CustomException("Todo item not found", HttpStatus.NOT_FOUND);
        }

        return todoItem;
    }

    @Override
    public TodoItem update(TodoItem todoItem, long id) {

        TodoItem foundTodoItem = this.findOne(id);

        if (foundTodoItem.isCompleted()) {
            throw new CustomException("Todo item is already completed", HttpStatus.FORBIDDEN);
        } else {
            try {
                foundTodoItem.setContent(todoItem.getContent());
                foundTodoItem.setCompleted(todoItem.isCompleted());
                foundTodoItem.setUpdatedAt(this.getTimestamp());

                return this.getTodoItemRepository().update(foundTodoItem, id);
            } catch (Exception ex) {
                throw new CustomException("Update operation not successful; please, try again.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @Override
    public void delete(long id) {
        this.findOne(id);

        try {
            this.getTodoItemRepository().delete(id);
        } catch (Exception ex) {
            throw new CustomException("Delete operation not successful; please, try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Mark todo item as completed.
     *
     * @param id the id
     * @return the todo item
     */
    public TodoItem completeTodoItem(long id) {
        TodoItem todoItem = this.findOne(id);
        todoItem.setCompleted(true);

        try {
            return this.getTodoItemRepository().update(todoItem, id);
        } catch (Exception ex) {
            throw new CustomException("Could not mark item as completed; please, try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Filter list.
     *
     * @param filter the filter
     * @return the list
     */
    public List<TodoItem> filter(boolean filter) {
        try {
            return this.getTodoItemRepository().filter(filter);
        } catch (Exception ex) {
            throw new CustomException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
