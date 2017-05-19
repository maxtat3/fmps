package stage1.calc;

import model.Container;
import stage1.elements.GeneralElementStage1;

import java.util.List;

/**
 * Расчеты для задачи №1
 */
public class Calc {

	public Calc() {



		// do calculate
//		findEnthalpyVaporization(list);
//		findEnthalpyVapor();
//		findVaporPressureOfPureComps(list, TEMPERATURE_TASK);
//		findPartialPressureCompsOverAlloy(list, TEMPERATURE_TASK);
//		findVaporPressureOverAlloy(list);
//		findMoleFractionEachElemInVapor(list);
//		findWeightFractionEachElemInVapor(list);
//		findRateVaporizationEachElemOfWeldPool(
//			list, TEMPERATURE_TASK, Container.getInstance().getStage1().getExtraInputDataStage1().getSurfaceWeldArea()
//		);
//		findDecreaseMoltenMetalDueVaporization(list);
	}

	/**
	 * Расчет мольной доли всех элементов сплава.
	 * Результат записывается в каждый элемент в переданной коллекции.
	 * Результат представлен в долях вещества [0 ... 1) . Для получения результат в % нужно умножить на 100.
	 * Формула 1.
	 *
	 * @param userElements список элементов из задания пользователя
	 */
	public void findMoleFractionOfAlloyElems(List<GeneralElementStage1> userElements) {
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
	 * @param userElements список элементов из задания пользователя
	 * @param temperatureTask // TODO: 19.05.17 уточнить что за температура ?
	 * @param temperatureElements // TODO: 19.05.17 уточнить что за температура ?
	 * @return энтальпия жидкого сплава в КДж/Моль
	 */
	public double findEnthalpyLiquidAlloy(List<GeneralElementStage1> userElements, int temperatureTask, int temperatureElements) {
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
//		Container.getInstance().getStage1().getCalcDataStage1().setEnthalpyLiquidAlloy(enthalpyLiquidAlloySum);
		return enthalpyLiquidAlloySum;
	}

	/**
	 * Расчет энтальпии испарения (КДж/Моль).
	 * Формула 2.2
	 *
	 * @param userElements список элементов из задания пользователя
	 */
	public double findEnthalpyVaporization(List<GeneralElementStage1> userElements){
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
	 */
	public void findEnthalpyVapor(){
		double enthalpyLiquidAlloy = Container.getInstance().getStage1().getCalcDataStage1().getEnthalpyLiquidAlloy();
		double enthalpyVaporization = Container.getInstance().getStage1().getCalcDataStage1().getEnthalpyVaporization();
		double enthalpyVapor = enthalpyLiquidAlloy + enthalpyVaporization;
		Container.getInstance().getStage1().getCalcDataStage1().setEnthalpyVapor(enthalpyVapor);
		System.out.println("enthalpyVapor = " + enthalpyVapor);
	}

	/**
	 * Давление пара чистых компонентов (Па)
	 * Формула 3
	 */
	public void findVaporPressureOfPureComps(List<GeneralElementStage1> userElements, int temperatureTask){
		double dividerConst = 19.15; // общий делитель
		double lgPi[] = new double[userElements.size()];
		double pi[] = new double[userElements.size()];
		int elemPointer = 0;

		for (GeneralElementStage1 userElem : userElements) {
			for (GeneralElementStage1 containerElem : Container.getInstance().getStage1().getAllElements()) {
				if (userElem.toString().equals(containerElem.toString())) {
					double heatOfVaporization = GeneralElementStage1.CONST_ELEMS.get(containerElem.toString(), GeneralElementStage1.CLAPEYRON_CLAUSIUS_EQUATION_HEAT_OF_VAPORIZATION);
					double bfactor = GeneralElementStage1.CONST_ELEMS.get(containerElem.toString(), GeneralElementStage1.CLAPEYRON_CLAUSIUS_EQUATION_B_FACTOR);

					lgPi[elemPointer] = -((heatOfVaporization * 1000)/(dividerConst * temperatureTask)) + bfactor;
					pi[elemPointer] = Math.pow(10, lgPi[elemPointer]);

					containerElem.setVaporPressureOfPureComps(pi[elemPointer]);
					elemPointer++;
				}
			}
		}

		for (double v : pi) {
			System.out.println("pi = " + v);
		}
	}

	/**
	 * Парциальное давление компонент над сплавом (Па)
	 * Формула 4
	 *
	 * @param userElements список элементов из задания пользователя
	 * @param temperatureTask температура расчета (град. Цельсия)
	 */
	public void findPartialPressureCompsOverAlloy(List<GeneralElementStage1> userElements, int temperatureTask){
		double aGamma, bGamma, ni, pi;
		double lgGamma[] = new double[userElements.size()];
		double gamma[] = new double[userElements.size()];
		double pip[] = new double[userElements.size()];
		int elemPointer = 0;

		for (GeneralElementStage1 userElem : userElements) {
			for (GeneralElementStage1 containerElem : Container.getInstance().getStage1().getAllElements()) {
				if (userElem.toString().equals(containerElem.toString())) {
					aGamma = GeneralElementStage1.CONST_ELEMS.get(containerElem.toString(), GeneralElementStage1.A_GAMMA_FACTOR);
					bGamma = GeneralElementStage1.CONST_ELEMS.get(containerElem.toString(), GeneralElementStage1.B_GAMMA_FACTOR);
					ni = containerElem.getMoleFractionAlloyElem(); // formula 1
					pi = containerElem.getVaporPressureOfPureComps(); // formula 3

					lgGamma[elemPointer] = -(aGamma / temperatureTask) + bGamma;
					gamma[elemPointer] = Math.pow(10, lgGamma[elemPointer]);

					pip[elemPointer] = gamma[elemPointer] * ni * pi;
					containerElem.setPartialPressureCompsOverAlloy(pip[elemPointer]);
					elemPointer++;
				}
			}
		}

		for (double val : pip) {
			System.out.println("Pip = " + val);
		}
	}

	/**
	 * Давление пара над сплавом (Па)
	 * Формула 5
	 *
	 * @param userElements список элементов из задания пользователя
	 */
	public void findVaporPressureOverAlloy(List<GeneralElementStage1> userElements){
		double vaporPressureOverAlloy = 0;

		for (GeneralElementStage1 userElem : userElements) {
			for (GeneralElementStage1 containerElem : Container.getInstance().getStage1().getAllElements()) {
				if (userElem.toString().equals(containerElem.toString())) {
					vaporPressureOverAlloy += containerElem.getPartialPressureCompsOverAlloy();
				}
			}
		}

		Container.getInstance().getStage1().getCalcDataStage1().setVaporPressureOverAlloy(vaporPressureOverAlloy);
		System.out.println("vaporPressureOverAlloy = " + vaporPressureOverAlloy);
	}

	/**
	 * Мольная доля каждого компонента в паре (%)
	 * Формула 6
	 *
	 * @param userElements список элементов из задания пользователя
	 */
	public void findMoleFractionEachElemInVapor(List<GeneralElementStage1> userElements){
		double vaporPressureOverAlloy = Container.getInstance().getStage1().getCalcDataStage1().getVaporPressureOverAlloy();
		double partialPressureCompsOverAlloy;
		double moleFractionEachElemInVapor; // find value

		for (GeneralElementStage1 userElem : userElements) {
			for (GeneralElementStage1 containerElem : Container.getInstance().getStage1().getAllElements()) {
				if (userElem.toString().equals(containerElem.toString())) {
					partialPressureCompsOverAlloy = containerElem.getPartialPressureCompsOverAlloy();
					moleFractionEachElemInVapor = (partialPressureCompsOverAlloy / vaporPressureOverAlloy) * 100;
					containerElem.setMoleFractionEachElemInVapor(moleFractionEachElemInVapor);
				}
			}
		}

		for (GeneralElementStage1 elementStage1 : Container.getInstance().getStage1().getAllElements()) {
			System.out.println(elementStage1.toString() + " : " + "NviPercent = " + elementStage1.getMoleFractionEachElemInVapor());
		}
	}

	/**
	 * Весовая доля каждого компонента в паре (%)
	 * Формула 7
	 *
	 * @param userElements список элементов из задания пользователя
	 */
	public void findWeightFractionEachElemInVapor(List<GeneralElementStage1> userElements){
		double nvi, ai, nviMulaiSum = 0;

		for (GeneralElementStage1 userElem : userElements) {
			for (GeneralElementStage1 containerElem : Container.getInstance().getStage1().getAllElements()) {
				if (userElem.toString().equals(containerElem.toString())) {
					nvi = containerElem.getMoleFractionEachElemInVapor();
					ai = GeneralElementStage1.CONST_ELEMS.get(containerElem.toString(), GeneralElementStage1.ATOMIC_FRACTION);
					nviMulaiSum += nvi * ai;
				}
			}
		}

		for (GeneralElementStage1 userElem : userElements) {
			for (GeneralElementStage1 containerElem : Container.getInstance().getStage1().getAllElements()) {
				if (userElem.toString().equals(containerElem.toString())) {
					nvi = containerElem.getMoleFractionEachElemInVapor();
					ai = GeneralElementStage1.CONST_ELEMS.get(containerElem.toString(), GeneralElementStage1.ATOMIC_FRACTION);
					containerElem.setWeightFractionEachElemInVapor(((nvi * ai) / nviMulaiSum) * 100);
				}
			}
		}

		for (GeneralElementStage1 elementStage1 : Container.getInstance().getStage1().getAllElements()) {
			System.out.println(elementStage1.toString() + " : " + "gi = " + elementStage1.getWeightFractionEachElemInVapor());
		}
	}

	/**
	 * Скорость испарения из сварочной ванны каждого элемента (гр/сек)
	 * Формула 8
	 *
	 * @param userElements список элементов из задания пользователя
	 */
	public void findRateVaporizationEachElemOfWeldPool(List<GeneralElementStage1> userElements,
	                                                   int temperatureTask, double surfaceWeldArea) {
		double pip, ai, vi;

		for (GeneralElementStage1 userElem : userElements) {
			for (GeneralElementStage1 containerElem : Container.getInstance().getStage1().getAllElements()) {
				if (userElem.toString().equals(containerElem.toString())) {
					pip = containerElem.getPartialPressureCompsOverAlloy();
					ai = GeneralElementStage1.CONST_ELEMS.get(containerElem.toString(), GeneralElementStage1.ATOMIC_FRACTION);
					vi = 0.00044 * pip * Math.sqrt(ai / temperatureTask) * surfaceWeldArea;
					containerElem.setRateVaporizationEachElemOfWeldPool(vi);
				}
			}
		}

		for (GeneralElementStage1 el : Container.getInstance().getStage1().getAllElements()) {
			System.out.println(el.toString() + " : " + "vi = " + el.getRateVaporizationEachElemOfWeldPool());
		}
	}

	/**
	 * Уменьшение массы расплавленного металла за счет испарения (гр/сек)
	 * Формула 9
	 *
	 * @param userElements список элементов из задания пользователя
	 */
	public void findDecreaseMoltenMetalDueVaporization(List<GeneralElementStage1> userElements){
		double sum = 0;

		for (GeneralElementStage1 userElem : userElements) {
			for (GeneralElementStage1 containerElem : Container.getInstance().getStage1().getAllElements()) {
				if (userElem.toString().equals(containerElem.toString())) {
					sum += containerElem.getRateVaporizationEachElemOfWeldPool();
				}
			}
		}

		Container.getInstance().getStage1().getCalcDataStage1().setDecreaseMoltenMetalDueVaporization(sum);
		System.out.println("deltaVm = " + sum);
	}

}
