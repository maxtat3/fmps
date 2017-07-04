package app;

/**
 * Выбор задачи
 */
public enum Stage {

	STAGE_1("Задача 1. Расчет процессов испарения металлов при сварке плавлением"),
	STAGE_2("Задача 2. Расчет химического состава активной защитной газовой смеси"),
	STAGE_3("Задача 3. Расчет раскислительной способности элементов сплава");

	Stage(String name) {
		this.name = name;
	}

	private String name;

	public String getName() {
		return name;
	}
}
