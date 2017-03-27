package db;

import domain.User;
import org.junit.*;

/**
 * Test SQLite DB methods.
 */
public class DBUtilsTest {

	private static final String firstName = "Alex";
	private static final String MiddleName = "Vitalievich";
	private static final String lastName = "Tkachecnko";
	public static final int numberOfRecordBook = 7031503;


	@BeforeClass
	public static void initDB(){
		DBUtils.initDatabase();
	}

	@Before
	public void addNewUser() {
		DBUtils.addNewUser(firstName, MiddleName, lastName, numberOfRecordBook);
	}


	@Test
	public void testCreateNewUser(){
		int countBefore = DBUtils.countOfRecordsInMainTable();
		DBUtils.addNewUser("UserFirstName", "UserMiddleName", "UserLastName", 10577);
		int countAfter = DBUtils.countOfRecordsInMainTable();

		Assert.assertEquals(++countBefore, countAfter);
	}

	@Test
	public void testReadUserData(){
		int latestUserId = DBUtils.getLatestUserIdInMainTable();
		User user = DBUtils.getUser(latestUserId); // get latest user

		assert user != null;
		Assert.assertEquals(firstName, user.getFirstName());
		Assert.assertEquals(MiddleName, user.getMiddleName());
		Assert.assertEquals(lastName, user.getLastName());
		Assert.assertEquals(numberOfRecordBook, user.getNumberOfRecordBook());
	}

	@Test
	public void testUpdateUserData(){
		int latestUserId = DBUtils.getLatestUserIdInMainTable();
		User user = new User(firstName, MiddleName, lastName, numberOfRecordBook);
		DBUtils.updateUser(latestUserId, user);
		user = DBUtils.getUser(latestUserId);

		assert user != null;
		Assert.assertEquals(firstName, user.getFirstName());
	}

	@Test
	public void testUpdateUserDataRv1(){
		int latestUserId = DBUtils.getLatestUserIdInMainTable();
		User user = new User("Dmitry", MiddleName, lastName, numberOfRecordBook);
		DBUtils.updateUser(latestUserId, user);
		user = DBUtils.getUser(latestUserId);

		assert user != null;
		Assert.assertEquals("Dmitry", user.getFirstName());
	}

	@Test
	public void testUpdateUserDataRv2(){
		int latestUserId = DBUtils.getLatestUserIdInMainTable();
		User user = new User(firstName, MiddleName, lastName, 127);
		DBUtils.updateUser(latestUserId, user);
		user = DBUtils.getUser(latestUserId);

		assert user != null;
		Assert.assertEquals(127, user.getNumberOfRecordBook());
	}

	@Test
	public void testUpdateUserDataRv3(){
		String firstName = "Andrew";
		String middleName = "Timofeevich";
		int numOfRecBook = 1250071;

		int latestUserId = DBUtils.getLatestUserIdInMainTable();
		User user = new User(firstName, middleName, lastName, numOfRecBook);
		DBUtils.updateUser(latestUserId, user);
		user = DBUtils.getUser(latestUserId);

		assert user != null;
		Assert.assertEquals(firstName, user.getFirstName());
		Assert.assertEquals(middleName, user.getMiddleName());
		Assert.assertEquals(lastName, user.getLastName());
		Assert.assertEquals(numOfRecBook, user.getNumberOfRecordBook());
	}

