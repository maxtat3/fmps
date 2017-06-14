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
	private static final int DELTA_TEMPERATURE = 100;

	/**
	 * Получение данных для графика - Зависимость паров чистых компонент от температуры Т
	 * Вычисляется согласно формуле 3 {@link Calc#findVaporPressureOfPureCompsF3(List, int)}
	 */
	public void buildChart1(){
		// TODO: 13.06.17 may be move #receiveUserTaskElementsStage1 method to Container !?
		List<GeneralElementStage1> elems = new InputDataController()
			.receiveUserTaskElementsStage1(InputDataController.AccessElementsStage1.TASK);

		captureData(elems);
		double vpopcsBefore = Container.getInstance().getStage1().getFe().getVaporPressureOfPureComps();

		Calc calc = new Calc();
		for (int temp = MIN_TEMPERATURE; temp < MAX_TEMPERATURE; temp += DELTA_TEMPERATURE) {
			calc.findVaporPressureOfPureCompsF3(elems, temp);
			System.out.println("temp = " + temp);
			for (GeneralElementStage1 el : elems) {
				double vpopcs = el.getLgVaporPressureOfPureComps();
				System.out.println(el.toString() + " vpopcs: " + vpopcs);
			}
			System.out.println("---");
		}

		restoreData(elems);

		double vpopcsAfter = Container.getInstance().getStage1().getFe().getVaporPressureOfPureComps();

		System.out.println("vp0 = " + vpopcsBefore);
		System.out.println("vp1 = " + vpopcsAfter);

	}


	// TODO: 14.06.17 Important - this a crutch approach! Correct way is make a deep copy of user elements to single list and change/rewrite values elements in this list . But current class hierarchy most like not suitable for this.

	private HashMap<GeneralElementStage1, Double> vpopcs = new HashMap<>();

	/**
	 * Save values of method {@link BaseElementStage1#getVaporPressureOfPureComps()} before build chart.
	 *
	 * @param elems user elements
	 */
	private void captureData(List<GeneralElementStage1> elems){
		for (GeneralElementStage1 el : elems) {
			vpopcs.put(el, el.getVaporPressureOfPureComps());
		}
	}

	/**
	 * Restore values from {@link BaseElementStage1#getVaporPressureOfPureComps()} after build chart.
	 *
	 * @param elems user elements
	 */
	private void restoreData(List<GeneralElementStage1> elems){
		for (GeneralElementStage1 el : elems) {
			for (Map.Entry<GeneralElementStage1, Double> entry : vpopcs.entrySet()) {
				if (entry.getKey() == el) {
					el.setVaporPressureOfPureComps(entry.getValue());
				}
			}
		}
	}

}
