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
import project.common.entity.Type;

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
		Assert.assertEquals("Automotive", project.getType().getName());
	}

	@Test
	public void testFindAll() {
		List<Project> projects = baseDao.findAll(Project.class);

		Assert.assertTrue(projects.size() == 2);

		Project project1 = projects.get(0);
		Project project2 = projects.get(1);

		Assert.assertTrue(project1.getId() > 0);
		Assert.assertEquals("Example Project 1", project1.getName());
		Assert.assertEquals("Automotive", project1.getType().getName());

		Assert.assertTrue(project2.getId() > 0);
		Assert.assertEquals("Example Project 2", project2.getName());
		Assert.assertEquals("Computer", project2.getType().getName());
	}

	@Test
	public void testSave() {
		String name = "New Project 1";
		Type type = baseDao.find(Type.class, 1);

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
		Type type = baseDao.find(Type.class, 2);

		Project newPrj = new Project();
		newPrj.setName("To Be Upate");
		newPrj.setType(baseDao.find(Type.class, 1));

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
		newPrj.setType(baseDao.find(Type.class, 1));

		Project persisted = baseDao.save(newPrj);

		baseDao.delete(Project.class, persisted.getId());

		Project deleted = baseDao.find(Project.class, persisted.getId());

		Assert.assertNull(deleted);
	}
}
