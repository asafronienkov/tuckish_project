package project.web.controller;

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

import project.common.dao.BaseDao;
import project.common.entity.Project;
import project.common.entity.Type;

@Controller
@RequestMapping(value = "/app/projects/*")
public class Projects {
	private static final Logger LOG = Logger.getLogger(Projects.class);

	@Autowired
	private BaseDao baseDao;

	/**
	 * This method responds to REST calls for all available {@link Project}
	 * objects and returns them as JSON
	 * 
	 * @return Marshalled JSON array of {@link Project} objects
	 */
	@ResponseBody
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public List<Project> findAll() {
		LOG.trace("Enter findAll()");

		LOG.debug("Retrieving all available projects");

		List<Project> projects = baseDao.findAll(Project.class);

		LOG.trace("Exit findAll()");
		return projects;
	}

	/**
	 * This method responds to REST calls requesting a single {@link Project}
	 * object
	 * 
	 * @param id
	 *            the ID of the specific object
	 * @return {@link Project} marshalled as a JSON object
	 */
	@ResponseBody
	@RequestMapping(value = "/find", method = RequestMethod.GET, produces = "application/json")
	public Project findById(@RequestParam(value = "id") long id) {
		LOG.trace("Enter findById()");

		LOG.debug("Retrieving project[id = " + id + "]");

		Project project = baseDao.find(Project.class, id);

		LOG.trace("Exit findById()");
		return project;
	}

	/**
	 * Save changes to a new or existing project
	 * 
	 * @param project
	 *            the JSON marshalled project from the client
	 * @return the persisted version of the {@link Project}
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
	public Project save(@RequestBody Project project) {
		LOG.trace("Enter save()");

		// Translate the Type.ID into a real entity
		long typeId = project.getType().getId();
		Type type = baseDao.find(Type.class, typeId);
		project.setType(type);
		LOG.debug("Translated type.id = " + typeId + ", to type = " + type.getName());

		Project persisted = null;
		if (project.getId() == 0) {
			// The zero ID means it is a new project
			LOG.debug("Saving new project");
			persisted = baseDao.save(project);
		} else {
			LOG.debug("Updating existing project");
			persisted = baseDao.update(Project.class, project);
		}

		LOG.trace("Exit save()");
		return persisted;
	}

	/**
	 * Deletes a {@link Project} given the ID finds a valid project
	 * 
	 * @param id
	 *            the ID of the {@link Project} to delete
	 * @return {@link ResponseEntity} with the following status codes: <br>
	 *         200: Delete completed<br>
	 *         304: Unable to find a matching project<br>
	 *         400: Invalid project id
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@RequestParam(value = "id") long id) {
		if (id == 0) {
			return new ResponseEntity<String>("Invalid Project ID", HttpStatus.BAD_REQUEST);
		}

		Project toDelete = baseDao.find(Project.class, id);
		if (toDelete == null) {
			return new ResponseEntity<String>("Unable to find project with id: " + id, HttpStatus.NOT_MODIFIED);
		}

		baseDao.delete(Project.class, toDelete.getId());

		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}
}
