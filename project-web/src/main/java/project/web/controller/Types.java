package project.web.controller;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
@RequestMapping(value = "/app/types/*")
public class Types {
	private static final Logger LOG = Logger.getLogger(Types.class);

	@Autowired
	@Qualifier(value = "base")
	private BaseDao dao;

	/**
	 * This controller method responds to REST calls for all available
	 * {@link Type} objects and returns them as JSON
	 * 
	 * @return Marshalled JSON array of {@link Type} objects
	 */
	@ResponseBody
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public List<Type> findAll() {
		LOG.trace("Enter findAll()");

		LOG.debug("Retrieving all available types");

		List<Type> types = dao.findAll(Type.class);

		LOG.debug("Sorting the list of types");
		Collections.sort(types);

		LOG.trace("Exit findAll()");
		return types;
	}

	/**
	 * This controller method responds to REST calls to save a new {@link Type}
	 * object. The POST request must contain a valid JSON Type object
	 * 
	 * @param newType
	 *            the request JSON marshalled to a {@link Type}
	 * @return the persisted version of the new {@link Type}
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
	public Type save(@RequestBody Type newType) {
		LOG.trace("Enter save()");

		LOG.debug("Saving new type: " + newType.getName());

		Type persisted = dao.save(newType);

		LOG.trace("Exit save()");
		return persisted;
	}

	/**
	 * This controller method responds to REST calls to update an existing
	 * {@link Type} object. The PUT request must contain a valid JSON Type
	 * object
	 * 
	 * @param updateType
	 *            the request JSON marshalled to a {@link Type}
	 * @return the persisted changes to the {@link Type} object
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json")
	public Type update(@RequestBody Type updateType) {
		LOG.trace("Enter update()");

		LOG.debug("Updating type [id=" + updateType.getId() + "] with name: " + updateType.getName());

		Type persisted = dao.update(Type.class, updateType);

		LOG.trace("Exit update()");
		return persisted;
	}

	/**
	 * This controller method responds to REST calls to delete an existing
	 * {@link Type} object. The URL must contain a 'id' parameter
	 * 
	 * @param id
	 *            the ID of the existing {@link Type} to delete
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@RequestParam(value = "id") long id) {
		LOG.trace("Enter delete()");

		LOG.debug("Deleting type: " + id);

		// Types can be associated with Projects or Parts. The type must be
		// dereferenced before it is allowed to be deleted.
		boolean refFound = false;
		List<Project> projects = dao.findAll(Project.class);
		for (Project project : projects) {
			Type prjType = project.getType();
			if (prjType != null && prjType.getId() == id) {
				// This is a match for the type
				refFound = true;
			}
		}

		if (refFound) {
			LOG.warn("Type[id = " + id + "] has references to at least one project/part. Dereference before deleting");
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

		dao.delete(Type.class, id);

		LOG.trace("Exit delete()");
		return new ResponseEntity<String>(HttpStatus.OK);
	}
}
