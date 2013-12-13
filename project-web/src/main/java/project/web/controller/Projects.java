package project.web.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import project.common.dao.BaseDao;
import project.common.entity.Project;

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
	public List<Project> getAllProjects() {
		LOG.trace("Enter getAllProjects()");

		LOG.debug("Retrieving all available projects");

		List<Project> projects = baseDao.findAll(Project.class);

		LOG.trace("Exit getAllProjects()");
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
	public Project findProjectById(@RequestParam(value = "id") long id) {
		LOG.trace("Enter findProjectById()");

		LOG.debug("Retrieving project[id = " + id + "]");

		Project project = baseDao.find(Project.class, id);

		LOG.trace("Exit findProjectById()");
		return project;
	}
}
