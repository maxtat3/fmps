package stage1;

import stage1.elements.GeneralElementStage1;

import java.util.List;

// TODO: 07.06.17 may be pass all values needed to calculation in class constructor ?
/**
 * Расчетные формулы для задачи №1
 */
public class Formulas {

	/**
	 * Расчет мольной доли всех элементов сплава.
	 * Результат записывается в каждый элемент в переданной коллекции.
	 * Результат представлен в долях вещества [0 ... 1) . Для получения результат в % нужно умножить на 100.
	 * Формула 1.
	 *
	 * @param userElements список элементов из задания пользователя
	 */
	public void findMoleFractionOfAlloyElemsF1(List<GeneralElementStage1> userElements) {
		double gi; // массовая доля вещества
		double aiFraction; // атомная доля элемента
		double[] relGiToAi = new double[userElements.size()]; // отношения массовой доли вещества (%) к атомной массе элемента (кг/моль)
		int dElemPointer = 0;

		for (GeneralElementStage1 el : userElements) {
			gi = el.getAlloyCompWeight();
			aiFraction = GeneralElementStage1.CONST_ELEMS.get(el.toString(), GeneralElementStage1.ATOMIC_FRACTION);
			relGiToAi[dElemPointer] = gi / aiFraction;
			dElemPointer++;
		}

		// reset pointer for get saved order elements again.
		dElemPointer = 0;

		// find sum
		double relGiAiSum = 0;
		for (double val : relGiToAi) {
			relGiAiSum += val;
		}

		for (GeneralElementStage1 el : userElements) {
			el.setMoleFractionAlloyElem(relGiToAi[dElemPointer]/relGiAiSum);
			dElemPointer++;
		}
	}

	/**
	 * Расчет энтальпии жидкого сплава (КДж/Моль).
	 * Формула 2.1
	 *
	 * Formula dependencies:
	 *      - {@link #findMoleFractionOfAlloyElemsF1(List)} - formula 1
	 * @param userElements список элементов из задания пользователя
	 * @param temperatureTask // TODO: 19.05.17 уточнить что за температура ?
	 * @param temperatureElements // TODO: 19.05.17 уточнить что за температура ?
	 * @return энтальпия жидкого сплава в КДж/Моль
	 */
	public double findEnthalpyLiquidAlloyF2p1(List<GeneralElementStage1> userElements, int temperatureTask, int temperatureElements) {
		findMoleFractionOfAlloyElemsF1(userElements);

		// HT0-H2980 - temp values 1 high temperature component enthalpy of each element
		double[] ht0minh2980 = new double[userElements.size()];

		// (HT0-H2980)*Ni - temp values 2 enthalpy of vaporization
		double[] ht0minh2980mulni = new double[userElements.size()];

		int elemPointer = 0;

		double highTemprEnthalpy;
		double thermalCapacity;
		double deltaTempr;
		double moleFractionAlloyElem;

		for (GeneralElementStage1 el : userElements) {
			highTemprEnthalpy = GeneralElementStage1.CONST_ELEMS.get(el.toString(), GeneralElementStage1.HIGH_TEMPER_ENTHALPY);
			thermalCapacity = GeneralElementStage1.CONST_ELEMS.get(el.toString(), GeneralElementStage1.THERMAL_CAPACITY);
			deltaTempr = temperatureTask - temperatureElements;
			ht0minh2980[elemPointer] = (highTemprEnthalpy * 1000 + thermalCapacity * deltaTempr) / 1000;

			moleFractionAlloyElem = el.getMoleFractionAlloyElem();
			ht0minh2980mulni[elemPointer] = ht0minh2980[elemPointer] * moleFractionAlloyElem;

			elemPointer++;
		}

		double enthalpyLiquidAlloySum = 0;
		for (double val : ht0minh2980mulni) {
			enthalpyLiquidAlloySum += val;
		}
		return enthalpyLiquidAlloySum;
	}

	/**
	 * Расчет энтальпии испарения (КДж/Моль).
	 * Формула 2.2
	 *
	 * Formula dependencies:
	 *      - {@link #findMoleFractionOfAlloyElemsF1(List)} - formula 1
	 * @param userElements список элементов из задания пользователя
	 * @return значение энтальпии испарения в КДж/Моль
	 */
	public double findEnthalpyVaporizationF2p2(List<GeneralElementStage1> userElements){
		new Formulas().findMoleFractionOfAlloyElemsF1(userElements);

		// temp value deHкип0*Ni for find enthalpy of vaporization
		double[] deHboil0mulNi = new double[userElements.size()];
		int elemPointer = 0;
		double moleFractionAlloyElem;
		double hiddenHeatVaporization;

		for (GeneralElementStage1 el : userElements) {
			moleFractionAlloyElem = el.getMoleFractionAlloyElem();
			hiddenHeatVaporization = GeneralElementStage1.CONST_ELEMS.get(el.toString(), GeneralElementStage1.HIDDEN_HEAT_VAPORIZATION);

			deHboil0mulNi[elemPointer] = moleFractionAlloyElem * hiddenHeatVaporization;
			elemPointer++;
		}

		double enthalpyVaporizationSum = 0;
		for (double val : deHboil0mulNi) {
			enthalpyVaporizationSum += val;
		}
		return enthalpyVaporizationSum;
	}

