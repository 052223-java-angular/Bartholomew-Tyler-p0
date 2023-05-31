package com.revature.app.daos;

import java.util.List;

/**
 * The CrudDAO interface provides CRUD (Create, Read, Update, Delete) operations
 * for a data model.
 *
 * @param <T> the type of the data model
 */
public interface CrudDAO<T> {
    /**
     * Saves the object to the data source.
     *
     * @param obj the object to be saved
     */
    void save(T obj);

    /**
     * Updates the object with the specified ID in the data source.
     *
     * @param id the ID of the object to be updated
     */
    void update(T obj);

    /**
     * Deletes the object with the specified ID from the data source.
     *
     * @param id the ID of the object to be deleted
     */
    void delete(String id);

    /**
     * Finds and retrieves the object with the specified ID from the data source.
     *
     * @param id the ID of the object to find
     * @return the found object, or null if not found
     */
    T findById(String id);

    /**
     * Retrieves all objects from the data source.
     *
     * @return a list of all objects
     */
    List<T> findAll();
}
