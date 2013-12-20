package project.common.dao;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import project.common.entity.Part;
import project.common.entity.Task;
import project.common.exception.BusinessException;

@Repository
@Transactional
@Qualifier(value = "parts")
public class PartsDaoImpl extends BaseDaoImpl implements PartsDao {
	private static final Logger LOG = Logger.getLogger(PartsDaoImpl.class);

	@Override
	public Set<Part> findByTask(long taskId) throws BusinessException {
		LOG.trace("Enter findByTask()");

		LOG.debug("Looking for task id: " + taskId);
		Task task = find(Task.class, taskId);
		if (task == null) {
			throw new BusinessException("Task ID: " + taskId + " not found");
		}

		Set<Part> parts = task.getParts();
		LOG.debug("Task ID: " + taskId + " has " + parts.size() + " task");

		LOG.trace("Exit findByTask()");
		return parts;
	}
}
