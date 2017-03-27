package db;

import domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 */
public class DBUtilsTest {


	@Before
	public void init(){
		DBUtils.initDatabase();

	}

	String firstName = "Alex";
	String MiddleName = "Vitalievich";
	String lastName = "Tkachecnko";

	@Test
	public void testCreateNewUser(){
		int countBefore = DBUtils.countOfRecordsInMainTable();
		DBUtils.addNewUser(firstName, MiddleName, lastName);
		int countAfter = DBUtils.countOfRecordsInMainTable();

		Assert.assertEquals(++countBefore, countAfter);
	}

	@Test
	public void testReadUserData(){
		DBUtils.addNewUser(firstName, MiddleName, lastName);
		int latestUserId = DBUtils.getLatestUserIdInMainTable();
		User user = DBUtils.getUser(latestUserId); // get latest user

		assert user != null;
		Assert.assertEquals(firstName, user.getFirstName());
		Assert.assertEquals(MiddleName, user.getMiddleName());
		Assert.assertEquals(lastName, user.getLastName());
	}

	@Ignore
	@Test
	public void testUpdateUserData(){
		int records = DBUtils.countOfRecordsInMainTable();
		// todo - add method <updateUserData> in DBUtils
	}

	@Test
	public void testIsUserExist(){
		final String firstName = "TestUserFirstName0";
		final String middleName = "TestUserMiddleName0";
		final String lastName = "TestUserLastName0";

		DBUtils.addNewUser(firstName, middleName, lastName);

		boolean isUserExist = DBUtils.isUserExist(firstName, middleName, lastName);
		Assert.assertEquals(true, isUserExist);
	}

	@Test
	public void testRemoveUser(){
		int countBefore = DBUtils.countOfRecordsInMainTable();
		int id = DBUtils.getLatestUserIdInMainTable();
		DBUtils.removeUser(id);
		int countAfter = DBUtils.countOfRecordsInMainTable();

		Assert.assertEquals(--countBefore, countAfter);
	}


}
