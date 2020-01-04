package com.simplespringtodo.services;

import com.simplespringtodo.exceptions.CustomException;
import com.simplespringtodo.interfaces.ICRUD;
import com.simplespringtodo.models.Todo;
import com.simplespringtodo.repositories.TodoRepository;
import com.simplespringtodo.utils.CustomDate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.util.List;

/**
 * The type Todo service.
 */
@Service
public class TodoService extends CustomDate implements ICRUD<Todo> {

    private TodoRepository todoRepository;

    /**
     * Instantiates a new Todo service.
     *
     * @param todoRepository the todo repository
     */
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    /**
     * Gets todo repository.
     *
     * @return the todo repository
     */
    public TodoRepository getTodoRepository() {
        return todoRepository;
    }

    @Override
    public Todo create(Todo todo) {

        todo.setCreatedAt(this.getTimestamp());
        todo.setUpdatedAt(this.getTimestamp());

        try {
            return this.getTodoRepository().create(todo);
        } catch (Exception ex) {
            throw new CustomException("Could not create todo; please try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<Todo> list() {
        try {
            return this.getTodoRepository().list();
        } catch (Exception ex) {
            throw new CustomException("An error occurred, could not retrieve todos", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Todo findOne(long id) {
        Todo todo = this.getTodoRepository().findOne(id);
        if (todo.getId() == 0) {
            throw new CustomException("Todo not found", HttpStatus.NOT_FOUND);
        }

        return todo;
    }

    @Override
    public Todo update(Todo todo, long id) {
        Todo foundTodo = this.findOne(id);

        foundTodo.setUpdatedAt(this.getTimestamp());
        foundTodo.setTitle(todo.getTitle());

        try {
            return this.getTodoRepository().update(foundTodo, id);
        } catch (Exception ex) {
            throw new CustomException("Update operation not successful; please, try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void delete(long id) {

        this.findOne(id);

        try {
            this.getTodoRepository().delete(id);
        } catch (Exception ex) {
            throw new CustomException("Delete operation not successful; please, try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
