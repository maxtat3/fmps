package app;

import chart.ChartData;
import controller.InputDataController;
import db.DBUtils;
import domain.User;
import model.Container;
import stage1.CommonCalculatedDataStage1;
import stage1.ExtraInputDataStage1;
import stage1.calc.Calc;
import stage1.elements.BaseElementStage1;
import stage1.elements.GeneralElementStage1;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 *
 */
public class ReferenceCalculationsStage1 {

	public static final int TEMPERATURE_ELEMENTS = 1800; // TODO: 07.06.17 правильно ли указана эта температуа элементов ???

	/**
	 * Minimum temperature at build chart.
	 */
	private static final int MIN_TEMPERATURE = 1500;

	/**
	 * Maximum temperature at build chart.
	 */
	private static final int MAX_TEMPERATURE = 3000;

	/**
	 * Delta of temperature at build chart.
	 */
	private static final int DELTA_TEMPERATURE = 100;

	/**
	 * Minimum time at build chart 5.
	 */
	private static final double MIN_TIME = 0;

	/**
	 * Maximum time at build chart 5.
	 */
	private static final double MAX_TIME = 20;

	/**
	 * Delta of time at build chart 5.
	 */
	private static final double DELTA_TIME = 1.0;


	/**
	 * Perform reference data - calculations of all formulas for stage 1.
	 * This results data applied for compare of user answers.
	 *
	 * @param user user for whom the calculations will be performed
	 */
	public void performRefCalcStage1(User user){
		Container.Stage1 elements = DBUtils.getMainMsrDataStage1(user.getId());
		ExtraInputDataStage1 extra = DBUtils.getExtraMsrDataStage1(user.getId());

		Container.getInstance().setStage1(elements);
		Container.getInstance().getStage1().setExtraInputData(extra);

		CommonCalculatedDataStage1 commonData = Container.getInstance().getStage1().getCommonCalculatedData();


		List<GeneralElementStage1> userTaskElements = new InputDataController().receiveUserTaskElementsStage1(InputDataController.AccessElementsStage1.TASK);

		int pEnv = Container.getInstance().getStage1().getExtraInputData().getPressureEnv();
		double surfaceWeldArea = Container.getInstance().getStage1().getExtraInputData().getSurfaceWeldArea();
		double weightMoltenMetal = Container.getInstance().getStage1().getExtraInputData().getWeightMoltenMetal();
		int temperature = Container.getInstance().getStage1().getExtraInputData().getTemperature();
		double time = Container.getInstance().getStage1().getExtraInputData().getTime();


		Calc calc = new Calc();

		// f1
		calc.findMoleFractionOfAlloyElemsF1(userTaskElements);

		// f2.1, f2.2, f2.3
		double f2p1Res = calc.findEnthalpyLiquidAlloyF2p1(userTaskElements, temperature, TEMPERATURE_ELEMENTS);
		double f2p2Res = calc.findEnthalpyVaporizationF2p2(userTaskElements);
		double f2p3Res = calc.findEnthalpyVaporF2p3(userTaskElements, temperature, TEMPERATURE_ELEMENTS);
		commonData.setEnthalpyLiquidAlloyF2p1(f2p1Res);
		commonData.setEnthalpyVaporizationF2p2(f2p2Res);
		commonData.setEnthalpyVaporF2p3(f2p3Res);

		// f3, f4
		calc.findVaporPressureOfPureCompsF3(userTaskElements, temperature);
		calc.findPartialPressureCompsOverAlloyF4(userTaskElements, temperature);

		// f5
		double f5Res = calc.findVaporPressureOverAlloyF5(userTaskElements, temperature);
		commonData.setVaporPressureOverAlloyF5(f5Res);

		// f6, f7, f8
		calc.findMoleFractionEachElemInVaporF6(userTaskElements, temperature);
		calc.findWeightFractionEachElemInVaporF7(userTaskElements, temperature);
		calc.findRateVaporizationEachElemOfWeldPoolF8(userTaskElements, temperature, surfaceWeldArea);

		// f9
		double f9Res = calc.findDecreaseMoltenMetalDueVaporizationF9(userTaskElements, temperature, surfaceWeldArea);
		commonData.setDecreaseMoltenMetalDueVaporizationF9(f9Res);
	}

