package stage1.elements;

import com.google.common.collect.ImmutableTable;
import model.Container;

public interface GeneralElementStage1 {

	String AL = Container.getInstance().getStage1().getAl().toString();
	String B = Container.getInstance().getStage1().getB().toString();
	String C = Container.getInstance().getStage1().getC().toString();
	String CR = Container.getInstance().getStage1().getCr().toString();
	String CU = Container.getInstance().getStage1().getCu().toString();
	String FE = Container.getInstance().getStage1().getFe().toString();
	String HF = Container.getInstance().getStage1().getHf().toString();
	String MG = Container.getInstance().getStage1().getMg().toString();
	String MN = Container.getInstance().getStage1().getMn().toString();
	String MO = Container.getInstance().getStage1().getMo().toString();
	String NB = Container.getInstance().getStage1().getNb().toString();
	String NI = Container.getInstance().getStage1().getNi().toString();
	String RE = Container.getInstance().getStage1().getRe().toString();
	String SI = Container.getInstance().getStage1().getSi().toString();
	String TA = Container.getInstance().getStage1().getTa().toString();
	String TI = Container.getInstance().getStage1().getTi().toString();
	String V = Container.getInstance().getStage1().getV().toString();
	String W = Container.getInstance().getStage1().getW().toString();
	String ZR = Container.getInstance().getStage1().getZr().toString();

	/**
	 * Мольная масса (кг/моль)
	 */
	String ATOMIC_FRACTION = "atomic_fraction_of_element";

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
			// see http://raal100.narod.ru/index/0-616 or http://www.alhimik.ru/teleclass/sprav/massa.shtml
			.put(AL, ATOMIC_FRACTION, 0.02698)
			.put(B, ATOMIC_FRACTION, 0.01081)
			.put(C, ATOMIC_FRACTION, 0.01201)
			.put(CR, ATOMIC_FRACTION, 0.051996)
			.put(CU, ATOMIC_FRACTION, 0.063546)
			.put(FE, ATOMIC_FRACTION, 0.055847)
			.put(HF, ATOMIC_FRACTION, 0.17849)
			.put(MG, ATOMIC_FRACTION, 0.024305)
			.put(MN, ATOMIC_FRACTION, 0.054938)
			.put(MO, ATOMIC_FRACTION, 0.02698)
			.put(NB, ATOMIC_FRACTION, 0.092906)
			.put(NI, ATOMIC_FRACTION, 0.05869)
			.put(RE, ATOMIC_FRACTION, 0.186207)
			.put(SI, ATOMIC_FRACTION, 0.0280855)
			.put(TA, ATOMIC_FRACTION, 0.180948)
			.put(TI, ATOMIC_FRACTION, 0.04788)
			.put(V, ATOMIC_FRACTION, 0.050942)
			.put(W, ATOMIC_FRACTION, 0.18385)
			.put(ZR, ATOMIC_FRACTION, 0.091224)

			// see table P3, page 39 in methodical book
			.put(AL, HIGH_TEMPER_ENTHALPY, 54.05)
			.put(B, HIGH_TEMPER_ENTHALPY, 0d) // TODO: 07.06.17 - in table P3, page 39
			.put(C, HIGH_TEMPER_ENTHALPY, 30.64)
			.put(CR, HIGH_TEMPER_ENTHALPY, 50.08)
			.put(CU, HIGH_TEMPER_ENTHALPY, 56.35)
			.put(FE, HIGH_TEMPER_ENTHALPY, 73.61)
			.put(HF, HIGH_TEMPER_ENTHALPY, 0d) // TODO: 07.06.17 not presented in table P3, page 39
			.put(MG, HIGH_TEMPER_ENTHALPY, 29.23) // TODO: 07.06.17 presented for T=1400 Kelvin in table P3, page 39
			.put(MN, HIGH_TEMPER_ENTHALPY, 77.64)
			.put(MO, HIGH_TEMPER_ENTHALPY, 43.40)
			.put(NB, HIGH_TEMPER_ENTHALPY, 41.84)
			.put(NI, HIGH_TEMPER_ENTHALPY, 67.84)
			.put(RE, HIGH_TEMPER_ENTHALPY, 0d) // TODO: 07.06.17 not presented in table P3, page 39
			.put(SI, HIGH_TEMPER_ENTHALPY, 89.3)
			.put(TA, HIGH_TEMPER_ENTHALPY, 0d) // TODO: 07.06.17 not presented in table P3, page 39
			.put(TI, HIGH_TEMPER_ENTHALPY, 57.7)
			.put(V, HIGH_TEMPER_ENTHALPY, 47.90)
			.put(W, HIGH_TEMPER_ENTHALPY, 41.05)
			.put(ZR, HIGH_TEMPER_ENTHALPY, 0d) // TODO: 07.06.17 - in table P3, page 39

