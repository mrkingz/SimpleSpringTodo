package com.simplespringtodo.interfaces;

import java.util.List;

/**
 * The interface Icrud.
 *
 * @param <T> the type parameter
 */
public interface ICRUD<T> {
    /**
     * Create an instance of T.
     *
     * @param t the new instance of T to be persisted
     * @return the created instance of T
     */
    T create(T t);

    /**
     * List all instances of T.
     *
     * @return the list
     */
    List<T> list();

    /**
     * Find one instance of T.
     *
     * @param id the id
     * @return the instance of T
     */
    T findOne(long id);

    /**
     * Update an instance of T.
     *
     * @param t  the t
     * @param id the id
     * @return the updated instance of T
     */
    T update(T t, long id);

    /**
     * Delete an instance of T.
     *
     * @param id the id
     */
    void delete (long id);
}
