package stage1;

/**
 * Общие искомые данные которые нужно вычислить согласно заданию.
 * Каждая величина является общей для всех элементов сплпва.
 */
public class CommonCalculatedData {

	/**
	 * Энтальпия жидкого сплава H<sub>L</sub><sup>0</sup> (КДж/Моль).
	 * Формула 2.1
	 */
	private double enthalpyLiquidAlloyF2p1;

	/**
	 * Энтальпия испарения сплава &Delta;H<sub>кип</sub> (КДж/Моль).
	 * Формула 2.2
	 */
	private double enthalpyVaporizationF2p2;

	/**
	 * Энтальпия пара для сплава H<sub>g</sub> (КДж/Моль).
	 * Формула 2.3
	 */
	private double enthalpyVaporF2p3;

	/**
	 * Давление пара над сплавом P<sup>p</sup> (Па).
	 * Формула 5
	 */
	private double vaporPressureOverAlloyF5;

	/**
	 * Уменьшение массы расплавленного металла за счет испарения &Delta;<i>v</i><sub>m</sub> (гр/сек)
	 * Формула 9
	 */
	private double decreaseMoltenMetalDueVaporizationF9;


	public double getEnthalpyLiquidAlloyF2p1() {
		return enthalpyLiquidAlloyF2p1;
	}

	public void setEnthalpyLiquidAlloyF2p1(double enthalpyLiquidAlloyF2p1) {
		this.enthalpyLiquidAlloyF2p1 = enthalpyLiquidAlloyF2p1;
	}

	public double getEnthalpyVaporizationF2p2() {
		return enthalpyVaporizationF2p2;
	}

	public void setEnthalpyVaporizationF2p2(double enthalpyVaporizationF2p2) {
		this.enthalpyVaporizationF2p2 = enthalpyVaporizationF2p2;
	}

	public double getEnthalpyVaporF2p3() {
		return enthalpyVaporF2p3;
	}

	public void setEnthalpyVaporF2p3(double enthalpyVaporF2p3) {
		this.enthalpyVaporF2p3 = enthalpyVaporF2p3;
	}

	public double getVaporPressureOverAlloyF5() {
		return vaporPressureOverAlloyF5;
	}

	public void setVaporPressureOverAlloyF5(double vaporPressureOverAlloyF5) {
		this.vaporPressureOverAlloyF5 = vaporPressureOverAlloyF5;
	}

	public double getDecreaseMoltenMetalDueVaporizationF9() {
		return decreaseMoltenMetalDueVaporizationF9;
	}

	public void setDecreaseMoltenMetalDueVaporizationF9(double decreaseMoltenMetalDueVaporizationF9) {
		this.decreaseMoltenMetalDueVaporizationF9 = decreaseMoltenMetalDueVaporizationF9;
	}
}