			// see table P3, page 39 in methodical book
			.put(AL, THERMAL_CAPACITY, 26.3)
			.put(B, THERMAL_CAPACITY, 39.8)
			.put(C, THERMAL_CAPACITY, 24.9)
			.put(CR, THERMAL_CAPACITY, 0d) // TODO: 07.06.17 - in table P3, page 39
			.put(CU, THERMAL_CAPACITY, 31.4)
			.put(FE, THERMAL_CAPACITY, 41.9)
			.put(HF, THERMAL_CAPACITY, 0d) // TODO: 07.06.17 not presented in table P3, page 39
			.put(MG, THERMAL_CAPACITY, 0d) // TODO: 07.06.17 not presented in table P3, page 39
			.put(MN, THERMAL_CAPACITY, 46.0)
			.put(MO, THERMAL_CAPACITY, 0d) // TODO: 07.06.17 - in table P3, page 39
			.put(NB, THERMAL_CAPACITY, 0d) // TODO: 07.06.17 - in table P3, page 39
			.put(NI, THERMAL_CAPACITY, 38.5)
			.put(RE, THERMAL_CAPACITY, 0d) // TODO: 07.06.17 not presented in table P3, page 39
			.put(SI, THERMAL_CAPACITY, 25.6)
			.put(TA, THERMAL_CAPACITY, 0d) // TODO: 07.06.17 not presented in table P3, page 39
			.put(TI, THERMAL_CAPACITY, 33.5)
			.put(V, THERMAL_CAPACITY, 0d) // TODO: 07.06.17 - in table P3, page 39
			.put(W, THERMAL_CAPACITY, 0d) // TODO: 07.06.17 - in table P3, page 39
			.put(ZR, THERMAL_CAPACITY, 0d) // TODO: 07.06.17 - in table P3, page 39

			// TODO: 07.06.17 in page 15 written this characteristic but not link to table !?
			.put(AL, HIDDEN_HEAT_VAPORIZATION, 296.0)
			.put(B, HIDDEN_HEAT_VAPORIZATION, 0d)
			.put(C, HIDDEN_HEAT_VAPORIZATION, 814.0)
			.put(CR, HIDDEN_HEAT_VAPORIZATION, 0d)
			.put(CU, HIDDEN_HEAT_VAPORIZATION, 0d)
			.put(FE, HIDDEN_HEAT_VAPORIZATION, 418.0)
			.put(HF, HIDDEN_HEAT_VAPORIZATION, 0d)
			.put(MG, HIDDEN_HEAT_VAPORIZATION, 0d)
			.put(MN, HIDDEN_HEAT_VAPORIZATION, 279.0)
			.put(MO, HIDDEN_HEAT_VAPORIZATION, 0d)
			.put(NB, HIDDEN_HEAT_VAPORIZATION, 0d)
			.put(NI, HIDDEN_HEAT_VAPORIZATION, 0d)
			.put(RE, HIDDEN_HEAT_VAPORIZATION, 0d)
			.put(SI, HIDDEN_HEAT_VAPORIZATION, 469.0)
			.put(TA, HIDDEN_HEAT_VAPORIZATION, 0d)
			.put(TI, HIDDEN_HEAT_VAPORIZATION, 473.0)
			.put(V, HIDDEN_HEAT_VAPORIZATION, 0d)
			.put(W, HIDDEN_HEAT_VAPORIZATION, 0d)
			.put(ZR, HIDDEN_HEAT_VAPORIZATION, 0d)

