package project.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import project.common.dao.BaseDao;
import project.common.entity.Project;

@Controller
public class View {
	private static final Logger LOG = Logger.getLogger(View.class);

	@Autowired
	private BaseDao baseDao;

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView loadHome() {
		return new ModelAndView("home");
	}

	/**
	 * Responds to web requests to load the details for a specific project. This
	 * looks up the project and sets the object in the {@link Model} that is
	 * returned to the client
	 * 
	 * @param id
	 *            the ID of the project to load
	 * @return {@link ModelAndView} with view set to 'project' and the model
	 *         containing the found {@link ProjectView}
	 */
	@RequestMapping(value = "/project", method = RequestMethod.GET)
	public ModelAndView loadProject(@RequestParam(value = "id", required = true) long id) {
		LOG.info("Loading project [" + id + "] details");

		ModelAndView m = new ModelAndView("project");
		Project project = baseDao.find(Project.class, id);

		if (project == null) {
			LOG.error("Unable to locate a project with id: " + id);
			return null;
		}

		m.addObject("project", project);

		return m;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/projects", method = RequestMethod.GET)
	public ModelAndView loadProjects() {
		return new ModelAndView("projects");
	}
}
