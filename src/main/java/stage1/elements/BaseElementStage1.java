package stage1.elements;

public abstract class BaseElementStage1 implements GeneralElementStage1 {
	private double alloyCompWeight;
	private double moleFractionAlloyElem;
	private double enthalpyLiquidAlloy;
	private double enthalpyVaporization;
	private double highTemperatureCompEnthalpy;

	@Override
	public double getAlloyCompWeight() {
		return alloyCompWeight;
	}

	@Override
	public void setAlloyCompWeight(double val) {
		this.alloyCompWeight = val;
	}

	@Override
	public void setMoleFractionAlloyElem(double val) {
		moleFractionAlloyElem = val;
	}

	@Override
	public double getMoleFractionAlloyElem() {
		return moleFractionAlloyElem;
	}

	@Override
	public void setEnthalpyLiquidAlloy(double val) {
		enthalpyLiquidAlloy = val;
	}

	@Override
	public double getEnthalpyLiquidAlloy() {
		return enthalpyLiquidAlloy;
	}

	@Override
	public void setEnthalpyVaporization(double val) {
		enthalpyVaporization = val;
	}

	@Override
	public double getEnthalpyVaporization() {
		return enthalpyVaporization;
	}

	@Override
	public void setHighTemperatureCompEnthalpyEachElemTemp(double val) {
		highTemperatureCompEnthalpy = val;
	}

	@Override
	public double getHighTemperatureCompEnthalpyEachElemTemp() {
		return highTemperatureCompEnthalpy;
	}
}
