package project.common.dao;

import java.util.List;

import project.common.entity.Base;

/**
 * The first layer DAO. This DAO provides a basic set of generic methods for
 * performing DAO operations.
 */
public interface BaseDao {
	/**
	 * Returns all persisted database records for the corresponding class type.
	 * 
	 * @param clazz
	 *            the class type of the entity, which must extend {@link Base}
	 * @return {@link List} of persisted entities
	 */
	<T extends Base> List<T> findAll(Class<T> clazz);

	/**
	 * Returns a specific persisted database record for the corresponding class
	 * type and id.
	 * 
	 * @param clazz
	 *            the class type of the entity, which must extend {@link Base}
	 * @param id
	 *            the id value of the persisted record
	 * @return a single entity object
	 */
	<T extends Base> T find(Class<T> clazz, long id);

	/**
	 * Persists the provided entity to the database.
	 * 
	 * @param entity
	 *            the new entity to persist, which must extend {@link Base}
	 * @return the persisted copy of the entity
	 */
	<T extends Base> T save(T entity);

	/**
	 * Updates a specific persisted database record matching the corresponding
	 * class.
	 * 
	 * @param clazz
	 *            the class type of the entity, which must extend {@link Base}
	 * @param entity
	 *            the new version of the entity that should be persisted
	 * @return the updated version of the entity
	 */
	<T extends Base> T update(Class<T> clazz, T entity);

	/**
	 * Deletes a persisted entity from the database.
	 * 
	 * @param clazz
	 *            the class type of the entity, which must extend {@link Base}
	 * @param id
	 *            the id value of the persisted record
	 */
	<T extends Base> void delete(Class<T> clazz, long id);
}
