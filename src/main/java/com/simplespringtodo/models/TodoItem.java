package com.simplespringtodo.models;

import java.sql.Timestamp;

/**
 * The type Todo item.
 */
public class TodoItem {

    private String content;

    private boolean isCompleted;

    private Timestamp createdAt;

    private Timestamp updatedAt;

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
    public Timestamp getCreatedAt() {

        return createdAt;
    }

    /**
     * Sets created at.
     *
     * @param createdAt the created at
     */
    public void setCreatedAt(Timestamp createdAt) {

        this.createdAt = createdAt;
    }

    /**
     * Gets updated at.
     *
     * @return the updated at
     */
    public Timestamp getUpdatedAt() {

        return updatedAt;
    }

    /**
     * Sets updated at.
     *
     * @param updatedAt the updated at
     */
    public void setUpdatedAt(Timestamp updatedAt) {

        this.updatedAt = updatedAt;
    }
}
