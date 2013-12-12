package project.common.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import project.common.entity.Project;

@ContextConfiguration(locations = { "classpath:project-application.xml", "classpath:project-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseDaoTest {

	@Autowired
	private BaseDao baseDao;

	@Before
	public void setup() {
		Assert.assertNotNull(baseDao);
	}

	@Test
	public void testSave() {
		String name = "Basic project name";
		String type = "Basic project type";

		Project newPrj = new Project();
		newPrj.setName(name);
		newPrj.setProjectType(type);

		Project persisted = baseDao.save(newPrj);

		Assert.assertTrue(persisted.getId() > 0);
		Assert.assertEquals(name, persisted.getName());
		Assert.assertEquals(type, persisted.getProjectType());
	}
}
