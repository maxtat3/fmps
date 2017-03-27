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


	@BeforeClass
	public static void initDB(){
		DBUtils.initDatabase();
	}

	@Before
	public void addNewUser() {
		DBUtils.addNewUser(firstName, MiddleName, lastName);
	}


	@Test
	public void testCreateNewUser(){
		int countBefore = DBUtils.countOfRecordsInMainTable();
		DBUtils.addNewUser("UserFirstName", "UserMiddleName", "UserLastName");
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

}
