package project.web.controller;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import project.common.dao.BaseDao;
import project.common.entity.Task;

@Controller
@RequestMapping(value = "/app/tasks/*")
public class Tasks {
	private static final Logger LOG = Logger.getLogger(Tasks.class);

	@Autowired
	private BaseDao baseDao;

	/**
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/all")
	public List<Task> findAll() {
		LOG.trace("Enter findAll()");

		LOG.debug("Retrieving all available tasks");
		List<Task> tasks = baseDao.findAll(Task.class);

		LOG.debug("Sorting the list of tasks");
		Collections.sort(tasks);

		LOG.trace("Exit findAll()");
		return tasks;
	}

	public Task findById() {
		return null;
	}

	public Task save(Task task) {
		return null;
	}

	public Task update(Task task) {
		return null;
	}

	public void delete(long id) {

	}
}
