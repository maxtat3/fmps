package calc;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import stage1.calc.Calc;
import stage1.elements.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class CalcStage1Test {

	private static final boolean isEnableLog = true;
	public static final double DOUBLE_DELTA = 0.01;

	private final int TEMPERATURE_ELEMENTS = 1800; // temperature elements (in Celsius degrees)
	private final int TEMPERATURE_TASK = 2273; // temperature defined for user in task (in Celsius degrees)
	private static List<GeneralElementStage1> userElements;
	private static List<GeneralElementStage1> expectedList;

	@BeforeClass
	public static void initUserData() {

		// initial data defined in user task
		Fe fe = new Fe();
		Al al = new Al();
		C c = new C();
		Si si = new Si();
		Ti ti = new Ti();

		fe.setAlloyCompWeight(89.9);
		al.setAlloyCompWeight(1.79);
		c.setAlloyCompWeight(4.89);
		si.setAlloyCompWeight(4.21);
		ti.setAlloyCompWeight(4.09);

		userElements = new ArrayList<>();
		userElements.add(fe);
		userElements.add(al);
		userElements.add(c);
		userElements.add(si);
		userElements.add(ti);

		// expected data from all formulas
		expectedList = new ArrayList<>();
		Fe feExp = new Fe();
		Al alExp = new Al();
		C cExp = new C();
		Si siExp = new Si();
		Ti tiExp = new Ti();

		// expected data for formula 1
		feExp.setMoleFractionAlloyElem(0.6942);
		alExp.setMoleFractionAlloyElem(0.0286);
		cExp.setMoleFractionAlloyElem(0.1756);
		siExp.setMoleFractionAlloyElem(0.0646);
		tiExp.setMoleFractionAlloyElem(0.0368);

		//fill elements
		expectedList.addAll(Arrays.asList(feExp, alExp, cExp, siExp, tiExp));

		if (isEnableLog) {
			System.out.println("=======================================");
			System.out.println("    Результаты расчетов задачи 1    ");
			System.out.println("=======================================\n\n");
		}
	}

	@Test
	public void moleFractionOfAlloyElementsStage1Formula1Test() {
		new Calc().findMoleFractionOfAlloyElems(userElements);

		for (GeneralElementStage1 uEl : userElements) {
			for (GeneralElementStage1 eEl : expectedList) {
				if (uEl.toString().equals(eEl.toString())) {
					Assert.assertEquals(eEl.getMoleFractionAlloyElem(), uEl.getMoleFractionAlloyElem(), DOUBLE_DELTA);
				}
			}
		}

		if (isEnableLog) {
			System.out.println("1. Мольная доля всех элементов сплава (доли): ");
			for (GeneralElementStage1 elem : userElements) {
				System.out.println(elem.toString() + " : " + elem.getMoleFractionAlloyElem());
			}
		}
	}

}
