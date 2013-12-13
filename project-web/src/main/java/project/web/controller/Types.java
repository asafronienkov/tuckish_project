package project.web.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
}
