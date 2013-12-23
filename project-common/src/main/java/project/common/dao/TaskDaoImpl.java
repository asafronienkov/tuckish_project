package project.common.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import project.common.entity.Part;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see project.common.dao.TaskDao#addPart(long, long)
	 */
	@Override
	public Task addPart(long taskId, long partId) throws BusinessException {
		LOG.trace("Enter addPart()");

		// Look up required objects
		Task task = find(Task.class, taskId);
		Part part = find(Part.class, partId);
		Task persisted = null;

		if (task == null || part == null) {
			throw new BusinessException("Unable to find matching Task/Part pair");
		}

		if (!part.getTasks().contains(task) && !task.getParts().contains(part)) {
			LOG.debug("Associating part[" + partId + "] with task[" + taskId + "]");
			part.getTasks().add(task);
			task.getParts().add(part);

			persisted = save(task);
			// save(part);
		} else {
			throw new BusinessException("The task already contains the requested part");
		}

		LOG.trace("Exit addPart()");
		return persisted;
	}
}
