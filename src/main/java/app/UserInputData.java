package app;

/**
 * Initial data that user must be enter.
 */
public class UserInputData {

	/**
	 * Input data for stage 1
	 */
	public class InputStage1 {
		// Состав сплава по весу (%)
		public static final double alloyCompWeightFe = 89.54;
		public static final double alloyCompWeightAl = 1.37;
		public static final double alloyCompWeightC = 2.11;
		public static final double alloyCompWeightMn = 5.12;
		public static final double alloyCompWeightSi = 1.86;

		// Давление окружающей среды (Па)
		public static final int envP = 100000;

		// Площадь свободной поверхности сварочной ванны (см2)
		public static final double surfaceWeldArea = 3.0;

		// Масса расплавленного металла (гр.)
		public static final double moltenMetalWeight = 11.3;

		// Температура расчета (град. Цельсия)
		public static final double T = 2100;

		// Время нахождения сплава в жидком состоянии (с)
		public static final double t = 18.4;
	}

}
