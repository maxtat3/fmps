package stage1.calc;

import model.Container;
import stage1.elements.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Расчеты на основании входных данных для задачи №1
 */
public class Calc {

	public Calc() {
//		Container.getInstance().getStage1().getFe().setMoleFractionAlloyElem(
//			calc.moleFraction(
//				GeneralElementStage1.CONST_ELEMS.get(GeneralElementStage1.FE, GeneralElementStage1.MOLAR_MASS), 11.7
//			)
//		);

		Fe fe = new Fe();
		Al al = new Al();
		C c = new C();
		Si si = new Si();
		Ti ti = new Ti();

		// initial data
		fe.setAlloyCompWeight(89.9);
		al.setAlloyCompWeight(1.79);
		c.setAlloyCompWeight(4.89);
		si.setAlloyCompWeight(4.21);
		ti.setAlloyCompWeight(4.09);

		ArrayList<GeneralElementStage1> list = new ArrayList<>();
		list.add(fe);
		list.add(al);
		list.add(c);
		list.add(si);
		list.add(ti);

		// do calculate
		findMoleFractionOfAlloyElems(list);
		findEnthalpyLiquidAlloy(list, 2273, 1800);
	}

	/**
	 * Расчет мольной доли всех элементов сплава (%).
	 * Результат записывается в каждый элемент.
	 * Формула 1.
	 *
	 * @param userElements список элементов из задания пользователя
	 */
	public void findMoleFractionOfAlloyElems(List<GeneralElementStage1> userElements) {
		double gi;
		double molarMass;
		double[] relGiAiElems = new double[userElements.size()]; // отношения массовой доли вещества (%) к атомной массе элемента (кг/моль)
		int dElemPointer = 0;

		for (GeneralElementStage1 userElem : userElements) {
			for (GeneralElementStage1 containerElem : Container.getInstance().getStage1().getAllElements()) {
				if (userElem.toString().equals(containerElem.toString())) {
					gi = userElem.getAlloyCompWeight();
					molarMass = GeneralElementStage1.CONST_ELEMS.get(containerElem.toString(), GeneralElementStage1.MOLAR_MASS);
					relGiAiElems[dElemPointer] = gi / molarMass;
					dElemPointer++;
					break;
				}
			}
		}

		// reset pointer for get saved order elements again.
		dElemPointer = 0;

		// find sum
		double relGiAiSum = 0;
		for (double rel : relGiAiElems) {
			relGiAiSum += rel;
		}
		System.out.println("relGiAiSum = " + relGiAiSum);

		// find molar mass for each element
		for (GeneralElementStage1 userElem : userElements) {
			for (GeneralElementStage1 containerElem : Container.getInstance().getStage1().getAllElements()) {
				if (userElem.toString().equals(containerElem.toString())) {
					containerElem.setMoleFractionAlloyElem(relGiAiElems[dElemPointer]/relGiAiSum);
					dElemPointer++;
				}
			}
		}

		System.out.println("formula 1 results :");
		for (GeneralElementStage1 elem : Container.getInstance().getStage1().getAllElements()) {
			System.out.println(elem.toString() + ":" + elem.getMoleFractionAlloyElem());
		}
	}

	/**
	 * Расчет энтальпии жидкого сплава (КДж/Моль).
	 * Формула 2.1
	 *
	 * @param userElements список элементов из задания пользователя
	 */
	public void findEnthalpyLiquidAlloy(List<GeneralElementStage1> userElements, int temperatureTask, int temperatureConst) {
		double[] ht0minh2980 = new double[userElements.size()]; // HT0-H2980 - temp values 1 for find enthalpy of liquid alloy
		double[] ht0minh2980mulni = new double[userElements.size()]; // (HT0-H2980)*Ni - temp values 2 for find enthalpy of vaporization
		int elemPointer = 0;

		double highTemprEnthalpy;
		double thermalCapacity;
		double deltaTempr;
		double moleFractionAlloyElem;

		for (GeneralElementStage1 userElem : userElements) {
			for (GeneralElementStage1 containerElem : Container.getInstance().getStage1().getAllElements()) {
				if (userElem.toString().equals(containerElem.toString())) {
					highTemprEnthalpy = GeneralElementStage1.CONST_ELEMS.get(containerElem.toString(), GeneralElementStage1.HIGH_TEMPER_ENTHALPY);
					thermalCapacity = GeneralElementStage1.CONST_ELEMS.get(containerElem.toString(), GeneralElementStage1.THERMAL_CAPACITY);
					deltaTempr = temperatureTask - temperatureConst;

					ht0minh2980[elemPointer] = (highTemprEnthalpy * 1000 + thermalCapacity * deltaTempr) / 1000;

					moleFractionAlloyElem = containerElem.getMoleFractionAlloyElem();
					ht0minh2980mulni[elemPointer] = ht0minh2980[elemPointer] * moleFractionAlloyElem;

					elemPointer++;
				}
			}
		}

		double enthalpyLiquidAlloySum = 0;
		for (double val : ht0minh2980mulni) {
			enthalpyLiquidAlloySum += val;
		}

		for (double v : ht0minh2980mulni) {
			System.out.println("v = " + v);
		}

		System.out.println("enthalpyLiquidAlloySum = " + enthalpyLiquidAlloySum);
	}

	/**
	 * Расчет энтальпии испарения (КДж/Моль).
	 * Формула 2.2
	 *
	 * @param userElements список элементов из задания пользователя
	 */
	public void findEnthalpyVaporization(List<GeneralElementStage1> userElements){
		double[] ht0minh2980mulni = new double[userElements.size()]; // temp value (HTo-H298o)*Ni for find enthalpy of vaporization
		int elemPointer = 0;
		double moleFractionAlloyElem;
		double thermalCapacity;

		for (GeneralElementStage1 userElem : userElements) {
			for (GeneralElementStage1 containerElem : Container.getInstance().getStage1().getAllElements()) {
				if (userElem.toString().equals(containerElem.toString())) {
					moleFractionAlloyElem = containerElem.getMoleFractionAlloyElem();
					thermalCapacity = GeneralElementStage1.CONST_ELEMS.get(containerElem.toString(), GeneralElementStage1.THERMAL_CAPACITY);

					ht0minh2980mulni[elemPointer] = moleFractionAlloyElem * thermalCapacity;
					elemPointer++;
				}
			}
		}

		for (double val : ht0minh2980mulni) {
			System.out.println(val);
		}
	}


}
