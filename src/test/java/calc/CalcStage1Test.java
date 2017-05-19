package calc;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
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
		// for calculate this result must be calculate formula 1
		new Calc().findMoleFractionOfAlloyElems(userElements);
		double result = new Calc().findEnthalpyLiquidAlloy(userElements, TEMPERATURE_TASK, TEMPERATURE_ELEMENTS);

		double expectedResult = 83.48332;
		Assert.assertEquals(expectedResult, result, DOUBLE_DELTA);

		if (isEnableLog) {
			System.out.print("2.1. Энтальпия жидкого сплава (КДж/Моль): " + result);
		}
	}

	@Test
	public void findEnthalpyVaporizationFormula2p2Test(){
		// for calculate this result must be calculate formula 1
		new Calc().findMoleFractionOfAlloyElems(userElements);
		double result = new Calc().findEnthalpyVaporization(userElements);

		double expectedValue = 489.373;
		Assert.assertEquals(expectedValue, result, DOUBLE_DELTA);

		if (isEnableLog) {
			System.out.print("2.2. Энтальпия испарения (КДж/Моль): " + result);
		}
	}

	@Ignore
	@Test
	public void findEnthalpyVaporFormula2p3Test(){
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
		// after calculate this must be calculate several formula - see in this method dependencies
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

	}

	@Test
	public void findMoleFractionEachElemInVaporFormula6Test(){

	}

	@Test
	public void findWeightFractionEachElemInVaporFormula7Test(){

	}

	@Test
	public void findRateVaporizationEachElemOfWeldPoolFormula8Test(){

	}

	@Test
	public void findDecreaseMoltenMetalDueVaporizationFormula9Test(){

	}
}
