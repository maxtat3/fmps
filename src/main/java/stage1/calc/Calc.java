package stage1.calc;

import model.Container;
import stage1.elements.C;
import stage1.elements.Fe;
import stage1.elements.GeneralElementStage1;
import stage1.elements.Mn;

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
		C c = new C();
		Mn mn = new Mn();
		fe.setAlloyCompWeight(11.1);
		c.setAlloyCompWeight(22.2);
		mn.setAlloyCompWeight(33.5);
		ArrayList<GeneralElementStage1> list = new ArrayList<>();
		list.add(fe);
		list.add(c);
		list.add(mn);
		findMoleFractionOfAlloyElems(list);
	}

	/**
	 * Расчет мольной доли всех элементов сплава (%).
	 * Результат записывается в каждый элемент.
	 *
	 * @param userElements элементы из задания пользователя
	 */
	public void findMoleFractionOfAlloyElems(List<GeneralElementStage1> userElements) {
		// отношения массовой доли вещества (%) к атомной массе элемента (кг/моль)
		double[] dElem = new double[userElements.size()];
		int dElemPointer = 0;

		for (GeneralElementStage1 userElem : userElements) {
			for (GeneralElementStage1 containerElem : Container.getInstance().getStage1().getAllElements()) {
				if (userElem.toString().equals(containerElem.toString())) {
					System.out.println(userElem.toString() + ":" + userElem.getAlloyCompWeight());
//					containerElem.setMoleFractionAlloyElem(
					dElem[dElemPointer] = userElem.getAlloyCompWeight() /
						GeneralElementStage1.CONST_ELEMS.get(containerElem.toString(), GeneralElementStage1.MOLAR_MASS);
					dElemPointer++;
//					);
					break;
				}
			}
		}

		// reset pointer for get saved order elements again.
		dElemPointer = 0;

		// find sum
		double dSum = 0;
		for (double dNElem : dElem) {
			dSum += dNElem;
		}
		System.out.println("dSum = " + dSum);

		// find molar mass for each element
		for (GeneralElementStage1 userElem : userElements) {
			for (GeneralElementStage1 containerElem : Container.getInstance().getStage1().getAllElements()) {
				if (userElem.toString().equals(containerElem.toString())) {
					containerElem.setMoleFractionAlloyElem(dElem[dElemPointer]/dSum);
					dElemPointer++;
				}
			}
		}

		System.out.println("---");
		for (GeneralElementStage1 elem : Container.getInstance().getStage1().getAllElements()) {
			System.out.println(elem.getMoleFractionAlloyElem());
		}
	}

}
