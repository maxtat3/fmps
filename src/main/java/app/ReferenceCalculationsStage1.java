package app;

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
		System.out.println("temperature = " + temperature);
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

	private static final int MIN_TEMPERATURE = 1500;
	private static final int MAX_TEMPERATURE = 3000;
	private static final int DELTA_TEMPERATURE = 10;

	/**
	 * Получение данных для графика - Зависимость паров чистых компонент от температуры Т
	 * Вычисляется согласно формуле 3 {@link Calc#findVaporPressureOfPureCompsF3(List, int)}
	 */
	public HashMap<GeneralElementStage1, LinkedHashMap<Integer, Double>> buildChart1(){
		// TODO: 13.06.17 may be move #receiveUserTaskElementsStage1 method to Container !?
		List<GeneralElementStage1> elems = new InputDataController()
			.receiveUserTaskElementsStage1(InputDataController.AccessElementsStage1.TASK);

		captureData(elems);
		Calc calc = new Calc();
		HashMap<GeneralElementStage1, LinkedHashMap<Integer, Double>> chData = new HashMap<>();
		for (GeneralElementStage1 el : elems) {
			chData.put(el, new LinkedHashMap<Integer, Double>());
		}
		for (int temp = MIN_TEMPERATURE; temp < MAX_TEMPERATURE; temp += DELTA_TEMPERATURE) {
			calc.findVaporPressureOfPureCompsF3(elems, temp);
			for (GeneralElementStage1 el : elems) {
				double point = el.getLgVaporPressureOfPureComps();
				if (chData.containsKey(el)) {
					chData.get(el).put(temp, point);
				}
			}
		}
		restoreData(elems);

//		for (Map.Entry<GeneralElementStage1, LinkedHashMap<Integer, Double>> entry : chData.entrySet()) {
//			GeneralElementStage1 el = entry.getKey();
//			System.out.println(el.toString() + ": ");
//			for (Map.Entry<Integer, Double> pData : entry.getValue().entrySet()) {
//				System.out.println(pData.getKey() + " °C : " + pData.getValue());
//			}
//			System.out.println();
//		}

		return chData;
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
