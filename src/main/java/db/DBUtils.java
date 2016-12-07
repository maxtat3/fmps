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
	public static final String LAST_NAME = "lastname";
	public static final String FIRST_NAME = "firstname";
	public static final String MIDDLE_NAME = "middlename";
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

	private static String dbStoredAbsPath;


	public DBUtils() {
		dbStoredAbsPath = SystemUtils.getUserCatalogAbsPath();
	}


	private static void initDatabase() throws Exception {
		Connection conn;
		Statement stmt;
		String dbNameAbsPath = dbStoredAbsPath + "/" + DB_NAME;
		System.out.println("db name = " + dbNameAbsPath);

		File dbFile = new File(dbNameAbsPath);
		if (dbFile.exists())
			return;

		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbStoredAbsPath + "/" + DB_NAME);
			System.out.println("Opened database successfully");
			stmt = conn.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_FMPS_MAIN + " (" +
				"id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  DEFAULT 1, " +
				LAST_NAME + " VARCHAR , " +
				FIRST_NAME + " VARCHAR , " +
				MIDDLE_NAME + " VARCHAR , " +
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
			System.out.println("Created new db file and executed init sql statement.");

			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Добавить нового пользователя.
	 *
	 * @param userLastName Имя
	 * @param userFirstName Отчество
	 * @param userMiddleName Фамилия
	 */
	private static void addNewUser(String userLastName, String userFirstName, String userMiddleName) {
		String sql = "INSERT INTO " + TABLE_FMPS_MAIN + " (" +
			LAST_NAME + DLC + FIRST_NAME + DLC + MIDDLE_NAME +
			") VALUES (\"" + userLastName + "\"" + DLC + "\"" + userFirstName + "\"" + DLC + "\"" + userMiddleName + "\"" + ");";
		sqlStatementExecutor(sql);
	}

	/**
	 * Получение информации о пользователе из БД.
	 *
	 * @param userId id пользователя в БД
	 * @return пользователь
	 */
	private static User getUser(int userId) {
		String sql = "SELECT "+ LAST_NAME + DLC + FIRST_NAME + DLC + MIDDLE_NAME + " FROM " + TABLE_FMPS_MAIN + " WHERE id=" + userId + ";";
//		System.out.println("sql = " + sql);
		Connection conn;
		Statement stmt;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbStoredAbsPath + "/" + DB_NAME);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			User user = new User(rs.getString(LAST_NAME), rs.getString(FIRST_NAME), rs.getString(MIDDLE_NAME));
			rs.close();
			stmt.close();
			conn.close();
			return user;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Проверка (по ФИО) есть ли уже такой пользователь в БД
	 *
	 * @param userLastName Имя
	 * @param userFirstName Отчество
	 * @param userMiddleName Фамилия
	 * @return <tr>true</tr> такой пользователь уже существует, <tr>false</tr> такого пользователя нет в БД
	 */
	private static boolean isUserExist(String userLastName, String userFirstName, String userMiddleName ) {
		Connection conn;
		Statement stmt;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbStoredAbsPath + "/" + DB_NAME);
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(
				"SELECT " + LAST_NAME + DLC + FIRST_NAME + DLC + MIDDLE_NAME + " FROM " + TABLE_FMPS_MAIN
			);
			List<User> allUsers = new ArrayList<>();
			while (rs.next()) {
				User user = new User(rs.getString(LAST_NAME), rs.getString(FIRST_NAME), rs.getString(MIDDLE_NAME));
				allUsers.add(user);
			}
			for (User user : allUsers) {
				if (user.getLastName().equals(userLastName) &&
					user.getFirstName().equals(userFirstName) &&
					user.getMiddleName().equals(userMiddleName)) {
					return true;
				}
			}

			stmt.close();
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Стереть пользователя из БД по его id.
	 * Если такого пользователя нет в БД, то ничего не выполняется.
	 *
	 * @param userId id пользователя
	 */
	private static void removeUser(int userId) {
		String sql = "DELETE FROM " + TABLE_FMPS_MAIN + " WHERE ID=" + userId;
		sqlStatementExecutor(sql);
	}


	// todo - для всех методов, которые добавляют/изменяют данные проверять, есть ли данные ФИО пользователя в БД !
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
	private static void resetStageProgress(Stage stage, int userId) {
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


	/**
	 * Выбор задачи
	 */
	private enum Stage {
		STAGE_1, STAGE_2, STAGE_3
	}

	/**
	 * General method for execute sql statements.
	 *
	 * @param sql query
	 */
	private static void sqlStatementExecutor(String sql) {
		Connection conn;
		Statement stmt;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbStoredAbsPath + "/" + DB_NAME);
			System.out.println("Opened database successfully");

			stmt = conn.createStatement();
			stmt.execute(sql);
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
