package db;

import domain.User;
import model.Container;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import stage1.ExtraInputDataStage1;

/**
 * Test SQLite DB methods.
 */
public class DBUtilsTest {

	private static final String firstName = "Alex";
	private static final String middleName = "Vitalievich";
	private static final String lastName = "Tkachecnko";
	public static final int numberOfRecordBook = 7031503;


	@BeforeClass
	public static void initDB(){
		DBUtils.initDatabase();
		// Add new user because we do not know which next method will be performed first.
		DBUtils.addNewUser(firstName, middleName, lastName, numberOfRecordBook);
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
		Assert.assertEquals(middleName, user.getMiddleName());
		Assert.assertEquals(lastName, user.getLastName());
		Assert.assertEquals(numberOfRecordBook, user.getNumberOfRecordBook());
	}

	@Test
	public void testUpdateUserData(){
		int latestUserId = DBUtils.getLatestUserIdInMainTable();
		User user = new User(firstName, middleName, lastName, numberOfRecordBook);
		DBUtils.updateUser(latestUserId, user);
		user = DBUtils.getUser(latestUserId);

		assert user != null;
		Assert.assertEquals(firstName, user.getFirstName());
	}

	@Test
	public void testUpdateUserDataRv1(){
		int latestUserId = DBUtils.getLatestUserIdInMainTable();
		User user = new User("Dmitry", middleName, lastName, numberOfRecordBook);
		DBUtils.updateUser(latestUserId, user);
		user = DBUtils.getUser(latestUserId);

		assert user != null;
		Assert.assertEquals("Dmitry", user.getFirstName());
	}

	@Test
	public void testUpdateUserDataRv2(){
		int latestUserId = DBUtils.getLatestUserIdInMainTable();
		User user = new User(firstName, middleName, lastName, 127);
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

	// TODO: before remove user may be need new user because new user create once before run all test methods !
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
	// TODO: in next 3 methods initial progress value may be differ from 0 ! 
	@Test
	public void testGetProgressStage1(){
		int id = DBUtils.getLatestUserIdInMainTable();
		// reset progress to 0 when some other method may be change progress value for this stage
		DBUtils.resetProgress(DBUtils.Stage.STAGE_1, id);

		int progress = DBUtils.getProgress(DBUtils.Stage.STAGE_1, id);
		Assert.assertEquals(0, progress);
	}

	@Test
	public void testGetProgressStage2(){
		int id = DBUtils.getLatestUserIdInMainTable();
		// reset progress to 0 when some other method may be change progress value for this stage
		DBUtils.resetProgress(DBUtils.Stage.STAGE_2, id);

		int progress = DBUtils.getProgress(DBUtils.Stage.STAGE_2, id);
		Assert.assertEquals(0, progress);
	}

	@Test
	public void testGetProgressStage3(){
		int id = DBUtils.getLatestUserIdInMainTable();
		// reset progress to 0 when some other method may be change progress value for this stage
		DBUtils.resetProgress(DBUtils.Stage.STAGE_3, id);

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

	@Test
	public void testFindUser(){
		String firstName = "Vitaly";
		String middleName = "Andreevich";
		String lastName = "Trifonov";
		int numOfRecBook = 1553011;
		DBUtils.addNewUser(firstName, middleName, lastName, numOfRecBook);
		boolean isUserFound = DBUtils.findUser(lastName, numOfRecBook);
		Assert.assertEquals(true, isUserFound);
	}

	@Test
	public void testFindUserRv1(){
		String firstName = "Vitaly";
		String middleName = "Andreevich";
		String lastName = "Trifonov";
		int numOfRecBook = 1553011;
		DBUtils.addNewUser(firstName, middleName, lastName, numOfRecBook);
		boolean isUserFound = DBUtils.findUser(lastName, 711); // other num of rec book
		Assert.assertEquals(false, isUserFound);
	}

	@Test
	public void testFindUserRv2(){
		String firstName = "Vitaly";
		String middleName = "Andreevich";
		String lastName = "Trifonov";
		int numOfRecBook = 1553011;
		DBUtils.addNewUser(firstName, middleName, lastName, numOfRecBook);
		boolean isUserFound = DBUtils.findUser("Alexandr", numOfRecBook); // other user last name
		Assert.assertEquals(false, isUserFound);
	}

	@Test
	public void testUpdDataStage1(){
		final double delta = 0.5; // TODO: moved to external constants which denote error
		final double fe = 55.1;
		final double c = 12.5;
		final double mn = 79;
		final int pEnv = 1000;
		final double weldArea = 15.345;
		final double weightMoltenMetal = 22.75;
		final int temperature = 1100;
		final double time = 5.5;

		int id = DBUtils.getLatestUserIdInMainTable();

		Container.Stage1 st1 = Container.getInstance().getStage1();
		st1.getFe().setAlloyCompWeight(fe);
		st1.getC().setAlloyCompWeight(c);
		st1.getMn().setAlloyCompWeight(mn);

		ExtraInputDataStage1 extra = new ExtraInputDataStage1();
		extra.setPressureEnv(pEnv);
		extra.setSurfaceWeldArea(weldArea);
		extra.setWeightMoltenMetal(weightMoltenMetal);
		extra.setTemperature(temperature);
		extra.setTime(time);

		// make update data
		DBUtils.updMsrDataStage1(id, st1, extra);

		// check main data
		Container.Stage1 mainFromDB = DBUtils.getMainMsrDataStage1(id);
		double actualAlloyCompWeightFe = mainFromDB.getFe().getAlloyCompWeight();
		double actualAlloyCompWeightC = mainFromDB.getC().getAlloyCompWeight();
		double actualAlloyCompWeightMn = mainFromDB.getMn().getAlloyCompWeight();
		Assert.assertEquals(fe, actualAlloyCompWeightFe, delta);
		Assert.assertEquals(c, actualAlloyCompWeightC, delta);
		Assert.assertEquals(mn, actualAlloyCompWeightMn, delta);

		// check extra data
		ExtraInputDataStage1 extraFromDB = DBUtils.getExtraMsrDataStage1(id);
		int actualPEnv = extraFromDB.getPressureEnv();
		double actualWeldArea = extraFromDB.getSurfaceWeldArea();
		double actualWeightMoltenMetal = extraFromDB.getWeightMoltenMetal();
		int actualTmpr = extraFromDB.getTemperature();
		double actualTime = extraFromDB.getTime();
		Assert.assertEquals(pEnv, actualPEnv);
		Assert.assertEquals(weldArea, actualWeldArea, delta);
		Assert.assertEquals(weightMoltenMetal, actualWeightMoltenMetal, delta);
		Assert.assertEquals(temperature, actualTmpr);
		Assert.assertEquals(time, actualTime, delta);
	}

}
