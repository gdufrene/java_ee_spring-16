import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import user.dao.UserDao;
import user.model.User;

public class TestUserJDBC {

	UserDao dao;
	
	User testUser;
	
	@Before
	public void init() throws Exception {
		dao = TestHelper.createUserDao();
		testUser = createUser("test1", "test2", "testPwd");
	}
	
	@Before
	@After
	public void clear() throws Exception {
		TestHelper
			.getConnection()
			.createStatement()
			.executeUpdate("delete from user where email like '%@test.com'");
	}
	
	private User createUser(String firstname, String lastname, String password) {
		User u = new User();
		u.setMail(firstname+"."+lastname+"@test.com");
		u.setPrenom(firstname);
		u.setNom(lastname);
		u.setMotDePasse(password);
		return u;
	}
	
	private boolean oneLineExists(String query) throws SQLException {
		return TestHelper
			.getConnection()
			.createStatement()
			.executeQuery(query)
			.next();
	}
	
	@Test
	public void create_Nominal() throws SQLException {
		assertFalse(
			oneLineExists("select 1 from user where email = 'A.B@test.com'")
		);
		dao.create( createUser("A", "B", "C") );
		assertTrue(
			oneLineExists("select 1 from user where email = 'A.B@test.com'")
		);
	}
	
	@Test
	public void update_Nominal() throws SQLException {
		TestHelper.updateDb("insert into user(email, pwd) values('test1.test2@test.com', 'testPwd')");
		testUser.setMotDePasse("newPassword");
		testUser.setPrenom("newPrenom");
		testUser.setNom("newNom");
		dao.update(testUser);
		assertTrue(
			oneLineExists("select 1 from user where email = 'test1.test2@test.com' and firstname = 'newPrenom'")
		);
		assertTrue(
			oneLineExists("select 1 from user where email = 'test1.test2@test.com' and lastname = 'newNom'")
		);
		assertTrue(
			oneLineExists("select 1 from user where email = 'test1.test2@test.com' and pwd = 'newPassword'")
		);
	}
	
	@Test
	public void find_Nominal() {
		User user = dao.find("mark.zuckerberg@facebook.com");
		assertEquals("Mark", user.getPrenom());
		assertEquals("Zuckerberg", user.getNom());
		assertEquals("mark.zuckerberg@facebook.com", user.getMail());
		assertEquals("f4c3b00k", user.getMotDePasse());
	}
	
	
	@Test
	public void delete_Nominal() throws SQLException {
		TestHelper.updateDb("insert into user(email) values('test1.test2@test.com')");
		dao.delete("test1.test2@test.com");
		assertFalse(
			oneLineExists("select 1 from user where email = 'test1.test2@test.com'")
		);
	}
	
	@Test
	public void checkPassword_Nominal() throws SQLException {
		TestHelper.updateDb("insert into user(email, pwd) values('test1.test2@test.com', 'testPwd')");
 		assertTrue( dao.checkPassword("test1.test2@test.com", "testPwd") );
 		assertFalse( dao.checkPassword("test1.test2@test.com", "__testPwd__") );
	}
	
	@Test
	public void checkPassword_Inject1() throws SQLException {
		TestHelper.updateDb("insert into user(email, pwd) values('test1.test2@test.com', 'testPwd')");
 		assertFalse( dao.checkPassword("test1.test2@test.com", "' OR '1' = '1") );
	}
	
	@Test
	public void checkPassword_Inject2() throws SQLException {
		TestHelper.updateDb("insert into user(email, pwd) values('test1.test2@test.com', 'testPwd')");
 		assertFalse( dao.checkPassword("noMatch' UNION SELECT 'anypassword', 'anypassword', 'anypassword', 'anypassword", "anypassword") );
	}
	
	
}
