package calc;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import stage1.FormulasStage1;
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
	private final double SURFACE_WELD_AREA = 4.6;
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

		// expected data for formula 8
		feExp.setRateVaporizationEachElemOfWeldPool(0.0040);
		alExp.setRateVaporizationEachElemOfWeldPool(1.423E-4);
		cExp.setRateVaporizationEachElemOfWeldPool(4.456E-10);
		siExp.setRateVaporizationEachElemOfWeldPool(1.295E-7);
		tiExp.setRateVaporizationEachElemOfWeldPool(3.085E-7);

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
		new FormulasStage1().findMoleFractionOfAlloyElemsF1(userElements);

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
			System.out.println();
		}
	}

	@Test
	public void findEnthalpyLiquidAlloyFormula2p1Test(){
		double result = new FormulasStage1().findEnthalpyLiquidAlloyF2p1(userElements, TEMPERATURE_TASK, TEMPERATURE_ELEMENTS);

		double expectedResult = 83.48332;
		Assert.assertEquals(expectedResult, result, DOUBLE_DELTA);

		if (isEnableLog) {
			System.out.print("2.1. Энтальпия жидкого сплава (КДж/Моль): " + result + "\n\n");
		}
	}

	@Test
	public void findEnthalpyVaporizationFormula2p2Test(){
		double result = new FormulasStage1().findEnthalpyVaporizationF2p2(userElements);

		double expectedValue = 489.373;
		Assert.assertEquals(expectedValue, result, DOUBLE_DELTA);

		if (isEnableLog) {
			System.out.print("2.2. Энтальпия испарения (КДж/Моль): " + result + "\n\n");
		}
	}

	@Test
	public void findEnthalpyVaporFormula2p3Test(){
		double result = new FormulasStage1().findEnthalpyVaporF2p3(userElements, TEMPERATURE_TASK, TEMPERATURE_ELEMENTS);

		double expectedValue = 572.85648;
		Assert.assertEquals(expectedValue, result, DOUBLE_DELTA);

		if (isEnableLog) {
			System.out.println("2.3. энтальпию пара (КДж/Моль): " + result + "\n\n");
		}
	}

	@Test
	public void findVaporPressureOfPureCompsFormula3Test(){
		new FormulasStage1().findVaporPressureOfPureCompsF3(userElements, TEMPERATURE_TASK);

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
			System.out.println();
		}
	}

	@Test
	public void findPartialPressureCompsOverAlloyFormula4Test(){
		new FormulasStage1().findPartialPressureCompsOverAlloyF4(userElements,  TEMPERATURE_TASK);

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
			System.out.println();
		}
	}

	@Test
	public void findVaporPressureOverAlloyFormula5Test(){
		double result = new FormulasStage1().findVaporPressureOverAlloyF5(userElements, TEMPERATURE_TASK);

		double expectedValue = 420.26;
		Assert.assertEquals(expectedValue, result, DOUBLE_DELTA);

		if (isEnableLog) {
			System.out.print("5. Давление пара над сплавом (Па): " + result + "\n\n");
		}
	}

	@Test
	public void findMoleFractionEachElemInVaporFormula6Test(){
		new FormulasStage1().findMoleFractionEachElemInVaporF6(userElements,  TEMPERATURE_TASK);

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
			System.out.println();
		}
	}

	@Test
	public void findWeightFractionEachElemInVaporFormula7Test(){
		new FormulasStage1().findWeightFractionEachElemInVaporF7(userElements,  TEMPERATURE_TASK);

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
			System.out.println();
		}
	}

	@Test
	public void findRateVaporizationEachElemOfWeldPoolFormula8Test(){
		new FormulasStage1().findRateVaporizationEachElemOfWeldPoolF8(userElements, TEMPERATURE_TASK, SURFACE_WELD_AREA);

		for (GeneralElementStage1 uEl : userElements) {
			for (GeneralElementStage1 eEl : expectedList) {
				if (uEl.toString().equals(eEl.toString())) {
					Assert.assertEquals(eEl.getRateVaporizationEachElemOfWeldPool(), uEl.getRateVaporizationEachElemOfWeldPool(), DOUBLE_DELTA);
				}
			}
		}

		if (isEnableLog) {
			System.out.println("8. Скорость испарения из сварочной ванны каждого элемента (гр/сек): ");
			for (GeneralElementStage1 elem : userElements) {
				System.out.println(elem.toString() + " : " + elem.getRateVaporizationEachElemOfWeldPool());
			}
			System.out.println();
		}
	}

	@Test
	public void findDecreaseMoltenMetalDueVaporizationFormula9Test(){
		double result = new FormulasStage1().findDecreaseMoltenMetalDueVaporizationF9(userElements, TEMPERATURE_TASK, SURFACE_WELD_AREA);

		double expectedValue = 4.1E-3;
		Assert.assertEquals(expectedValue, result, DOUBLE_DELTA);

		if (isEnableLog) System.out.println("9. Скорость уменьшения массы расплавленного металла за счет испарения (гр/сек): " + result + "\n\n");
	}
}
