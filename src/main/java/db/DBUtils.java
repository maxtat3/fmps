package db;

import app.SystemUtils;
import domain.User;
import model.Container;
import stage1.ExtraInputDataStage1;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {

	private static final String DB_NAME = "fmps.db";
	public static final String TABLE_FMPS_MAIN = "fmps_main";
	public static final String DLC = ", ";

	// Columns
	public static final String FIRST_NAME = "firstname"; // Имя
	public static final String MIDDLE_NAME = "middlename"; // Отчество
	public static final String LAST_NAME = "lastname"; // Фамилия
	public static final String NUM_OF_RECORD_BOOK = "num_of_record_book"; // Номер зачетной книжки
	public static final String ST1_PROGRESS = "st1_progress";
	public static final String ST2_PROGRESS = "st2_progress";
	public static final String ST3_PROGRESS = "st3_progress";
	public static final String ST1_FE = "st1_fe";
	public static final String ST1_C = "st1_c";
	public static final String ST1_MN = "st1_mn";
	public static final String ST1_P_ENV = "st1_p_env";
	public static final String ST1_F_SURF_WELD_AREA = "st1_f_surface_area";
	public static final String ST1_MP_WEIGHT_MOLTEN_METAL = "st1_mp_weight_molten_metal";
	public static final String ST1_TEMPERATURE = "st1_temperature";
	public static final String ST1_TIME = "st1_time";
	public static final String ST2_ARGON = "st2_argon";
	public static final String ST2_CO2 = "st2_co2";
	public static final String ST2_O2 = "st2_o2";
	public static final String ST2_CO = "st2_co";
	public static final String ST2_O = "st2_o";
	public static final String ST2_C = "st2_c";
	public static final String ST2_TEMPERATURE = "st2_temperature";
	public static final String ST3_CO_PARTIAL_PRESSURE = "st3_co_partial_pressure";
	public static final String ST3_O_PARTIAL_PRESSURE = "st3_o_partial_pressure";
	public static final String ST3_O_DISSOLVE = "st3_dissolve";
	public static final String ST3_ATM_PRESSURE = "st3_atm_pressure";
	public static final String ST3_TEMPERATURE = "st3_temperature";

	private static String dbStoredAbsPath = SystemUtils.getUserCatalogAbsPath();
	private static String dbNameAbsPath = dbStoredAbsPath + "/" + DB_NAME;


	public static void initDatabase(){
		File dbFile = new File(dbNameAbsPath);
		if (dbFile.exists())
			return;

		Statement stmt = sqlExecutor(SqlAction.PREPARE);
		if (stmt != null) {
			try {
				String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_FMPS_MAIN + " (" +
					"id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  DEFAULT 1, " +
					FIRST_NAME + " VARCHAR , " +
					MIDDLE_NAME + " VARCHAR , " +
					LAST_NAME + " VARCHAR , " +
					NUM_OF_RECORD_BOOK + " INT , " +
					ST1_PROGRESS + " INTEGER DEFAULT 0, " +
					ST2_PROGRESS + " INTEGER DEFAULT 0, " +
					ST3_PROGRESS + " INTEGER DEFAULT 0, " +
					ST1_FE + " DOUBLE DEFAULT 0, " +
					ST1_C + " DOUBLE DEFAULT 0, " +
					ST1_MN + " DOUBLE DEFAULT 0, " +
					ST1_P_ENV + " INTEGER , " +
					ST1_F_SURF_WELD_AREA + " DOUBLE , " +
					ST1_MP_WEIGHT_MOLTEN_METAL + " DOUBLE , " +
					ST1_TEMPERATURE + " INTEGER , " +
					ST1_TIME + " DOUBLE , " +
					ST2_ARGON + " DOUBLE ," +
					ST2_CO2 + " DOUBLE ," +
					ST2_O2 + " DOUBLE ," +
					ST2_CO + " DOUBLE ," +
					ST2_O + " DOUBLE ," +
					ST2_C + " DOUBLE ," +
					ST2_TEMPERATURE + " INTEGER ," +
					ST3_CO_PARTIAL_PRESSURE + " DOUBLE ," +
					ST3_O_PARTIAL_PRESSURE + " DOUBLE ," +
					ST3_O_DISSOLVE + " DOUBLE ," +
					ST3_ATM_PRESSURE + " INTEGER ," +
					ST3_TEMPERATURE + " DOUBLE" +
					");";
				stmt.execute(sql);
				sqlExecutor(SqlAction.CLOSE);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Добавить нового пользователя.
	 *
	 * @param userFirstName Имя
	 * @param userMiddleName Отчество
	 * @param userLastName Фамилия
	 * @param numOfRecordBook Номер зачетной книжки
	 */
	public static void addNewUser(String userFirstName, String userMiddleName, String userLastName, int numOfRecordBook) {
		String sql = "INSERT INTO " + TABLE_FMPS_MAIN + " ("
			+ FIRST_NAME + DLC + MIDDLE_NAME + DLC + LAST_NAME + DLC + NUM_OF_RECORD_BOOK
			+ ") VALUES ("
			+ "\"" + userFirstName + "\"" + DLC
			+ "\"" + userMiddleName + "\"" + DLC
			+ "\"" + userLastName + "\"" + DLC
			+ "\"" + numOfRecordBook + "\""
			+ ");";
		sqlStatementExecutor(sql);
	}

	/**
	 * Получение ФИО пользователя.
	 *
	 * @param userId id пользователя в БД
	 * @return пользователь
	 */
	public static User getUser(int userId) {
		String sql = "SELECT "+ FIRST_NAME + DLC + MIDDLE_NAME + DLC + LAST_NAME + DLC + NUM_OF_RECORD_BOOK
			+ " FROM " + TABLE_FMPS_MAIN + " WHERE id=" + userId + ";";
		User user = null;
		try {
			Statement stmt = sqlExecutor(SqlAction.PREPARE);
			if (stmt != null) {
				ResultSet rs = stmt.executeQuery(sql);
				user = new User(rs.getString(FIRST_NAME), rs.getString(MIDDLE_NAME), rs.getString(LAST_NAME), rs.getInt(NUM_OF_RECORD_BOOK));
				rs.close();
				sqlExecutor(SqlAction.CLOSE);
			}
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Find is user before registered in this app.
	 *
	 * @param lastName user last name
	 * @param numberOfRecordBook user number of record book
	 * @return <tt>true</tt> if searched user is present in db
	 */
	public static boolean findUser(String lastName, int numberOfRecordBook){
		String sql = "SELECT "+ LAST_NAME + DLC + NUM_OF_RECORD_BOOK + " FROM " + TABLE_FMPS_MAIN ;
		Statement stmt = sqlExecutor(SqlAction.PREPARE);
		if (stmt != null) {
			try {
				ResultSet rs = stmt.executeQuery(sql);
				List<User> allUsers = new ArrayList<>();
				while (rs.next()) {
					User user = new User(rs.getString(LAST_NAME), rs.getInt(NUM_OF_RECORD_BOOK));
					allUsers.add(user);
				}
				for (User user : allUsers) {
					if (
						user.getLastName().equals(lastName) &&
						user.getNumberOfRecordBook() == numberOfRecordBook) {
						return true;
					}
				}
				rs.close();
				sqlExecutor(SqlAction.CLOSE);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static void updateUser(int userId, User updUserData) {
		Statement stmt = sqlExecutor(SqlAction.PREPARE);
		if (stmt != null) {
			try {
				ResultSet rs = stmt.executeQuery("SELECT " + FIRST_NAME + DLC + MIDDLE_NAME + DLC + LAST_NAME + DLC
					+ NUM_OF_RECORD_BOOK + " FROM " + TABLE_FMPS_MAIN + " WHERE ID=" + userId);
				User oldUserData = new User(rs.getString(FIRST_NAME), rs.getString(MIDDLE_NAME), rs.getString(LAST_NAME),
					rs.getInt(NUM_OF_RECORD_BOOK));

				String updFName = updUserData.getFirstName();
				String updMName = updUserData.getMiddleName();
				String updLName = updUserData.getLastName();
				int updBookNum = updUserData.getNumberOfRecordBook();

				if (!userDataValidator(oldUserData.getFirstName(), updFName)) updFName = oldUserData.getFirstName();
				if (!userDataValidator(oldUserData.getMiddleName(), updMName)) updMName = oldUserData.getMiddleName();
				if (!userDataValidator(oldUserData.getLastName(), updLName)) updLName = oldUserData.getLastName();
				if (!userDataValidator(oldUserData.getNumberOfRecordBook(), updBookNum))
					updBookNum = oldUserData.getNumberOfRecordBook();

				stmt.execute("UPDATE " + TABLE_FMPS_MAIN + " SET "
					+ FIRST_NAME + "=" + "'" + updFName + "'" + DLC
					+ MIDDLE_NAME + "=" + "'" + updMName + "'" + DLC
					+ LAST_NAME + "=" + "'" + updLName + "'" + DLC
					+ NUM_OF_RECORD_BOOK + "=" + "'" + updBookNum + "'"
					+ " WHERE ID = " + userId);
				rs.close();
				sqlExecutor(SqlAction.CLOSE);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static boolean userDataValidator(String oldUserField, String newUserField) {
		return newUserField != null && !newUserField.equals("") && !newUserField.equals(" ")
			&& newUserField.length() > 1 && !oldUserField.equals(newUserField);
	}

	private static boolean userDataValidator(int oldUserField, int newUserField) {
		return oldUserField != newUserField && newUserField > 0;
	}

	//todo - may be changed signature method to pass only User object
	/**
	 * Проверка (по ФИО) есть ли уже такой пользователь в БД
	 *
	 * @param userFirstName Имя
	 * @param userMiddleName Отчество
	 * @param userLastName Фамилия
	 * @return <tr>true</tr> такой пользователь уже существует, <tr>false</tr> такого пользователя нет в БД
	 */
	public static boolean isUserExist(String userFirstName, String userMiddleName, String userLastName, int numOfRecordBook) {
		String sql = "SELECT " + FIRST_NAME + DLC + MIDDLE_NAME + DLC + LAST_NAME  + DLC + NUM_OF_RECORD_BOOK + " FROM " + TABLE_FMPS_MAIN;
		Statement stmt = sqlExecutor(SqlAction.PREPARE);
		if (stmt != null) {
			try {
				ResultSet rs = stmt.executeQuery(sql);
				List<User> allUsers = new ArrayList<>();
				while (rs.next()) {
					User user = new User(rs.getString(FIRST_NAME), rs.getString(MIDDLE_NAME), rs.getString(LAST_NAME),
						rs.getInt(NUM_OF_RECORD_BOOK));
					allUsers.add(user);
				}
				for (User user : allUsers) {
					if (user.getFirstName().equals(userFirstName) &&
						user.getMiddleName().equals(userMiddleName) &&
						user.getLastName().equals(userLastName) &&
						user.getNumberOfRecordBook() == numOfRecordBook) {
						return true;
					}
				}
				rs.close();
				sqlExecutor(SqlAction.CLOSE);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * Стереть пользователя из БД по его id.
	 * Если такого пользователя нет в БД, то ничего не выполняется.
	 *
	 * @param userId id пользователя
	 */
	public static void removeUser(int userId) {
		String sql = "DELETE FROM " + TABLE_FMPS_MAIN + " WHERE ID=" + userId;
		sqlStatementExecutor(sql);
	}

	// todo - В запросе должны быть добавлены одинарные кавычки, пример ... UPDATE TABLE7 SET st1_fe='791', ...  !
	// todo - Для всех методов, которые добавляют/изменяют данные проверять, есть ли данные ФИО пользователя в БД !
	public static void updMsrDataStage1(int userId, Container.Stage1 st1, ExtraInputDataStage1 extra) {
		String sql = "UPDATE " + TABLE_FMPS_MAIN + " SET " +
			ST1_FE + "=" + st1.getFe().getAlloyCompWeight() + DLC +
			ST1_C + "=" + st1.getC().getAlloyCompWeight() + DLC +
			ST1_MN + "=" + st1.getMn().getAlloyCompWeight() + DLC +
			ST1_P_ENV + "=" + extra.getPressureEnv() + DLC +
			ST1_F_SURF_WELD_AREA + "=" + extra.getSurfaceWeldArea() + DLC +
			ST1_MP_WEIGHT_MOLTEN_METAL + "=" + extra.getWeightMoltenMetal() + DLC +
			ST1_TEMPERATURE + "=" + extra.getTemperature() + DLC +
			ST1_TIME + "=" + extra.getTime() + " " +
			"WHERE ID=" + userId;
		sqlStatementExecutor(sql);
	}

	/**
	 * Retrieve chemical elements for stage 1 from user id.
	 *
	 * @param userId user id which data will be retrieved
	 * @return chemical elements
	 */
	public static Container.Stage1 getMainMsrDataStage1(int userId) {
		Container.Stage1 st1 = new Container().new Stage1();

		String sql = "SELECT " + ST1_FE + DLC + ST1_C + DLC + ST1_MN
			+ " FROM " + TABLE_FMPS_MAIN + " WHERE ID = " + userId;
		Statement stmt = sqlExecutor(SqlAction.PREPARE);
		if (stmt != null) {
			try {
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					st1.getFe().setAlloyCompWeight(rs.getDouble(ST1_FE));
					st1.getC().setAlloyCompWeight(rs.getDouble(ST1_C));
					st1.getMn().setAlloyCompWeight(rs.getDouble(ST1_MN));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return st1;
	}

	/**
	 * Retrieve additional user input parameters for stage 1.
	 *
	 * @param userId user id which data will be retrieved
	 * @return additional user input parameters
	 */
	public static ExtraInputDataStage1 getExtraMsrDataStage1(int userId) {
		ExtraInputDataStage1 extData = new ExtraInputDataStage1();

		String sql = "SELECT " + ST1_P_ENV + DLC +
			ST1_F_SURF_WELD_AREA + DLC +
			ST1_MP_WEIGHT_MOLTEN_METAL + DLC +
			ST1_TEMPERATURE + DLC +
			ST1_TIME +
			" FROM " + TABLE_FMPS_MAIN + " WHERE ID = " + userId;
		Statement stmt = sqlExecutor(SqlAction.PREPARE);
		if (stmt != null) {
			try {
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					extData.setPressureEnv(rs.getInt(ST1_P_ENV));
					extData.setSurfaceWeldArea(rs.getDouble(ST1_F_SURF_WELD_AREA));
					extData.setWeightMoltenMetal(rs.getDouble(ST1_MP_WEIGHT_MOLTEN_METAL));
					extData.setTemperature(rs.getInt(ST1_TEMPERATURE));
					extData.setTime(rs.getDouble(ST1_TIME));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return extData;
	}

	private static void updMsrDataStage2(int userId, double st2Argon, double st2CO2, double st2O2, double st2CO, double st2O, double st2C, double st2Temperature){
		String sql = "UPDATE " + TABLE_FMPS_MAIN + " SET " +
			ST2_ARGON + "=" + st2Argon + DLC +
			ST2_CO2 + "=" + st2CO2 + DLC +
			ST2_O2 + "=" + st2O2 + DLC +
			ST2_CO + "=" + st2CO + DLC +
			ST2_O + "=" + st2O + DLC +
			ST2_C + "=" + st2C + DLC +
			ST2_TEMPERATURE + "=" + st2Temperature + " " +
			"WHERE ID=" + userId;

		sqlStatementExecutor(sql);
	}

	private static void updMsrDataStage3(int userId, double st3COPartialPrs, double st3OPartialPrs, double st3ODissolve, int st3AtmPrs, double st3Temperature){
		String sql = "UPDATE " + TABLE_FMPS_MAIN + " SET " +
			ST3_CO_PARTIAL_PRESSURE + "=" + st3COPartialPrs + DLC +
			ST3_O_PARTIAL_PRESSURE + "=" + st3OPartialPrs + DLC +
			ST3_O_DISSOLVE + "=" + st3ODissolve + DLC +
			ST3_ATM_PRESSURE + "=" + st3AtmPrs + DLC +
			ST3_TEMPERATURE + "=" + st3Temperature + " " +
			"WHERE ID=" + userId;

		sqlStatementExecutor(sql);
	}

	/**
	 * Увеличтить прогресс на 1 для задачи stage для пользователя по его id
	 *
	 * @param stage номер задачи
	 * @param userId id пользователя
	 */
	public static void incrementProgress(Stage stage, int userId) {
		String stageProgressColumn = getStage(stage);

		Statement stmt = sqlExecutor(SqlAction.PREPARE);
		if (stmt != null) {
			try {
				ResultSet rs = stmt.executeQuery("SELECT " + stageProgressColumn + " FROM " + TABLE_FMPS_MAIN + " WHERE ID=" + userId);
				int progress = 0;
				while(rs.next()) progress = rs.getInt(1);
				stmt.execute("UPDATE " + TABLE_FMPS_MAIN + " SET " + stageProgressColumn + "=" + ++progress + " WHERE ID=" + userId);
				rs.close();
				sqlExecutor(SqlAction.CLOSE);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Получить прогресс пользователя для конкретной задачи.
	 *
	 * @param stage номер задачи
	 * @param userId id пользователя
	 * @return прогресс пользователя, по умолчанию возвразется -1 - запрос не выполнен.
	 */
	public static int getProgress(Stage stage, int userId) {
		int progress = -1;
		String stageProgressColumn = getStage(stage);

		Statement stmt = sqlExecutor(SqlAction.PREPARE);
		if (stmt != null) {
			try {
				ResultSet rs = stmt.executeQuery("SELECT " + stageProgressColumn + " FROM " + TABLE_FMPS_MAIN + " WHERE ID=" + userId);
				while (rs.next()) {
					progress = rs.getInt(1);
				}
				rs.close();
				sqlExecutor(SqlAction.CLOSE);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return progress;
	}

	/**
	 * Сбросить прогресс конкретного пользователя для задачи stage
	 *
	 * @param stage номер задачи
	 * @param userId id пользователя
	 */
	public static void resetProgress(Stage stage, int userId) {
		String stageProgressColumn = getStage(stage);

		Statement stmt = sqlExecutor(SqlAction.PREPARE);
		if (stmt != null) {
			try {
				stmt.execute("UPDATE " + TABLE_FMPS_MAIN + " SET " + stageProgressColumn + " = " + "0" + " WHERE ID=" + userId);
				stmt.close();
				sqlExecutor(SqlAction.CLOSE);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static String getStage(Stage stage) {
		String stageProgressColumn = null;
		switch (stage) {
			case STAGE_1:
				stageProgressColumn = ST1_PROGRESS;
				break;

			case STAGE_2:
				stageProgressColumn = ST2_PROGRESS;
				break;

			case STAGE_3:
				stageProgressColumn = ST3_PROGRESS;
				break;
		}
		return stageProgressColumn;
	}

	/**
	 * Return of count of records in main table.
	 *
	 * @return count of records
	 */
	public static int countOfRecordsInMainTable(){
		Statement stmt = sqlExecutor(SqlAction.PREPARE);
		int countRecords = 0;

		if (stmt != null) {
			try {
				ResultSet rs = stmt.executeQuery("SELECT Count(*) FROM " + TABLE_FMPS_MAIN);
				while (rs.next()) {
					countRecords = rs.getInt(1);
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return countRecords;
	}

	// todo - refactoring - simplify at next sql "SELECT id FROM " + TABLE_FMPS_MAIN + " WHERE id = (SELECT MAX(id) FROM " + TABLE_FMPS_MAIN + ")";
	// todo - refactoring - move this method up
	/**
	 * Get last user id in table {@link #TABLE_FMPS_MAIN}.
	 *
	 * @return last user id
	 */
	public static int getLatestUserIdInMainTable(){
		int id = -1;
		Statement stmt = sqlExecutor(SqlAction.PREPARE);
		ResultSet rs;

		if (stmt != null) {
			try {
				rs = stmt.executeQuery("SELECT id FROM " + TABLE_FMPS_MAIN);
				while (rs.next()) {
					id = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return id;
	}


	private static void dropMainTable() {
		sqlStatementExecutor("DROP TABLE " + TABLE_FMPS_MAIN);
	}

	/**
	 * General method for execute sql statements.
	 *
	 * @param sql query
	 */
	private static void sqlStatementExecutor(String sql) {
		Statement stmt = sqlExecutor(SqlAction.PREPARE);
		if (stmt != null) {
			try {
				stmt.execute(sql);
				sqlExecutor(SqlAction.CLOSE);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Выбор задачи
	 */
	public enum Stage {
		STAGE_1, STAGE_2, STAGE_3
	}


	/**
	 * Prepare sql to execute connection.
	 * See available actions in {@link SqlAction}:
	 * {@link SqlAction#PREPARE} - open db and prepare connection,
	 * {@link SqlAction#CLOSE} - close {@link Statement} and {@link Connection} if it are early open
	 *
	 * @param action
	 * @return Statement reference
	 */
	private static Statement sqlExecutor(SqlAction action) {
		Connection conn = null;
		Statement stmt = null;

		if (action == SqlAction.PREPARE) {
			try {
				Class.forName("org.sqlite.JDBC");
				conn = DriverManager.getConnection("jdbc:sqlite:" + dbStoredAbsPath + "/" + DB_NAME);
				stmt = conn.createStatement();
				return stmt;
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

		} else if (action == SqlAction.CLOSE) {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	private enum SqlAction {
		PREPARE, // init and return resultSet object
		CLOSE // close connection
	}
}
