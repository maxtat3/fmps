package stage1.elements;

import com.google.common.collect.ImmutableTable;
import model.Container;

public interface GeneralElementStage1 {

	double TEMPERATURE_EL = 1800; // Celsius degrees
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


	// 3. Давление пара чистых компонентов (Па)
	// vaporPressureOfPureComps


	// 4. Парциальное давление компонент над сплавом (Па)
	// partialPressureCompsOverAlloy
	//

	// 5. Давление пара над сплавом (Па)
	// vaporPressureOverAlloy
	//

	// 6. Мольная доля каждого компонента в паре (%)
	// moleFractionEachElemInVapor

	// 7. Весовая доля каждого компонента в паре (%)
	// weightFractionEachElemInVapor

	// 8. Скорость испарения из сварочной ванны каждого элемента (гр/сек)
	// rateVaporizationEachElemOfWeldPool

	// 9. Скорость уменьшения массы расплавленного металла за счет испарения (гр/сек)
	// rateDecreaseMoltenMetalDueVaporization
}
