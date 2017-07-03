package util;

/**
 *
 */
public class TimeWatcher {
	private static final String LOG_WATCHER = "Time watcher >>> ";
	private static long startTime;
	private static long endTime;

	/**
	 * Запуск таймера
	 * @param startMsg сообщение в консоли при старте.
	 */
	public static void start(String startMsg) {
		System.out.println("---");
		System.out.println(LOG_WATCHER + startMsg);
		startTime = System.currentTimeMillis();
	}

	/**
	 * Остановка таймера. После остановки выводиться результат измерения.
	 * @param endMsg сообщение в консоли при остановке.
	 */
	public static void stop(String endMsg) {
		endTime = System.currentTimeMillis();
		System.out.println(LOG_WATCHER + endMsg);
		System.out.println(LOG_WATCHER + "delta time = " + (endTime - startTime) + " ms");
		System.out.println("--------");
		clear();
	}

	/**
	 * Сброс переменных
	 */
	private static void clear() {
		startTime = 0L;
		endTime = 0L;
	}
}