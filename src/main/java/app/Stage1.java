package app;

/**
 *
 */
public class Stage1 {

	public double checkAllElementsSum() {
		return UserInputData.InputStage1.alloyCompWeightFe + UserInputData.InputStage1.alloyCompWeightAl
			+ UserInputData.InputStage1.alloyCompWeightC + UserInputData.InputStage1.alloyCompWeightSi
			+ UserInputData.InputStage1.alloyCompWeightTi;
	}

	/**
	 * Мольная доля всех элементов сплава (Ni) .
	 *
	 * @return массив мольных масс
	 */
	public double[] moleFractionAllElements() {
		double dFe = UserInputData.InputStage1.alloyCompWeightFe / Const.MolarMassFe;
		double dAl = UserInputData.InputStage1.alloyCompWeightAl / Const.MolarMassAl;
		double dC = UserInputData.InputStage1.alloyCompWeightC / Const.MolarMassC;
		double dSi = UserInputData.InputStage1.alloyCompWeightSi / Const.MolarMassSi;
		double dTi = UserInputData.InputStage1.alloyCompWeightTi / Const.MolarMassTi;
		double dSum = dFe + dAl + dC + dSi + dTi;

		return new double[]{dFe/dSum, dAl/dSum, dC/dSum, dSi/dSum, dTi/dSum};
	}

}
