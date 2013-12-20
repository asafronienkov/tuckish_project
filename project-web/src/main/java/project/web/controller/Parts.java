package project.web.controller;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import project.common.dao.PartsDao;
import project.common.entity.Part;
import project.common.exception.BusinessException;

@Controller
@RequestMapping(value = "/app/parts/*")
public class Parts {
	private static final Logger LOG = Logger.getLogger(Parts.class);

	@Autowired
	@Qualifier(value = "parts")
	private PartsDao dao;

	/**
	 * This controller method responds to REST calls for all available
	 * {@link Part} objects and returns them as JSON
	 * 
	 * @return Marshalled JSON array of {@link Part} objects
	 */
	@ResponseBody
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public List<Part> findAll() {
		LOG.trace("Enter findAll()");

		LOG.debug("Retrieving all available parts");

		List<Part> types = dao.findAll(Part.class);

		LOG.debug("Sorting the list of parts");
		Collections.sort(types);

		LOG.trace("Exit findAll()");
		return types;
	}

	/**
	 * 
	 * @param taskId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/task", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Set<Part>> findByTask(@RequestParam(value = "id") long taskId) {
		if (taskId == 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Set<Part> parts;
		try {
			parts = dao.findByTask(taskId);
		} catch (BusinessException e) {
			LOG.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Set<Part>>(parts, HttpStatus.OK);
	}
}
