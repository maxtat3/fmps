package stage1.elements;

public abstract class BaseElementStage1 implements GeneralElementStage1 {

	/**
	 * Состав элемента по весу (массовая доля вещества), gi (%)
	 * Величина задается пользователю в задании.
	 */
	private double alloyCompWeight;

	/**
	 * Мольная доля элемента сплава, Ni (%)
	 * Формула 1
	 */
	private double moleFractionAlloyElem;

	/**
	 * Высокотемпературная составляющая энтальпии отдельного вещества, (HT0-H2980)
	 * Временная величина.
	 * Формула 2.1.t1
	 */
	private double highTemperatureCompEnthalpy;

	/**
	 * Энтальпия испарения для каждого элемента, (HT0-H2980)*Ni
	 * Временная величина.
	 * Формула 2.1.t2
	 */
	private double enthalpyVaporization;

	/**
	 * Давление пара чистых компонентов, Pi (Па)
	 * Формула 3
	 */
	private double vaporPressureOfPureComps;

	/**
	 * Парциальное давление компонент над сплавом, Pip (Па)
	 * Формула 4
	 */
	private double partialPressureCompsOverAlloy;

	/**
	 * Мольная доля компонента в паре,
	 * Формула 6
	 */
	private double moleFractionEachElemInVapor;

	/**
	 * Весовая доля каждого компонента в паре (%)
	 * Формула 7
	 */
	private double weightFractionEachElemInVapor;


	@Override
	public double getAlloyCompWeight() {
		return alloyCompWeight;
	}

	@Override
	public void setAlloyCompWeight(double val) {
		this.alloyCompWeight = val;
	}

	@Override
	public double getMoleFractionAlloyElem() {
		return moleFractionAlloyElem;
	}

	@Override
	public void setMoleFractionAlloyElem(double val) {
		moleFractionAlloyElem = val;
	}

	public double getHighTemperatureCompEnthalpyEachElemTemp() {
		return highTemperatureCompEnthalpy;
	}

	public void setHighTemperatureCompEnthalpyEachElemTemp(double val) {
		highTemperatureCompEnthalpy = val;
	}

	public double getEnthalpyVaporization() {
		return enthalpyVaporization;
	}

	public void setEnthalpyVaporization(double val) {
		enthalpyVaporization = val;
	}

	@Override
	public double getVaporPressureOfPureComps() {
		return vaporPressureOfPureComps;
	}

	@Override
	public void setVaporPressureOfPureComps(double val) {
		vaporPressureOfPureComps = val;
	}

	@Override
	public double getPartialPressureCompsOverAlloy() {
		return partialPressureCompsOverAlloy;
	}

	@Override
	public void setPartialPressureCompsOverAlloy(double val) {
		partialPressureCompsOverAlloy = val;
	}

	@Override
	public double getMoleFractionEachElemInVapor() {
		return moleFractionEachElemInVapor;
	}

	@Override
	public void setMoleFractionEachElemInVapor(double val) {
		moleFractionEachElemInVapor = val;
	}

	@Override
	public double getWeightFractionEachElemInVapor() {
		return weightFractionEachElemInVapor;
	}

	@Override
	public void setWeightFractionEachElemInVapor(double val) {
		weightFractionEachElemInVapor = val;
	}
}
