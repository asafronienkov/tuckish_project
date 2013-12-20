package project.common.dao;

import java.util.List;

import project.common.entity.Project;
import project.common.entity.Task;
import project.common.exception.BusinessException;

public interface TaskDao extends BaseDao {
	/**
	 * Returns a {@link List} of {@link Task} objects that are attached to a
	 * given {@link Project}
	 * 
	 * @param projectId
	 *            the project to pull tasks for
	 * @return {@link List of {@link Task} objects
	 * @throws BusinessException
	 *             when the given {@link Project} is not found
	 */
	List<Task> findByProject(long projectId) throws BusinessException;
}