			// see table P1, page 37 in methodical book
			// TODO: 07.06.17 recheck !
			.put(AL, CLAPEYRON_CLAUSIUS_EQUATION_HEAT_OF_VAPORIZATION, 296.0)
			.put(B, CLAPEYRON_CLAUSIUS_EQUATION_HEAT_OF_VAPORIZATION, 0d) // TODO: 07.06.17 not presented in table
			.put(C, CLAPEYRON_CLAUSIUS_EQUATION_HEAT_OF_VAPORIZATION, 714.0)
			.put(CR, CLAPEYRON_CLAUSIUS_EQUATION_HEAT_OF_VAPORIZATION, 340.0)
			.put(CU, CLAPEYRON_CLAUSIUS_EQUATION_HEAT_OF_VAPORIZATION, 302.0)
			.put(FE, CLAPEYRON_CLAUSIUS_EQUATION_HEAT_OF_VAPORIZATION, 352.0)
			.put(HF, CLAPEYRON_CLAUSIUS_EQUATION_HEAT_OF_VAPORIZATION, 0d) // TODO: 07.06.17 not presented in table
			.put(MG, CLAPEYRON_CLAUSIUS_EQUATION_HEAT_OF_VAPORIZATION, 128.5)
			.put(MN, CLAPEYRON_CLAUSIUS_EQUATION_HEAT_OF_VAPORIZATION, 220.0)
			.put(MO, CLAPEYRON_CLAUSIUS_EQUATION_HEAT_OF_VAPORIZATION, 595.0)
			.put(NB, CLAPEYRON_CLAUSIUS_EQUATION_HEAT_OF_VAPORIZATION, 0d) // TODO: 07.06.17 not presented in table
			.put(NI, CLAPEYRON_CLAUSIUS_EQUATION_HEAT_OF_VAPORIZATION, 374.0)
			.put(RE, CLAPEYRON_CLAUSIUS_EQUATION_HEAT_OF_VAPORIZATION, 0d) // TODO: 07.06.17 not presented in table
			.put(SI, CLAPEYRON_CLAUSIUS_EQUATION_HEAT_OF_VAPORIZATION, 473.0)
			.put(TA, CLAPEYRON_CLAUSIUS_EQUATION_HEAT_OF_VAPORIZATION, 0d) // TODO: 07.06.17 not presented in table
			.put(TI, CLAPEYRON_CLAUSIUS_EQUATION_HEAT_OF_VAPORIZATION, 427.0)
			.put(V, CLAPEYRON_CLAUSIUS_EQUATION_HEAT_OF_VAPORIZATION, 459.0)
			.put(W, CLAPEYRON_CLAUSIUS_EQUATION_HEAT_OF_VAPORIZATION, 800.0)
			.put(ZR, CLAPEYRON_CLAUSIUS_EQUATION_HEAT_OF_VAPORIZATION, 583.0)

			// see table P1, page 37 in methodical book
			.put(AL, CLAPEYRON_CLAUSIUS_EQUATION_B_FACTOR, 10.646)
			.put(B, CLAPEYRON_CLAUSIUS_EQUATION_B_FACTOR, 0d)  // TODO: 07.06.17 not presented in table
			.put(C, CLAPEYRON_CLAUSIUS_EQUATION_B_FACTOR, 13.14)
			.put(CR, CLAPEYRON_CLAUSIUS_EQUATION_B_FACTOR, 11.309)
			.put(CU, CLAPEYRON_CLAUSIUS_EQUATION_B_FACTOR, 10.587)
			.put(FE, CLAPEYRON_CLAUSIUS_EQUATION_B_FACTOR, 10.847)
			.put(HF, CLAPEYRON_CLAUSIUS_EQUATION_B_FACTOR, 0d)  // TODO: 07.06.17 not presented in table
			.put(MG, CLAPEYRON_CLAUSIUS_EQUATION_B_FACTOR, 9.841)
			.put(MN, CLAPEYRON_CLAUSIUS_EQUATION_B_FACTOR, 9.98)
			.put(MO, CLAPEYRON_CLAUSIUS_EQUATION_B_FACTOR, 11.092)
			.put(NB, CLAPEYRON_CLAUSIUS_EQUATION_B_FACTOR, 0d)  // TODO: 07.06.17 not presented in table
			.put(NI, CLAPEYRON_CLAUSIUS_EQUATION_B_FACTOR, 11.174)
			.put(RE, CLAPEYRON_CLAUSIUS_EQUATION_B_FACTOR, 0d)  // TODO: 07.06.17 not presented in table
			.put(SI, CLAPEYRON_CLAUSIUS_EQUATION_B_FACTOR, 12.681)
			.put(TA, CLAPEYRON_CLAUSIUS_EQUATION_B_FACTOR, 0d)  // TODO: 07.06.17 not presented in table
			.put(TI, CLAPEYRON_CLAUSIUS_EQUATION_B_FACTOR, 11.363)
			.put(V, CLAPEYRON_CLAUSIUS_EQUATION_B_FACTOR, 11.615)
			.put(W, CLAPEYRON_CLAUSIUS_EQUATION_B_FACTOR, 12.204)
			.put(ZR, CLAPEYRON_CLAUSIUS_EQUATION_B_FACTOR, 11.475)

