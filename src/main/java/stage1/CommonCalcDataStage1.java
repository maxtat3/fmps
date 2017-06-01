package stage1;

/**
 * Общие искомые данные которые нужно вычислить согласно заданию.
 * Каждая величина является общей для всех элементов сплпва.
 */
public class CommonCalcDataStage1 {

	/**
	 * Энтальпия жидкого сплава H<sub>L</sub><sup>0</sup> (КДж/Моль).
	 * Формула 2.1
	 */
	private double enthalpyLiquidAlloy;

	/**
	 * Энтальпия испарения сплава &Delta;H<sub>кип</sub> (КДж/Моль).
	 * Формула 2.2
	 */
	private double enthalpyVaporization;

	/**
	 * Энтальпия пара для сплава H<sub>g</sub> (КДж/Моль).
	 * Формула 2.3
	 */
	private double enthalpyVapor;

	/**
	 * Давление пара над сплавом P<sup>p</sup> (Па).
	 * Формула 5
	 */
	private double vaporPressureOverAlloy;

	/**
	 * Уменьшение массы расплавленного металла за счет испарения &Delta;<i>v</i><sub>m</sub> (гр/сек)
	 * Формула 9
	 */
	private double decreaseMoltenMetalDueVaporization;


	public double getEnthalpyLiquidAlloy() {
		return enthalpyLiquidAlloy;
	}

	public void setEnthalpyLiquidAlloy(double enthalpyLiquidAlloy) {
		this.enthalpyLiquidAlloy = enthalpyLiquidAlloy;
	}

	public double getEnthalpyVaporization() {
		return enthalpyVaporization;
	}

	public void setEnthalpyVaporization(double enthalpyVaporization) {
		this.enthalpyVaporization = enthalpyVaporization;
	}

	public double getEnthalpyVapor() {
		return enthalpyVapor;
	}

	public void setEnthalpyVapor(double enthalpyVapor) {
		this.enthalpyVapor = enthalpyVapor;
	}

	public double getVaporPressureOverAlloy() {
		return vaporPressureOverAlloy;
	}

	public void setVaporPressureOverAlloy(double vaporPressureOverAlloy) {
		this.vaporPressureOverAlloy = vaporPressureOverAlloy;
	}

	public double getDecreaseMoltenMetalDueVaporization() {
		return decreaseMoltenMetalDueVaporization;
	}

	public void setDecreaseMoltenMetalDueVaporization(double decreaseMoltenMetalDueVaporization) {
		this.decreaseMoltenMetalDueVaporization = decreaseMoltenMetalDueVaporization;
	}
}
