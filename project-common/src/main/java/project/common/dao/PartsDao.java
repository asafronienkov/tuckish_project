package project.common.dao;

import java.util.List;
import java.util.Set;

import project.common.entity.Part;
import project.common.entity.Task;
import project.common.exception.BusinessException;

public interface PartsDao extends BaseDao {
	/**
	 * Returns a {@link List} of {@link Part} objects that are attached to a
	 * given {@link Task}
	 * 
	 * @param taskId
	 *            the task to pull parts for
	 * @return {@link Set} of {@link Part} objects
	 * @throws BusinessException
	 *             when the given {@link Task} is not found
	 */
	Set<Part> findByTask(long taskId) throws BusinessException;
}