			// see table P5, page 41 in methodical book
			.put(AL, A_GAMMA_FACTOR, 2251.0)
			.put(B, A_GAMMA_FACTOR, 0d)  // TODO: 07.06.17 not presented in table
			.put(C, A_GAMMA_FACTOR, 0d) // TODO: 07.06.17 - in table
			.put(CR, A_GAMMA_FACTOR, 0d)  // TODO: 07.06.17 not presented in table
			.put(CU, A_GAMMA_FACTOR, -1748.0)
			.put(FE, A_GAMMA_FACTOR, 0d)  // TODO: 07.06.17 not presented in table
			.put(HF, A_GAMMA_FACTOR, 0d)  // TODO: 07.06.17 not presented in table
			.put(MG, A_GAMMA_FACTOR, 0d)  // TODO: 07.06.17 not presented in table
			.put(MN, A_GAMMA_FACTOR, 0d)  // TODO: 07.06.17 not presented in table
			.put(MO, A_GAMMA_FACTOR, 0d)  // TODO: 07.06.17 not presented in table
			.put(NB, A_GAMMA_FACTOR, 0d)  // TODO: 07.06.17 not presented in table
			.put(NI, A_GAMMA_FACTOR, 1093.0)
			.put(RE, A_GAMMA_FACTOR, 0d)  // TODO: 07.06.17 not presented in table
			.put(SI, A_GAMMA_FACTOR, 6230.0)
			.put(TA, A_GAMMA_FACTOR, 0d)  // TODO: 07.06.17 not presented in table
			.put(TI, A_GAMMA_FACTOR, 3667.0)
			.put(V, A_GAMMA_FACTOR, 3667.0)
			.put(W, A_GAMMA_FACTOR, 0d)  // TODO: 07.06.17 not presented in table
			.put(ZR, A_GAMMA_FACTOR, 1725.0)

			// see table P5, page 41 in methodical book
			.put(AL, B_GAMMA_FACTOR, -0.00109)
			.put(B, A_GAMMA_FACTOR, 0d)  // TODO: 07.06.17 not presented in table
			.put(C, B_GAMMA_FACTOR, 0d) // TODO: 07.06.17 - in table
			.put(CR, A_GAMMA_FACTOR, 0d)  // TODO: 07.06.17 not presented in table
			.put(CU, A_GAMMA_FACTOR, 0.00155)
			.put(FE, B_GAMMA_FACTOR, 0d)  // TODO: 07.06.17 not presented in table
			.put(HF, A_GAMMA_FACTOR, 0d)  // TODO: 07.06.17 not presented in table
			.put(MG, A_GAMMA_FACTOR, 0d)  // TODO: 07.06.17 not presented in table
			.put(MN, B_GAMMA_FACTOR, 0d)  // TODO: 07.06.17 not presented in table
			.put(MO, A_GAMMA_FACTOR, 0d)  // TODO: 07.06.17 not presented in table
			.put(NB, A_GAMMA_FACTOR, 0d)  // TODO: 07.06.17 not presented in table
			.put(NI, A_GAMMA_FACTOR, 0.4)
			.put(RE, A_GAMMA_FACTOR, 0d)  // TODO: 07.06.17 not presented in table
			.put(SI, B_GAMMA_FACTOR, 0.376)
			.put(TA, A_GAMMA_FACTOR, 0d)  // TODO: 07.06.17 not presented in table
			.put(TI, B_GAMMA_FACTOR, 0.015)
			.put(V, A_GAMMA_FACTOR, 0.0) // TODO: 07.06.17 in methodical book indicated 0.00000 !?
			.put(W, A_GAMMA_FACTOR, 0d)  // TODO: 07.06.17 not presented in table
			.put(ZR, A_GAMMA_FACTOR, -0.005)

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
	void setMoleFractionEachElemInVapor(double val);
	double getMoleFractionEachElemInVapor();

	// 7. Весовая доля каждого компонента в паре (%)
	// weightFractionEachElemInVapor
	void setWeightFractionEachElemInVapor(double val);
	double getWeightFractionEachElemInVapor();

	// 8. Скорость испарения из сварочной ванны каждого элемента (гр/сек)
	// rateVaporizationEachElemOfWeldPool
	void setRateVaporizationEachElemOfWeldPool(double val);
	double getRateVaporizationEachElemOfWeldPool();

}
