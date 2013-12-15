package project.web.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import project.common.dao.BaseDao;
import project.common.entity.Type;

@Controller
@RequestMapping(value = "/app/types/*")
public class Types {
	private static final Logger LOG = Logger.getLogger(Types.class);

	@Autowired
	private BaseDao baseDao;

	/**
	 * This controller method responds to REST calls for all available
	 * {@link Type} objects and returns them as JSON
	 * 
	 * @return Marshalled JSON array of {@link Type} objects
	 */
	@ResponseBody
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public List<Type> getAllTypes() {
		LOG.trace("Enter getAllTypes()");

		LOG.debug("Retrieving all available types");

		List<Type> types = baseDao.findAll(Type.class);

		LOG.trace("Exit getAllTypes()");
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
	public Type saveType(@RequestBody Type newType) {
		LOG.trace("Enter saveType()");

		LOG.debug("Saving new type: " + newType.getName());

		Type persisted = baseDao.save(newType);

		LOG.trace("Exit saveType()");
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
	public Type updateType(@RequestBody Type updateType) {
		LOG.trace("Enter updateType()");

		LOG.debug("Updating type [id=" + updateType.getId() + "] with name: " + updateType.getName());

		Type persisted = baseDao.update(Type.class, updateType);

		LOG.trace("Exit updateType()");
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
	public void deleteType(@RequestParam(value = "id") long id) {
		LOG.trace("Enter deleteType()");

		LOG.debug("Deleting type: " + id);

		baseDao.delete(Type.class, id);
		LOG.trace("Exit deleteType()");
	}
}