	/**
	 * Получение данных для графика - Зависимость паров чистых компонент от температуры Т
	 * Вычисляется согласно формуле 3 {@link Calc#findVaporPressureOfPureCompsF3(List, int)}
	 */
	public ChartData buildChartsXTemperYValues(){
		// TODO: 13.06.17 may be move #receiveUserTaskElementsStage1 method to Container !?
		List<GeneralElementStage1> elems = new InputDataController()
			.receiveUserTaskElementsStage1(InputDataController.AccessElementsStage1.TASK);

		captureData(elems);
		Calc calc = new Calc();
		Map<GeneralElementStage1, LinkedHashMap<Integer, Double>> chart1Formula3Data = new LinkedHashMap<>(); // formula 3
		Map<GeneralElementStage1, LinkedHashMap<Integer, Double>> chart2Formula4Data = new LinkedHashMap<>(); // formula 4
		Map<GeneralElementStage1, LinkedHashMap<Integer, Double>> chart3Formula7Data = new LinkedHashMap<>(); // formula 7
		for (GeneralElementStage1 el : elems) {
			chart1Formula3Data.put(el, new LinkedHashMap<Integer, Double>());
			chart2Formula4Data.put(el, new LinkedHashMap<Integer, Double>());
			chart3Formula7Data.put(el, new LinkedHashMap<Integer, Double>());
		}
		for (int temp = MIN_TEMPERATURE; temp < MAX_TEMPERATURE; temp += DELTA_TEMPERATURE) {
			calc.findVaporPressureOfPureCompsF3(elems, temp);
			calc.findPartialPressureCompsOverAlloyF4(elems, temp);
			calc.findWeightFractionEachElemInVaporF7(elems, temp);
			for (GeneralElementStage1 el : elems) {
				double pCh3 = el.getLgVaporPressureOfPureComps();
				double pCh4 = el.getLgPartialPressureCompsOverAlloy();
				double pCh7 = el.getWeightFractionEachElemInVapor();
				if (chart1Formula3Data.containsKey(el)) {
					chart1Formula3Data.get(el).put(temp, pCh3);
					chart2Formula4Data.get(el).put(temp, pCh4);
					chart3Formula7Data.get(el).put(temp, pCh7);
				}
			}
		}
		ChartData data = new ChartData();
		data.setChart1Formula3Data(chart1Formula3Data);
		data.setChart2Formula4Data(chart2Formula4Data);
		data.setChart3Formula7Data(chart3Formula7Data);
		restoreData(elems);

		return data;
	}

	public ChartData buildChart4XTemperYValue() {
		List<GeneralElementStage1> elems = new InputDataController()
			.receiveUserTaskElementsStage1(InputDataController.AccessElementsStage1.TASK);

		LinkedHashMap<Integer, Double> data = new LinkedHashMap<>();
		Calc calc = new Calc();
		for (int temp = MIN_TEMPERATURE; temp < MAX_TEMPERATURE; temp += DELTA_TEMPERATURE) {
			double val = calc.findEnthalpyLiquidAlloyF2p1(elems, temp, TEMPERATURE_ELEMENTS);
			data.put(temp, val);
		}
		ChartData chartData = new ChartData();
		chartData.setChart4Formula2p1Data(data);

		return chartData;
	}

