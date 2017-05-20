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

		// expected data for formula 3
		feExp.setVaporPressureOfPureComps(575.7606);
		alExp.setVaporPressureOfPureComps(7010.8656);
		cExp.setVaporPressureOfPureComps(0.0005);
		siExp.setVaporPressureOfPureComps(65.2257);
		tiExp.setVaporPressureOfPureComps(35.7447);

		// expected data for formula 4
		feExp.setPartialPressureCompsOverAlloy(399.7415);
		alExp.setPartialPressureCompsOverAlloy(20.4620);
		cExp.setPartialPressureCompsOverAlloy(0.0001);
		siExp.setPartialPressureCompsOverAlloy(0.0182);
		tiExp.setPartialPressureCompsOverAlloy(0.0332);

		// expected data for formula 6
		feExp.setMoleFractionEachElemInVapor(0.95);
		alExp.setMoleFractionEachElemInVapor(0.048);
		cExp.setMoleFractionEachElemInVapor(0); // TODO: 20.05.17 как быть пользователю с такими мпленькими значениями, даже в % ?
		siExp.setMoleFractionEachElemInVapor(0);
		tiExp.setMoleFractionEachElemInVapor(0);

		// expected data for formula 7
		feExp.setWeightFractionEachElemInVapor(97.577);
		alExp.setWeightFractionEachElemInVapor(2.41);
		cExp.setWeightFractionEachElemInVapor(0);
		siExp.setWeightFractionEachElemInVapor(0);
		tiExp.setWeightFractionEachElemInVapor(0);

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

	@Test
	public void findEnthalpyLiquidAlloyFormula2p1Test(){
		double result = new Calc().findEnthalpyLiquidAlloy(userElements, TEMPERATURE_TASK, TEMPERATURE_ELEMENTS);

		double expectedResult = 83.48332;
		Assert.assertEquals(expectedResult, result, DOUBLE_DELTA);

		if (isEnableLog) {
			System.out.print("2.1. Энтальпия жидкого сплава (КДж/Моль): " + result);
		}
	}

	@Test
	public void findEnthalpyVaporizationFormula2p2Test(){
		double result = new Calc().findEnthalpyVaporization(userElements);

		double expectedValue = 489.373;
		Assert.assertEquals(expectedValue, result, DOUBLE_DELTA);

		if (isEnableLog) {
			System.out.print("2.2. Энтальпия испарения (КДж/Моль): " + result);
		}
	}

	@Test
	public void findEnthalpyVaporFormula2p3Test(){
		double result = new Calc().findEnthalpyVapor(userElements, TEMPERATURE_TASK, TEMPERATURE_ELEMENTS);

		double expectedValue = 572.85648;
		Assert.assertEquals(expectedValue, result, DOUBLE_DELTA);

		if (isEnableLog) {
			System.out.println("2.3. энтальпию пара (КДж/Моль): " + result);
		}
	}

	@Test
	public void findVaporPressureOfPureCompsFormula3Test(){
		new Calc().findVaporPressureOfPureComps(userElements, TEMPERATURE_TASK);

		for (GeneralElementStage1 uEl : userElements) {
			for (GeneralElementStage1 eEl : expectedList) {
				if (uEl.toString().equals(eEl.toString())) {
					Assert.assertEquals(eEl.getVaporPressureOfPureComps(), uEl.getVaporPressureOfPureComps(), DOUBLE_DELTA);
				}
			}
		}

		if (isEnableLog) {
			System.out.println("3. Давление пара чистых компонентов (Па): ");
			for (GeneralElementStage1 elem : userElements) {
				System.out.println(elem.toString() + " : " + elem.getVaporPressureOfPureComps());
			}
		}
	}

	@Test
	public void findPartialPressureCompsOverAlloyFormula4Test(){
		new Calc().findPartialPressureCompsOverAlloy(userElements,  TEMPERATURE_TASK);

		for (GeneralElementStage1 uEl : userElements) {
			for (GeneralElementStage1 eEl : expectedList) {
				if (uEl.toString().equals(eEl.toString())) {
					Assert.assertEquals(
						eEl.getPartialPressureCompsOverAlloy(), uEl.getPartialPressureCompsOverAlloy(), DOUBLE_DELTA
					);
				}
			}
		}

		if (isEnableLog) {
			System.out.println("4. Парциальное давление компонент над сплавом (Па): ");
			for (GeneralElementStage1 elem : userElements) {
				System.out.println(elem.toString() + " : " + elem.getPartialPressureCompsOverAlloy());
			}
		}
	}

	@Test
	public void findVaporPressureOverAlloyFormula5Test(){
		double result = new Calc().findVaporPressureOverAlloy(userElements, TEMPERATURE_TASK);

		double expectedValue = 420.26;
		Assert.assertEquals(expectedValue, result, DOUBLE_DELTA);

		if (isEnableLog) {
			System.out.print("5. Давление пара над сплавом (Па): " + result);
		}
	}

	@Test
	public void findMoleFractionEachElemInVaporFormula6Test(){
		new Calc().findMoleFractionEachElemInVapor(userElements,  TEMPERATURE_TASK);

		for (GeneralElementStage1 uEl : userElements) {
			for (GeneralElementStage1 eEl : expectedList) {
				if (uEl.toString().equals(eEl.toString())) {
					Assert.assertEquals(eEl.getMoleFractionEachElemInVapor(), uEl.getMoleFractionEachElemInVapor(), DOUBLE_DELTA);
				}
			}
		}

		if (isEnableLog) {
			System.out.println("6. Мольная доля каждого компонента в паре (доли): ");
			for (GeneralElementStage1 elem : userElements) {
				System.out.println(elem.toString() + " : " + elem.getMoleFractionEachElemInVapor());
			}
		}
	}

	@Test
	public void findWeightFractionEachElemInVaporFormula7Test(){
		new Calc().findWeightFractionEachElemInVapor(userElements,  TEMPERATURE_TASK);

		for (GeneralElementStage1 uEl : userElements) {
			for (GeneralElementStage1 eEl : expectedList) {
				if (uEl.toString().equals(eEl.toString())) {
					Assert.assertEquals(eEl.getWeightFractionEachElemInVapor(), uEl.getWeightFractionEachElemInVapor(), DOUBLE_DELTA);
				}
			}
		}

		if (isEnableLog) {
			System.out.println("7. Весовая доля каждого компонента в паре (%): ");
			for (GeneralElementStage1 elem : userElements) {
				System.out.println(elem.toString() + " : " + elem.getWeightFractionEachElemInVapor());
			}
		}
	}

	@Test
	public void findRateVaporizationEachElemOfWeldPoolFormula8Test(){

	}

	@Test
	public void findDecreaseMoltenMetalDueVaporizationFormula9Test(){

	}
}
