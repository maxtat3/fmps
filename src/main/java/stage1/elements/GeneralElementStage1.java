package stage1.elements;

import com.google.common.collect.ImmutableTable;
import model.Container;

public interface GeneralElementStage1 {

	String FE = Container.getInstance().getStage1().getFe().toString();
	String C = Container.getInstance().getStage1().getC().toString();
	String MN = Container.getInstance().getStage1().getMn().toString();
	String AL = Container.getInstance().getStage1().getAl().toString();
	String SI = Container.getInstance().getStage1().getSi().toString();
	String TI = Container.getInstance().getStage1().getTi().toString();

	/**
	 * Мольная масса (кг/моль)
	 */
	String MOLAR_MASS = "molar_mass";

	/**
	 * Высокотемпературные составляющие энтальпии,  HTo-H298o (Кдж/моль)
	 */
	String HIGH_TEMPER_ENTHALPY = "high_temperature_components_of_enthalpy";

	/**
	 * Теплоемкость, cp (Дж/мольК)
	 */
	String THERMAL_CAPACITY = "thermal_capacity";

	/**
	 * Скрытая теплота парообразования, deltaHкип0 (кДж/моль)
	 * Характеристика фазового превращения вещества.
	 */
	String HIDDEN_HEAT_VAPORIZATION = "hidden_heat_of_vaporization";

	/**
	 * Теплота испарения, deltaHисп (кДж/моль)
	 */
	String CLAPEYRON_CLAUSIUS_EQUATION_HEAT_OF_VAPORIZATION = "Clapeyron-Clausius_heat_of_vaporization";

	/**
	 * Коэфициент B в уравнении Клапейрона-Клаузиуса
	 */
	String CLAPEYRON_CLAUSIUS_EQUATION_B_FACTOR = "Clapeyron-Clausius_B_factor";

	/**
	 * Коэфициент A в уравнении нахождения парциального давления компонентов над сплавом
	 */
	String A_GAMMA_FACTOR = "A_gamma_factor";

	/**
	 * Коэфициент B в уравнении нахождения парциального давления компонентов над сплавом
	 */
	String B_GAMMA_FACTOR = "B_gamma_factor";

	/**
	 * Таблица констант элементов.
	 */
	ImmutableTable<String, String, Double> CONST_ELEMS =
		new ImmutableTable.Builder<String, String, Double>()
			.put(FE, MOLAR_MASS, 0.055847)
			.put(C, MOLAR_MASS, 0.01201)
			.put(MN, MOLAR_MASS, 0.054938)
			.put(AL, MOLAR_MASS, 0.02698)
			.put(SI, MOLAR_MASS, 0.0280855)
			.put(TI, MOLAR_MASS, 0.04788)

			.put(FE, HIGH_TEMPER_ENTHALPY, 73.61)
			.put(C, HIGH_TEMPER_ENTHALPY, 30.64)
			.put(MN, HIGH_TEMPER_ENTHALPY, 77.64)
			.put(AL, HIGH_TEMPER_ENTHALPY, 54.05)
			.put(SI, HIGH_TEMPER_ENTHALPY, 89.3)
			.put(TI, HIGH_TEMPER_ENTHALPY, 57.7)

			.put(FE, THERMAL_CAPACITY, 41.9)
			.put(C, THERMAL_CAPACITY, 24.9)
			.put(MN, THERMAL_CAPACITY, 46.0)
			.put(AL, THERMAL_CAPACITY, 26.3)
			.put(SI, THERMAL_CAPACITY, 25.6)
			.put(TI, THERMAL_CAPACITY, 33.5)

			.put(FE, HIDDEN_HEAT_VAPORIZATION, 418.0)
			.put(C, HIDDEN_HEAT_VAPORIZATION, 814.0)
			.put(MN, HIDDEN_HEAT_VAPORIZATION, 279.0)
			.put(AL, HIDDEN_HEAT_VAPORIZATION, 296.0)
			.put(SI, HIDDEN_HEAT_VAPORIZATION, 469.0)
			.put(TI, HIDDEN_HEAT_VAPORIZATION, 473.0)

			.put(FE, CLAPEYRON_CLAUSIUS_EQUATION_HEAT_OF_VAPORIZATION, 352.0)
			.put(C, CLAPEYRON_CLAUSIUS_EQUATION_HEAT_OF_VAPORIZATION, 714.0)
			.put(MN, CLAPEYRON_CLAUSIUS_EQUATION_HEAT_OF_VAPORIZATION, 220.0)
			.put(AL, CLAPEYRON_CLAUSIUS_EQUATION_HEAT_OF_VAPORIZATION, 296.0)
			.put(SI, CLAPEYRON_CLAUSIUS_EQUATION_HEAT_OF_VAPORIZATION, 473.0)
			.put(TI, CLAPEYRON_CLAUSIUS_EQUATION_HEAT_OF_VAPORIZATION, 427.0)

			.put(FE, CLAPEYRON_CLAUSIUS_EQUATION_B_FACTOR, 10.847)
			.put(C, CLAPEYRON_CLAUSIUS_EQUATION_B_FACTOR, 13.14)
			.put(MN, CLAPEYRON_CLAUSIUS_EQUATION_B_FACTOR, 9.98)
			.put(AL, CLAPEYRON_CLAUSIUS_EQUATION_B_FACTOR, 10.646)
			.put(SI, CLAPEYRON_CLAUSIUS_EQUATION_B_FACTOR, 12.681)
			.put(TI, CLAPEYRON_CLAUSIUS_EQUATION_B_FACTOR, 11.363)

			.put(FE, A_GAMMA_FACTOR, 0.0)
			.put(C, A_GAMMA_FACTOR, 0.0)
			.put(MN, A_GAMMA_FACTOR, 0.0)
			.put(AL, A_GAMMA_FACTOR, 2251.0)
			.put(SI, A_GAMMA_FACTOR, 6230.0)
			.put(TI, A_GAMMA_FACTOR, 3667.0)

			.put(FE, B_GAMMA_FACTOR, 0.0)
			.put(C, B_GAMMA_FACTOR, 0.0)
			.put(MN, B_GAMMA_FACTOR, 0.0)
			.put(AL, B_GAMMA_FACTOR, -0.00109)
			.put(SI, B_GAMMA_FACTOR, 0.376)
			.put(TI, B_GAMMA_FACTOR, 0.015)

			// write to table
			.build();



	// ******** getters and setters *********

	// defined for user in task
	void setAlloyCompWeight(double val);
	double getAlloyCompWeight();

	// 1. Мольную долю всех элементов сплава (%)
	// Mole fraction of the alloy elements
	void setMoleFractionAlloyElem(double val);
	double getMoleFractionAlloyElem();


	// 3. Давление пара чистых компонентов, Pi (Па)
	void setVaporPressureOfPureComps(double val);
	double getVaporPressureOfPureComps();

	// 4. Парциальное давление компонент над сплавом, Pip (Па)
	void setPartialPressureCompsOverAlloy(double val);
	double getPartialPressureCompsOverAlloy();


	// 6. Мольная доля каждого компонента в паре (%)
	// moleFractionEachElemInVapor

	// 7. Весовая доля каждого компонента в паре (%)
	// weightFractionEachElemInVapor

	// 8. Скорость испарения из сварочной ванны каждого элемента (гр/сек)
	// rateVaporizationEachElemOfWeldPool

	// 9. Скорость уменьшения массы расплавленного металла за счет испарения (гр/сек)
	// rateDecreaseMoltenMetalDueVaporization
}
