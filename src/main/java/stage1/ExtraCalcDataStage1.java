package stage1;

/**
 * Дополнительные искомые данные которые нужно вычислить согласно заданию.
 * Каждая величина является общей для всех элементов сплпва.
 */
public class ExtraCalcDataStage1 {

	/**
	 * Энтальпия жидкого сплава, HL0 (КДж/Моль).
	 * Формула 2.1
	 */
	private double enthalpyLiquidAlloy;

	/**
	 * Энтальпия испарения сплава (КДж/Моль).
	 * Формула 2.2
	 */
	private double enthalpyVaporization;

	/**
	 * Ээнтальпия пара для сплава (КДж/Моль).
	 * Формула 2.3
	 */
	private double enthalpyVapor;


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
}
