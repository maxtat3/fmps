package app;

public class SystemUtils {

	/**
	 * Get absolute path to user catalog data.
	 */
	public static String getUserCatalogAbsPath() {
		String absPath = null;

		if (OsUtils.isWindows()) {
			// get env LOCALAPPDATA - returned null in winxp
			// get env PROGRAMPDATA - returned null in winxp
			// get env APPDATA - returned [C:\Documents and Settings\UsrName\Application Data] path in winxp
			absPath = System.getenv("APPDATA");
			System.out.println("Win platform");
			System.out.println("win env dir = " + absPath);

		} else if (OsUtils.isUnix()) {
			absPath = System.getProperty("user.dir");
			System.out.println("Unix");
			System.out.println("user.dir = " + absPath);

		} else {
			System.out.println("Not determine OS !");
		}

		return absPath;
	}

	private static final class OsUtils {
		private static String OS = null;

		public static String getOsName() {
			if(OS == null) {
				OS = System.getProperty("os.name");
			}
			return OS;
		}
		public static boolean isWindows() {
			return getOsName().startsWith("Windows");
		}

		public static boolean isUnix() {
			return getOsName().startsWith("Linux");
		}
	}
}
