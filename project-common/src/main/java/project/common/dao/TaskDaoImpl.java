package project.common.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import project.common.entity.Project;
import project.common.entity.Task;
import project.common.exception.BusinessException;

@Repository
@Transactional
@Qualifier(value = "task")
public class TaskDaoImpl extends BaseDaoImpl implements TaskDao {
	private static final Logger LOG = Logger.getLogger(TaskDaoImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see project.common.dao.TaskDao#findByProject(long)
	 */
	@Override
	public List<Task> findByProject(long projectId) throws BusinessException {
		LOG.trace("Enter findByProject()");

		LOG.debug("Looking for project id: " + projectId);
		Project project = find(Project.class, projectId);
		if (project == null) {
			throw new BusinessException("Project ID: " + projectId + " not found");
		}

		LOG.debug("Retrieving Tasks for project id: " + projectId);
		TypedQuery<Task> query = em.createQuery("SELECT e FROM Task e WHERE e.project = :project", Task.class);
		query.setParameter("project", project);

		List<Task> tasks = query.getResultList();
		LOG.debug("Project ID: " + projectId + " has " + tasks.size() + " task");

		LOG.trace("Exit findByProject()");
		return tasks;
	}
}