	public ChartData buildChart5XTimeYValues() {
		List<GeneralElementStage1> elems = new InputDataController()
			.receiveUserTaskElementsStage1(InputDataController.AccessElementsStage1.TASK);

		Map<GeneralElementStage1, LinkedHashMap<Double, Double>> data = new LinkedHashMap<>();
		LinkedHashMap<GeneralElementStage1, Double> viElems = new LinkedHashMap<>();

		CommonCalculatedDataStage1 commonData = Container.getInstance().getStage1().getCommonCalculatedData();
		ExtraInputDataStage1 extraInputData = Container.getInstance().getStage1().getExtraInputData();

		double COEFF = 4.43E-4;
		double pp = commonData.getVaporPressureOverAlloyF5();
		int temperature = extraInputData.getTemperature();
		double surfArea = extraInputData.getSurfaceWeldArea();
		double m = extraInputData.getWeightMoltenMetal();
		double sumMMulGi = 0;
		for (GeneralElementStage1 el : elems) {
			Double ai = GeneralElementStage1.CONST_ELEMS.get(el.toString(), GeneralElementStage1.ATOMIC_FRACTION);
			double vi = COEFF * pp * Math.sqrt(ai / temperature) * surfArea;
			viElems.put(el, vi);
			sumMMulGi += m * (el.getAlloyCompWeight() / 100.0);
			data.put(el, new LinkedHashMap<Double, Double>()); // create collection for each element
		}

		for (double time = MIN_TIME; time < MAX_TIME; time += DELTA_TIME){
			for (GeneralElementStage1 el : elems) {
				if (data.containsKey(el)) {
					Double vi = viElems.get(el);
					double deltaMi = vi * surfArea * time;
					double g = el.getAlloyCompWeight();
					double concentration = ((m * (g / 100.0) - deltaMi) / sumMMulGi - deltaMi);
					data.get(el).put(time, concentration);
				}
			}
		}

		// log - print chart data
//		for (Map.Entry<GeneralElementStage1, LinkedHashMap<Double, Double>> el : data.entrySet()) {
//			System.out.println(el.getKey() + " : ");
//			for (Map.Entry<Double, Double> p : el.getValue().entrySet()) {
//				System.out.println(p.getKey() + ", " + p.getValue());
//			}
//			System.out.println();
//		}

		ChartData chartData = new ChartData();
		chartData.setChart5Data(data);

		return chartData;
	}


	// TODO: 14.06.17 Important - this a crutch approach! Correct way is make a deep copy of user elements to single list and change/rewrite values elements in this list . But current class hierarchy most like not suitable for this.
	/**
	 * Storage for temporary save chemical elements.
	 */
	private HashMap<GeneralElementStage1, GeneralElementStage1> recovery = new HashMap<>();

	/**
	 * Save all values for all elements received list, expect value {@link BaseElementStage1#getLgVaporPressureOfPureComps()}.
	 *
	 * @param elems source user elements list
	 */
	private void captureData(List<GeneralElementStage1> elems){
		for (GeneralElementStage1 srcEl : elems) {
			GeneralElementStage1 destEl = new RecoveryElement();
			destEl.setAlloyCompWeight(srcEl.getAlloyCompWeight());
			destEl.setMoleFractionAlloyElem(srcEl.getMoleFractionAlloyElem());
			destEl.setVaporPressureOfPureComps(srcEl.getVaporPressureOfPureComps());
			destEl.setPartialPressureCompsOverAlloy(srcEl.getPartialPressureCompsOverAlloy());
			destEl.setMoleFractionEachElemInVapor(srcEl.getMoleFractionEachElemInVapor());
			destEl.setWeightFractionEachElemInVapor(srcEl.getWeightFractionEachElemInVapor());
			destEl.setRateVaporizationEachElemOfWeldPool(srcEl.getRateVaporizationEachElemOfWeldPool());
			recovery.put(srcEl, destEl);
		}
	}

	/**
	 * Restore values saved by method {@link #captureData(List)}, expect value {@link BaseElementStage1#getLgVaporPressureOfPureComps()}.
	 *
	 * @param elems source user elements list
	 */
	private void restoreData(List<GeneralElementStage1> elems){
		for (GeneralElementStage1 el : elems) {
			for (Map.Entry<GeneralElementStage1, GeneralElementStage1> entry : recovery.entrySet()) {
				if (entry.getKey() == el) {
					el.setAlloyCompWeight(entry.getValue().getAlloyCompWeight());
					el.setMoleFractionAlloyElem(entry.getValue().getMoleFractionAlloyElem());
					el.setVaporPressureOfPureComps(entry.getValue().getVaporPressureOfPureComps());
					el.setPartialPressureCompsOverAlloy(entry.getValue().getPartialPressureCompsOverAlloy());
					el.setMoleFractionEachElemInVapor(entry.getValue().getMoleFractionEachElemInVapor());
					el.setWeightFractionEachElemInVapor(entry.getValue().getWeightFractionEachElemInVapor());
					el.setRateVaporizationEachElemOfWeldPool(entry.getValue().getRateVaporizationEachElemOfWeldPool());
				}
			}
		}
	}

	/**
	 * General chemical element for temporary stored element values.
	 * Used in {@link #captureData(List)} and {@link #restoreData(List)} methods.
	 */
	private class RecoveryElement extends BaseElementStage1 {
	}
}
