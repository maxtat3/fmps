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
	public void test2ReadUserData(){
		DBUtils.addNewUser(firstName, MiddleName, lastName);
		int latestUserId = DBUtils.countOfRecordsInMainTable();
		System.out.println("latestUserId = " + latestUserId);

		User user = DBUtils.getUser(3); // get latest user

		assert user != null;
		Assert.assertEquals(firstName, user.getFirstName());
		Assert.assertEquals(MiddleName, user.getMiddleName());
		Assert.assertEquals(lastName, user.getLastName());
	}

	@Ignore
	@Test
	public void test3UpdateUserData(){
		int records = DBUtils.countOfRecordsInMainTable();
		// todo - add method <updateUserData> in DBUtils
	}

	@Test
	public void test4RemoveUser(){
		int countBefore = DBUtils.countOfRecordsInMainTable();
		DBUtils.removeUser(countBefore);
		int countAfter = DBUtils.countOfRecordsInMainTable();

		Assert.assertEquals(--countBefore, countAfter);
	}


}
