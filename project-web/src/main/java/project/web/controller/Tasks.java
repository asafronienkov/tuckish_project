package project.web.controller;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import project.common.dao.TaskDao;
import project.common.entity.Project;
import project.common.entity.Task;
import project.common.exception.BusinessException;

@Controller
@RequestMapping(value = "/app/tasks/*")
public class Tasks {
	private static final Logger LOG = Logger.getLogger(Tasks.class);

	@Autowired
	private TaskDao dao;

	/**
	 * This method responds to REST calls for all available {@link Task} objects
	 * and returns them as JSON
	 * 
	 * @return Marshalled JSON array of {@link Task} objects
	 */
	@ResponseBody
	@RequestMapping(value = "/all")
	public List<Task> findAll() {
		LOG.trace("Enter findAll()");

		LOG.debug("Retrieving all available tasks");
		List<Task> tasks = dao.findAll(Task.class);

		LOG.debug("Sorting the list of tasks");
		Collections.sort(tasks);

		LOG.trace("Exit findAll()");
		return tasks;
	}

	/**
	 * This method responds to REST calls for all available {@link Task} objects
	 * linked to a specific {@link Project}
	 * 
	 * @param projectId
	 *            the ID of the Project
	 * @return Marshalled JSON array of {@link Task} objects
	 */
	@ResponseBody
	@RequestMapping(value = "/project", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Task>> findByProject(@RequestParam(value = "id") long projectId) {
		if (projectId == 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		List<Task> tasks;
		try {
			tasks = dao.findByProject(projectId);
		} catch (BusinessException e) {
			LOG.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
	}

	/**
	 * This method responds to REST calls requesting a single {@link Task}
	 * object
	 * 
	 * @param id
	 *            the ID of the specific object
	 * @return {@link Task} marshalled as a JSON object
	 */
	@ResponseBody
	@RequestMapping(value = "/find", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Task> findById(@RequestParam(value = "id") long id) {
		if (id == 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Task task = dao.find(Task.class, id);
		if (task == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Task>(task, HttpStatus.OK);
	}

	/**
	 * This method responds to REST calls to save and new or updated
	 * {@link Task}
	 * 
	 * @param task
	 *            the {@link Task} object to save or update
	 * @return the new or updated version of the {@link Task} object
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Task> save(@RequestBody Task task, @RequestParam(value = "projectId") long projectId) {
		LOG.debug("Looking for Project[id:" + projectId + "]");

		Project project = dao.find(Project.class, projectId);
		if (project == null) {
			LOG.error("Unable to locate project with id: " + projectId);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		// Proper relation required for saving
		task.setProject(project);

		Task persisted = null;
		if (task.getId() == 0) {
			LOG.debug("Saving new project");
			persisted = dao.save(task);
		} else {
			LOG.debug("Updating existing project");
			persisted = dao.update(Task.class, task);
		}

		if (persisted == null) {
			LOG.error("Failed to save task");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Task>(persisted, HttpStatus.OK);
	}

	/**
	 * This method responds to REST calls to add a {@link Part} to a specific
	 * {@link Task}
	 * 
	 * @param taskId
	 *            the id of the Task to add the part to
	 * @param partId
	 *            the id of the existing Part
	 * @return the updated {@link Task} containing the new associated Part
	 */
	@ResponseBody
	@RequestMapping(value = "/addPart", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Task> addPart(@RequestParam(value = "taskId") long taskId,
			@RequestParam(value = "partId") long partId) {
		if (taskId == 0 || partId == 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Task persisted = null;
		try {
			persisted = dao.addPart(taskId, partId);
		} catch (BusinessException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Task>(persisted, HttpStatus.OK);
	}

	/**
	 * This method responds to REST calls to delete a specific {@link Task}
	 * object.
	 * 
	 * @param id
	 *            the ID of the {@link Task} to delete
	 * @return {@link ResponseEntity} with the following status codes: <br>
	 *         200: Delete completed<br>
	 *         304: Unable to find a matching task<br>
	 *         400: Invalid project id
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(long id) {
		if (id == 0) {
			return new ResponseEntity<String>("Invalid task ID", HttpStatus.BAD_REQUEST);
		}

		Task task = dao.find(Task.class, id);
		if (task == null) {
			return new ResponseEntity<String>("Unable to find matching Task", HttpStatus.NO_CONTENT);
		}

		dao.delete(Task.class, task.getId());

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
