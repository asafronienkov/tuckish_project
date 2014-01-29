package project.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import project.common.dao.BaseDao;
import project.common.entity.Project;
import project.common.entity.Task;

@Controller
public class View {
	private static final Logger LOG = Logger.getLogger(View.class);

	@Autowired
	@Qualifier(value = "base")
	private BaseDao dao;

	/**
	 * Responds to requests for the home page view
	 * 
	 * @return {@link ModelAndView} with the view of 'home'
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView loadHome() {
		return new ModelAndView("home");
	}

	/**
	 * Responds to requests for the projects page view
	 * 
	 * @return {@link ModelAndView} with the view of 'projects'
	 */
	@RequestMapping(value = "/projects", method = RequestMethod.GET)
	public ModelAndView loadProjects() {
		return new ModelAndView("projects");
	}

	/**
	 * Responds to web requests to load the details for a specific project. This
	 * looks up the project and sets the object in the {@link Model} that is
	 * returned to the client
	 * 
	 * @param id
	 *            the ID of the Project to load
	 * @return {@link ModelAndView} with view set to 'project' and the model
	 *         containing the found {@link Project}
	 */
	@RequestMapping(value = "/project", method = RequestMethod.GET)
	public ModelAndView loadProject(@RequestParam(value = "id", required = true) long id) {
		LOG.info("Loading project [" + id + "] details");

		ModelAndView m = new ModelAndView("project");
		Project project = dao.find(Project.class, id);

		if (project == null) {
			LOG.error("Unable to locate a project with id: " + id);
			return m;
		}

		m.addObject("project", project);

		return m;
	}

	/**
	 * Responds to web requests to load the details for a specific task. This
	 * looks up the task and sets the object in the {@link Model} that is
	 * returned to the client
	 * 
	 * @param id
	 *            the ID of the Task to load
	 * @return {@link ModelAndView} with view set to 'task' and the model
	 *         containing the found {@link Task}
	 */
	@RequestMapping(value = "/task", method = RequestMethod.GET)
	public ModelAndView loadTask(@RequestParam(value = "id", required = true) long id) {
		LOG.info("Loading task [" + id + "] details");

		ModelAndView m = new ModelAndView("task");
		Task task = dao.find(Task.class, id);

		if (task == null) {
			LOG.error("Unable to locate a task with id: " + id);
			return m;
		}

		m.addObject("project", task.getProject());
		m.addObject("task", task);

		return m;
	}
}