	/**
	 * Энтальпия пара для сплава (КДж/Моль).
	 * Формула 2.3
	 * Formula dependencies:
	 *      - {@link #findEnthalpyLiquidAlloyF2p1(List, int, int)} - formula 2.1
	 *      - {@link #findEnthalpyVaporizationF2p2(List)} - formula 2.2
	 * @param userElements список элементов из задания пользователя
	 * @param temperatureTask
	 * @param temperatureElements
	 * @return энтальпия пара для сплава
	 */
	public double findEnthalpyVaporF2p3(List<GeneralElementStage1> userElements, int temperatureTask, int temperatureElements){
		double enthalpyLiquidAlloy = findEnthalpyLiquidAlloyF2p1(userElements, temperatureTask, temperatureElements);
		double enthalpyVaporization = findEnthalpyVaporizationF2p2(userElements);
		return enthalpyLiquidAlloy + enthalpyVaporization;
	}

	/**
	 * Давление пара чистых компонентов (Па).
	 * Высесленные значения записываются по ссылке в каждый элемент.
	 * Формула 3
	 */
	public void findVaporPressureOfPureCompsF3(List<GeneralElementStage1> userElements, int temperatureTask){
		double divider = 19.15; // общий делитель, коэффициент в формуле нахождения lgPi
		double lgPi[] = new double[userElements.size()];
		double pi[] = new double[userElements.size()];
		int elemPointer = 0;

		for (GeneralElementStage1 el : userElements) {
			double heatOfVaporization = GeneralElementStage1.CONST_ELEMS.get(
				el.toString(), GeneralElementStage1.CLAPEYRON_CLAUSIUS_EQUATION_HEAT_OF_VAPORIZATION
			);
			double bfactor = GeneralElementStage1.CONST_ELEMS.get(
				el.toString(), GeneralElementStage1.CLAPEYRON_CLAUSIUS_EQUATION_B_FACTOR
			);

			lgPi[elemPointer] = -((heatOfVaporization * 1000)/(divider * temperatureTask)) + bfactor;
			pi[elemPointer] = Math.pow(10, lgPi[elemPointer]);

			el.setLgVaporPressureOfPureComps(lgPi[elemPointer]);
			el.setVaporPressureOfPureComps(pi[elemPointer]);
			elemPointer++;
		}
	}

	/**
	 * Парциальное давление компонент над сплавом (Па)
	 * Формула 4
	 * Также этот метод вычисляет значение десятичного
	 * логарифма {@link stage1.elements.BaseElementStage1#lgPartialPressureCompsOverAlloy} для построения графика.
	 *
	 * Formula dependencies:
	 *      - {@link #findMoleFractionOfAlloyElemsF1(List)} - formula 1
	 *      - {@link #findVaporPressureOfPureCompsF3(List, int)} - formula 3
	 * @param userElements список элементов из задания пользователя
	 * @param temperatureTask температура расчета (град. Цельсия)
	 */
	public void findPartialPressureCompsOverAlloyF4(List<GeneralElementStage1> userElements, int temperatureTask){
		findMoleFractionOfAlloyElemsF1(userElements);
		findVaporPressureOfPureCompsF3(userElements, temperatureTask);

		double aGamma, bGamma, ni, pi;
		double lgGamma[] = new double[userElements.size()];
		double gamma[] = new double[userElements.size()];
		double pip[] = new double[userElements.size()];
		int elemPointer = 0;

		for (GeneralElementStage1 el : userElements) {
			aGamma = GeneralElementStage1.CONST_ELEMS.get(el.toString(), GeneralElementStage1.A_GAMMA_FACTOR);
			bGamma = GeneralElementStage1.CONST_ELEMS.get(el.toString(), GeneralElementStage1.B_GAMMA_FACTOR);
			ni = el.getMoleFractionAlloyElem(); // formula 1
			pi = el.getVaporPressureOfPureComps(); // formula 3

			lgGamma[elemPointer] = -(aGamma / temperatureTask) + bGamma;
			gamma[elemPointer] = Math.pow(10, lgGamma[elemPointer]);

			pip[elemPointer] = gamma[elemPointer] * ni * pi;
			el.setPartialPressureCompsOverAlloy(pip[elemPointer]);
			el.setLgPartialPressureCompsOverAlloy(Math.log10(pip[elemPointer]));
			elemPointer++;
		}
	}

