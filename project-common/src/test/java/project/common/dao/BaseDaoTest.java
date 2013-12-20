package project.common.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import project.common.entity.Project;
import project.common.entity.Type;

@Transactional
@ContextConfiguration(locations = { "classpath:project-application.xml", "classpath:project-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseDaoTest {

	@Autowired
	@Qualifier(value = "base")
	private BaseDao dao;

	@Before
	public void setup() {
		Assert.assertNotNull(dao);
	}

	@Test
	public void testFind() {
		Project project = dao.find(Project.class, 1);

		Assert.assertNotNull(project);
		Assert.assertEquals(1, project.getId());
		Assert.assertEquals("Example Project 1", project.getName());
		Assert.assertEquals("Automotive", project.getType().getName());
	}

	@Test
	public void testFindAll() {
		List<Project> projects = dao.findAll(Project.class);

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
		Type type = dao.find(Type.class, 1);

		Project newPrj = new Project();
		newPrj.setName(name);
		newPrj.setType(type);

		Project persisted = dao.save(newPrj);

		Assert.assertTrue(persisted.getId() > 0);
		Assert.assertEquals(name, persisted.getName());
		Assert.assertEquals(type, persisted.getType());
	}

	@Test
	public void testUpdate() {
		String name = "Updated Project 1";
		Type type = dao.find(Type.class, 2);

		Project newPrj = new Project();
		newPrj.setName("To Be Upate");
		newPrj.setType(dao.find(Type.class, 1));

		Project persisted = dao.save(newPrj);

		persisted.setName(name);
		persisted.setType(type);

		Project updated = dao.update(Project.class, persisted);

		Assert.assertTrue(updated.getId() > 0);
		Assert.assertEquals(name, updated.getName());
		Assert.assertEquals(type, updated.getType());
	}

	@Test
	public void testDelete() {
		Project newPrj = new Project();
		newPrj.setName("To Be Deleted");
		newPrj.setType(dao.find(Type.class, 1));

		Project persisted = dao.save(newPrj);

		dao.delete(Project.class, persisted.getId());

		Project deleted = dao.find(Project.class, persisted.getId());

		Assert.assertNull(deleted);
	}
}
