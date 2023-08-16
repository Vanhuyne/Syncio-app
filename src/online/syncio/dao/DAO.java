package online.syncio.dao;

import java.util.List;

/**
 * The `DAO` (Data Access Object) interface provides a standardized set of methods for performing common database operations on entities of type `T`. Implementations of this interface are responsible for interacting with a specific type of entity in a database, such as adding, updating, deleting, retrieving by ID, and fetching all entities.
 *
 * @param <T> The type of entity that the DAO interacts with.
 */
public interface DAO<T> {

    /**
     * Adds a new entity to the database.
     *
     * @param t The entity to be added.
     * @return `true` if the entity was successfully added, otherwise `false`.
     */
    public boolean add(T t);

    /**
     * Updates an existing entity in the database based on its ID.
     *
     * @param t The entity with updated values.
     * @return `true` if the entity was successfully updated, otherwise `false`.
     */
    public boolean updateByID(T t);

    /**
     * Deletes an entity from the database based on its ID.
     *
     * @param entityID The ID of the entity to be deleted.
     * @return `true` if the entity was successfully deleted, otherwise `false`.
     */
    public boolean deleteByID(String entityID);

     /**
     * Retrieves an entity from the database based on its ID.
     *
     * @param entityID The ID of the entity to be retrieved.
     * @return The retrieved entity, or `null` if not found.
     */
    public T getByID(String entityID);

    /**
     * Fetches all entities of type `T` from the database.
     *
     * @return A list containing all retrieved entities.
     */
    public List<T> getAll();
}