	/**
	 * Давление пара над сплавом (Па)
	 * Формула 5
	 *
	 * Formula dependencies:
	 *      - {@link #findPartialPressureCompsOverAlloyF4(List, int)} - formula 4
	 * @param userElements список элементов из задания пользователя
	 * @return давление пара над сплавом в Па
	 */
	public double findVaporPressureOverAlloyF5(List<GeneralElementStage1> userElements, int temperatureTask){
		findPartialPressureCompsOverAlloyF4(userElements, temperatureTask);

		double vaporPressureOverAlloy = 0;
		for (GeneralElementStage1 el : userElements) {
			vaporPressureOverAlloy += el.getPartialPressureCompsOverAlloy();
		}
		return vaporPressureOverAlloy;
	}

	/**
	 * Мольная доля каждого компонента в паре (доли)
	 * Формула 6
	 *
	 * Formula dependencies:
	 *      - {@link #findVaporPressureOverAlloyF5(List, int)} - formula 5
	 * @param userElements список элементов из задания пользователя
	 */
	public void findMoleFractionEachElemInVaporF6(List<GeneralElementStage1> userElements, int temperatureTask){
		double vaporPressureOverAlloy = findVaporPressureOverAlloyF5(userElements, temperatureTask);

		for (GeneralElementStage1 el : userElements) {
			el.setMoleFractionEachElemInVapor(el.getPartialPressureCompsOverAlloy() / vaporPressureOverAlloy);
		}
	}

	/**
	 * Весовая доля каждого компонента в паре (%)
	 * Формула 7
	 *
	 * Formula dependencies:
	 *      - {@link #findVaporPressureOverAlloyF5(List, int)} - formula 6
	 * @param userElements список элементов из задания пользователя
	 */
	public void findWeightFractionEachElemInVaporF7(List<GeneralElementStage1> userElements, int temperatureTask){
		findMoleFractionEachElemInVaporF6(userElements, temperatureTask);

		double nvi, ai, nviMulAiSum = 0;

		for (GeneralElementStage1 el : userElements) {
			nvi = el.getMoleFractionEachElemInVapor();
			ai = GeneralElementStage1.CONST_ELEMS.get(el.toString(), GeneralElementStage1.ATOMIC_FRACTION);
			nviMulAiSum += nvi * ai;
		}

		for (GeneralElementStage1 el : userElements) {
			nvi = el.getMoleFractionEachElemInVapor();
			ai = GeneralElementStage1.CONST_ELEMS.get(el.toString(), GeneralElementStage1.ATOMIC_FRACTION);
			el.setWeightFractionEachElemInVapor(((nvi * ai) / nviMulAiSum) * 100);
		}
	}

	/**
	 * Скорость испарения из сварочной ванны каждого элемента (гр/сек)
	 * Формула 8
	 *
	 * Formula dependencies:
	 *      - {@link #findPartialPressureCompsOverAlloyF4(List, int)} - formula 4
	 * @param userElements список элементов из задания пользователя
	 * @param temperatureTask температура расчета (град. Цельсия)
	 * @param surfaceWeldArea площадь свободной поверхности сварочной ванны (см2)
	 */
	public void findRateVaporizationEachElemOfWeldPoolF8(List<GeneralElementStage1> userElements,
	                                                     int temperatureTask, double surfaceWeldArea) {
		findPartialPressureCompsOverAlloyF4(userElements, temperatureTask);

		double pip, ai, vi;

		for (GeneralElementStage1 el : userElements) {
			pip = el.getPartialPressureCompsOverAlloy();
			ai = GeneralElementStage1.CONST_ELEMS.get(el.toString(), GeneralElementStage1.ATOMIC_FRACTION);
			vi = 0.00044 * pip * Math.sqrt(ai / temperatureTask) * surfaceWeldArea;
			el.setRateVaporizationEachElemOfWeldPool(vi);
		}
	}

	/**
	 * Уменьшение массы расплавленного металла за счет испарения (гр/сек)
	 * Формула 9
	 *
	 * Formula dependencies:
	 *      - {@link #findRateVaporizationEachElemOfWeldPoolF8(List, int, double)} - formula 8
	 * @param userElements список элементов из задания пользователя
	 * @return значение массы расплавленного металла за счет испарения
	 */
	public double findDecreaseMoltenMetalDueVaporizationF9(List<GeneralElementStage1> userElements,
	                                                       int temperatureTask, double surfaceWeldArea){
		findRateVaporizationEachElemOfWeldPoolF8(userElements, temperatureTask, surfaceWeldArea);
		double sum = 0;
		for (GeneralElementStage1 el : userElements) {
			sum += el.getRateVaporizationEachElemOfWeldPool();
		}
		return sum;
	}
}
