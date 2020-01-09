package com.simplespringtodo.models;

/**
 * The type Todo item.
 */
public class TodoItem {

    private long id;

    private long todoId;

    private String content;

    private boolean isCompleted;

    private String createdAt;

    private String updatedAt;

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets todo id.
     *
     * @return the todo id
     */
    public long getTodoId() {
        return todoId;
    }

    /**
     * Sets todo id.
     *
     * @param todoId the todo id
     */
    public void setTodoId(long todoId) {
        this.todoId = todoId;
    }

    /**
     * Gets content.
     *
     * @return the content
     */
    public String getContent() {

        return content;
    }

    /**
     * Sets content.
     *
     * @param content the content
     */
    public void setContent(String content) {

        this.content = content;
    }

    /**
     * Is completed boolean.
     *
     * @return the boolean
     */
    public boolean isCompleted() {

        return isCompleted;
    }

    /**
     * Sets completed.
     *
     * @param completed the completed
     */
    public void setCompleted(boolean completed) {

        isCompleted = completed;
    }

    /**
     * Gets created at.
     *
     * @return the created at
     */
    public String getCreatedAt() {

        return createdAt;
    }

    /**
     * Sets created at.
     *
     * @param createdAt the created at
     */
    public void setCreatedAt(String createdAt) {

        this.createdAt = createdAt;
    }

    /**
     * Gets updated at.
     *
     * @return the updated at
     */
    public String getUpdatedAt() {

        return updatedAt;
    }

    /**
     * Sets updated at.
     *
     * @param updatedAt the updated at
     */
    public void setUpdatedAt(String updatedAt) {

        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " { " +
                "id: " + this.getId() +
                ", todoId: " + this.getTodoId() +
                ", content: " + this.getContent() +
                ", isCompleted: " + this.isCompleted() +
                ", createdAt: " + this.getCreatedAt() +
                ", updatedAt: " + this.getUpdatedAt() +
                " }";
    }
}
