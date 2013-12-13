package project.web.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import project.common.entity.Project;

@Controller
@RequestMapping(value = "/app/projects/*")
public class Projects {
	private static final Logger LOG = Logger.getLogger(Projects.class);

	@ResponseBody
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Project> getAllProjects() {
		return null;
	}
}
