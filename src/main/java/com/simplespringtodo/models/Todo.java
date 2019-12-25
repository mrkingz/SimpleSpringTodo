package com.simplespringtodo.models;

/**
 * The type Todo.
 */
public class Todo {

    private String title;

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{")
                .append("title: ").append(this.getTitle())
                .append("}");

        return stringBuilder.toString();
    }
}
