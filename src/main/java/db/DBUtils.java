package db;

import app.SystemUtils;
import domain.User;

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
	 */
	public static void addNewUser(String userFirstName, String userMiddleName, String userLastName) {
		String sql = "INSERT INTO " + TABLE_FMPS_MAIN + " (" +
			FIRST_NAME + DLC + MIDDLE_NAME + DLC + LAST_NAME +
			") VALUES (" +
			"\"" + userFirstName + "\"" + DLC
			+ "\"" + userMiddleName + "\"" + DLC
			+ "\"" + userLastName + "\""
			+ ");";
		sqlStatementExecutor(sql);
	}

	/**
	 * Получение информации о пользователе из БД.
	 *
	 * @param userId id пользователя в БД
	 * @return пользователь
	 */
	public static User getUser(int userId) {
		String sql = "SELECT "+ FIRST_NAME + DLC + MIDDLE_NAME + DLC + LAST_NAME + " FROM " + TABLE_FMPS_MAIN + " WHERE id=" + userId + ";";
		User user = null;
		try {
			Statement stmt = sqlExecutor(SqlAction.PREPARE);
			if (stmt != null) {
				ResultSet rs = stmt.executeQuery(sql);
				user = new User(rs.getString(FIRST_NAME), rs.getString(MIDDLE_NAME), rs.getString(LAST_NAME));
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
	 * Проверка (по ФИО) есть ли уже такой пользователь в БД
	 *
	 * @param userFirstName Имя
	 * @param userMiddleName Отчество
	 * @param userLastName Фамилия
	 * @return <tr>true</tr> такой пользователь уже существует, <tr>false</tr> такого пользователя нет в БД
	 */
	public static boolean isUserExist(String userFirstName, String userMiddleName, String userLastName) {
		String sql = "SELECT " + FIRST_NAME + DLC + MIDDLE_NAME + DLC + LAST_NAME + " FROM " + TABLE_FMPS_MAIN;
		Statement stmt = sqlExecutor(SqlAction.PREPARE);
		if (stmt != null) {
			try {
				ResultSet rs = stmt.executeQuery(sql);
				List<User> allUsers = new ArrayList<>();
				while (rs.next()) {
					User user = new User(rs.getString(FIRST_NAME), rs.getString(MIDDLE_NAME), rs.getString(LAST_NAME));
					allUsers.add(user);
				}
				for (User user : allUsers) {
					if (user.getFirstName().equals(userFirstName) &&
						user.getMiddleName().equals(userMiddleName) &&
						user.getLastName().equals(userLastName)) {
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
	private static void updMsrDataStage1(int userId, double st1Fe, double st1C, double st1Mn,
	                                     int st1PEnv, double st1FSurfaceWeldArea, double st1WeightMoltenMetal, int st1Temperature, double st1Time) {
		String sql = "UPDATE " + TABLE_FMPS_MAIN + " SET " +
			ST1_FE + "=" + st1Fe + DLC +
			ST1_C + "=" + st1C + DLC +
			ST1_MN + "=" + st1Mn + DLC +
			ST1_P_ENV + "=" + st1PEnv + DLC +
			ST1_F_SURF_WELD_AREA + "=" + st1FSurfaceWeldArea + DLC +
			ST1_MP_WEIGHT_MOLTEN_METAL + "=" + st1WeightMoltenMetal + DLC +
			ST1_TEMPERATURE + "=" + st1Temperature + DLC +
			ST1_TIME + "=" + st1Time + " " +
			"WHERE ID=" + userId;

		System.out.println("sql = [" + sql + "]");
		sqlStatementExecutor(sql);
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
	private static void incrementProgress(Stage stage, int userId) {
		String stageProgressColumn = getStage(stage);

		Connection conn;
		Statement stmt;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbStoredAbsPath + "/" + DB_NAME);
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT " + stageProgressColumn + " FROM " + TABLE_FMPS_MAIN + " WHERE ID="+userId);
			int progress = rs.getInt(userId);
			stmt.execute("UPDATE " + TABLE_FMPS_MAIN + " SET " + stageProgressColumn + "=" + ++progress + " WHERE ID=" + userId);
			stmt.execute("SELECT " + stageProgressColumn + " FROM " + TABLE_FMPS_MAIN + " WHERE ID="+userId);

			rs.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Сбросить прогресс конкретного пользователя для задачи stage
	 *
	 * @param stage номер задачи
	 * @param userId id пользователя
	 */
	private static void resetProgress(Stage stage, int userId) {
		String stageProgressColumn = getStage(stage);

		Connection conn;
		Statement stmt;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbStoredAbsPath + "/" + DB_NAME);
			stmt = conn.createStatement();

			stmt.execute(
				"UPDATE " + TABLE_FMPS_MAIN + " SET " +stageProgressColumn + " = " + "0" + " WHERE ID="+userId
			);

			stmt.close();
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
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
				ResultSet rs = stmt.executeQuery(
					"SELECT Count(*) FROM " + TABLE_FMPS_MAIN
				);
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
	private enum Stage {
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
