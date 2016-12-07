package db;

import app.SystemUtils;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
