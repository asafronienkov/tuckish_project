package project.common.dao;

import java.util.List;

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
	public void testFind() {
		Project project = baseDao.find(Project.class, 1);

		Assert.assertNotNull(project);
		Assert.assertEquals(1, project.getId());
		Assert.assertEquals("Example Project 1", project.getName());
		Assert.assertEquals("Type 1", project.getType());
	}

	@Test
	public void testFindAll() {
		List<Project> projects = baseDao.findAll(Project.class);

		Assert.assertTrue(projects.size() == 2);
		
		Project project1 = projects.get(0);
		Project project2 = projects.get(1);
		
		Assert.assertTrue(project1.getId() > 0);
		Assert.assertEquals("Example Project 1", project1.getName());
		Assert.assertEquals("Type 1", project1.getType());
		
		Assert.assertTrue(project2.getId() > 0);
		Assert.assertEquals("Example Project 2", project2.getName());
		Assert.assertEquals("Type 2", project2.getType());
	}

	@Test
	public void testSave() {
		String name = "New Project 1";
		String type = "New Type 1";

		Project newPrj = new Project();
		newPrj.setName(name);
		newPrj.setType(type);

		Project persisted = baseDao.save(newPrj);

		Assert.assertTrue(persisted.getId() > 0);
		Assert.assertEquals(name, persisted.getName());
		Assert.assertEquals(type, persisted.getType());
	}

	@Test
	public void testUpdate() {
		String name = "Updated Project 1";
		String type = "Updated Type 1";

		Project newPrj = new Project();
		newPrj.setName("To Be Upate");
		newPrj.setType("To Be Upate");

		Project persisted = baseDao.save(newPrj);

		persisted.setName(name);
		persisted.setType(type);

		Project updated = baseDao.update(Project.class, persisted);

		Assert.assertTrue(updated.getId() > 0);
		Assert.assertEquals(name, updated.getName());
		Assert.assertEquals(type, updated.getType());
	}

	@Test
	public void testDelete() {
		Project newPrj = new Project();
		newPrj.setName("To Be Deleted");
		newPrj.setType("To Be Deleted");

		Project persisted = baseDao.save(newPrj);

		baseDao.delete(Project.class, persisted.getId());

		Project deleted = baseDao.find(Project.class, persisted.getId());

		Assert.assertNull(deleted);
	}
}