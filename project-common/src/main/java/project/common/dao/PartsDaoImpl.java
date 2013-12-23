package project.common.dao;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import project.common.entity.Part;
import project.common.entity.Task;
import project.common.entity.Type;
import project.common.exception.BusinessException;

@Repository
@Transactional
@Qualifier(value = "parts")
public class PartsDaoImpl extends BaseDaoImpl implements PartsDao {
	private static final Logger LOG = Logger.getLogger(PartsDaoImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see project.common.dao.PartsDao#findByTask(long)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see project.common.dao.PartsDao#saveTaskPart(long,
	 * project.common.entity.Part)
	 */
	@Override
	public void saveTaskPart(long taskId, Part part) {
		LOG.trace("Enter saveTaskPart()");

		LOG.debug("Looking for task id: " + taskId);
		Task task = find(Task.class, taskId);

		// Inflate Type to a real object
		Type type = find(Type.class, part.getType().getId());
		part.setType(type);

		// Associate the task and part
		part.getTasks().add(task);
		task.getParts().add(part);

		save(task);
		LOG.trace("Exit saveTaskPart()");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see project.common.dao.PartsDao#updatePart(project.common.entity.Part)
	 */
	@Override
	public Part updatePart(Part part) throws BusinessException {
		LOG.trace("Enter updatePart()");

		if (part == null || part.getId() == 0) {
			throw new BusinessException("Invalid Part");
		}

		Part existing = find(Part.class, part.getId());
		if (existing == null) {
			throw new BusinessException("Unable to find matching Part");
		}

		// Transfer any changes to the persisted copy
		existing.setName(part.getName());
		existing.setManufacturer(part.getManufacturer());
		existing.setNumber(part.getNumber());
		existing.setCost(part.getCost());
		existing.setWeight(part.getWeight());

		// Inflate Type to real object
		Type type = find(Type.class, part.getType().getId());
		existing.setType(type);

		// Save the new entity changes
		Part persisted = null;
		persisted = save(existing);

		LOG.trace("Exit updatePart()");
		return persisted;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see project.common.dao.PartsDao#removePart(long, long)
	 */
	@Override
	public void removePart(long taskId, long partId) throws BusinessException {
		LOG.trace("Enter removePart()");

		// Look for the parent task
		Task task = find(Task.class, taskId);
		if (task == null) {
			throw new BusinessException("Unable to find a valid Task");
		}
		// Looking for part
		Part part = find(Part.class, partId);
		if (part == null) {
			throw new BusinessException("Unable to find a valid Part");
		}

		// Remove the part from the task, but we don't delete the part
		LOG.debug("Removing part[" + partId + "] from task[" + taskId + "]");
		task.getParts().remove(part);
		save(task);

		LOG.trace("Exit removePart()");
	}
}
