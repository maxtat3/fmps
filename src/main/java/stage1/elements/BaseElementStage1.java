package stage1.elements;

public abstract class BaseElementStage1 implements GeneralElementStage1 {

	/**
	 * Значение по умолчанию для массовой доли вещества если элемент не определен.
	 * Если массовая доля вещества не задана пользователем для конкретного элемента,
	 * то этот элемент в расчетах не учаввствует.
	 */
	public static final int ELEM_NOT_DEFINED = 0;

	/**
	 * Determine is basic element or not. Used in {@link ui.StudentCardFrame} only.
	 */
	private boolean isBasic;

	/**
	 * Состав элемента по весу (массовая доля вещества), gi (%)
	 * Величина задается пользователю в задании.
	 */
	private double alloyCompWeight = ELEM_NOT_DEFINED;

	/**
	 * Мольная доля элемента сплава, Ni (%)
	 * Формула 1
	 */
	private double moleFractionAlloyElem;

	/**
	 * Давление пара чистых компонентов, Pi (Па)
	 * Формула 3
	 */
	private double vaporPressureOfPureComps;

	/**
	 * Логарифм давления пара чистых компонентов, lg Pi (Па)
	 * Формула 3gr
	 * Важно: значение этого поля нужно только для построения графиков,
	 * в расчетных формулах участия не принимает.
	 */
	private double lgVaporPressureOfPureComps;

	/**
	 * Парциальное давление компонент над сплавом, Pip (Па)
	 * Формула 4
	 */
	private double partialPressureCompsOverAlloy;

	/**
	 * Логарифм парциального давления компонент над сплавом, log(Pip) (Па)
	 * Формула 4gr
	 * Важно: значение этого поля нужно только для построения графиков,
	 * в расчетных формулах участия не принимает.
	 */
	private double lgPartialPressureCompsOverAlloy;

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

	/**
	 * Скорость испарения из сварочной ванны элемента по уравнению Лэнгмюра
	 * Формула 8
	 */
	private double rateVaporizationEachElemOfWeldPool;


	public boolean isBasic() {
		return isBasic;
	}

	public void setBasic(boolean basic) {
		isBasic = basic;
	}

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

	@Override
	public double getVaporPressureOfPureComps() {
		return vaporPressureOfPureComps;
	}

	@Override
	public void setVaporPressureOfPureComps(double val) {
		vaporPressureOfPureComps = val;
	}

	public double getLgVaporPressureOfPureComps() {
		return lgVaporPressureOfPureComps;
	}

	public void setLgVaporPressureOfPureComps(double lgVaporPressureOfPureComps) {
		this.lgVaporPressureOfPureComps = lgVaporPressureOfPureComps;
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
	public void setLgPartialPressureCompsOverAlloy(double val) {
		lgPartialPressureCompsOverAlloy = val;
	}

	@Override
	public double getLgPartialPressureCompsOverAlloy() {
		return lgPartialPressureCompsOverAlloy;
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

	@Override
	public double getRateVaporizationEachElemOfWeldPool() {
		return rateVaporizationEachElemOfWeldPool;
	}

	@Override
	public void setRateVaporizationEachElemOfWeldPool(double val) {
		rateVaporizationEachElemOfWeldPool = val;
	}
}