	@Test
	public void testIsUserExist(){
		final String firstName = "TestUserFirstName0";
		final String middleName = "TestUserMiddleName0";
		final String lastName = "TestUserLastName0";
		final int num = 7071505;

		DBUtils.addNewUser(firstName, middleName, lastName, num);

		boolean isUserExist = DBUtils.isUserExist(firstName, middleName, lastName, num);
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

	/**
	 * @see {@link DBUtils#ST1_PROGRESS}, {@link DBUtils#ST2_PROGRESS}, {@link DBUtils#ST3_PROGRESS}
	 */
	@Test
	public void testGetProgressStage1(){
		int id = DBUtils.getLatestUserIdInMainTable();
		int progress = DBUtils.getProgress(DBUtils.Stage.STAGE_1, id);
		Assert.assertEquals(0, progress);
	}

	@Test
	public void testGetProgressStage2(){
		int id = DBUtils.getLatestUserIdInMainTable();
		int progress = DBUtils.getProgress(DBUtils.Stage.STAGE_2, id);
		Assert.assertEquals(0, progress);
	}

	@Test
	public void testGetProgressStage3(){
		int id = DBUtils.getLatestUserIdInMainTable();
		int progress = DBUtils.getProgress(DBUtils.Stage.STAGE_3, id);
		Assert.assertEquals(0, progress);
	}

	@Test
	public void testIncrementProgressStage1(){
		int id = DBUtils.getLatestUserIdInMainTable();

		int progressBefore = DBUtils.getProgress(DBUtils.Stage.STAGE_1, id);
		DBUtils.incrementProgress(DBUtils.Stage.STAGE_1, id);
		int progressAfter = DBUtils.getProgress(DBUtils.Stage.STAGE_1, id);

		Assert.assertEquals(++progressBefore, progressAfter);
	}

	@Test
	public void testIncrementProgressStage2(){
		int id = DBUtils.getLatestUserIdInMainTable();

		int progressBefore = DBUtils.getProgress(DBUtils.Stage.STAGE_2, id);
		DBUtils.incrementProgress(DBUtils.Stage.STAGE_2, id);
		int progressAfter = DBUtils.getProgress(DBUtils.Stage.STAGE_2, id);

		Assert.assertEquals(++progressBefore, progressAfter);
	}

	@Test
	public void testIncrementProgressStage3(){
		int id = DBUtils.getLatestUserIdInMainTable();

		int progressBefore = DBUtils.getProgress(DBUtils.Stage.STAGE_3, id);
		DBUtils.incrementProgress(DBUtils.Stage.STAGE_3, id);
		int progressAfter = DBUtils.getProgress(DBUtils.Stage.STAGE_3, id);

		Assert.assertEquals(++progressBefore, progressAfter);
	}

	@Test
	public void testIncrementProgressStage1Rv1(){
		int id = DBUtils.getLatestUserIdInMainTable();

		int progressBefore = DBUtils.getProgress(DBUtils.Stage.STAGE_1, id);
		DBUtils.incrementProgress(DBUtils.Stage.STAGE_1, id);
		DBUtils.incrementProgress(DBUtils.Stage.STAGE_1, id);
		DBUtils.incrementProgress(DBUtils.Stage.STAGE_1, id);
		DBUtils.incrementProgress(DBUtils.Stage.STAGE_1, id);
		DBUtils.incrementProgress(DBUtils.Stage.STAGE_1, id);
		int progressAfter = DBUtils.getProgress(DBUtils.Stage.STAGE_1, id);

		progressBefore += 5;
		Assert.assertEquals(progressBefore, progressAfter);
	}

	@Test
	public void testResetProgressStage1(){
		int id = DBUtils.getLatestUserIdInMainTable();

		DBUtils.incrementProgress(DBUtils.Stage.STAGE_1, id);
		DBUtils.incrementProgress(DBUtils.Stage.STAGE_1, id);
		DBUtils.incrementProgress(DBUtils.Stage.STAGE_1, id);
		DBUtils.resetProgress(DBUtils.Stage.STAGE_1, id);
		int progressAfter = DBUtils.getProgress(DBUtils.Stage.STAGE_1, id);

		Assert.assertEquals(0, progressAfter);
	}

	@Test
	public void testResetProgressStage2(){
		int id = DBUtils.getLatestUserIdInMainTable();

		DBUtils.incrementProgress(DBUtils.Stage.STAGE_2, id);
		DBUtils.resetProgress(DBUtils.Stage.STAGE_2, id);
		int progressAfter = DBUtils.getProgress(DBUtils.Stage.STAGE_2, id);

		Assert.assertEquals(0, progressAfter);
	}

	@Test
	public void testResetProgressStage3(){
		int id = DBUtils.getLatestUserIdInMainTable();

		DBUtils.incrementProgress(DBUtils.Stage.STAGE_3, id);
		DBUtils.incrementProgress(DBUtils.Stage.STAGE_3, id);
		DBUtils.incrementProgress(DBUtils.Stage.STAGE_3, id);
		DBUtils.incrementProgress(DBUtils.Stage.STAGE_3, id);
		DBUtils.incrementProgress(DBUtils.Stage.STAGE_3, id);
		DBUtils.resetProgress(DBUtils.Stage.STAGE_3, id);
		int progressAfter = DBUtils.getProgress(DBUtils.Stage.STAGE_3, id);

		Assert.assertEquals(0, progressAfter);
	}

}
