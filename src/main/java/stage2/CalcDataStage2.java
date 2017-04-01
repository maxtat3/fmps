package stage2;

/**
 * Данные которые нужно вычислить согласно заданию.
 */
public class CalcDataStage2 {

	/**
	 * Изменение энергии Гиббса (КДж/Моль).
	 */
	private double deltaGibbsEnergy;

	/**
	 * Константа равновесия. Определяется из уравнения Вант-Гоффа.
	 */
	private double eqConst;

	/**
	 * Коэффициент pi состояния в начальный момент.
	 */
	private double piCoeff;

	/**
	 * Направления протекания реакций.
	 */
	private double reactionFlowDirection; // todo is double type ?

	/**
	 * Состава газовой смеси для данной температуры, если изначально она содержит только углекислый газ,
	 * а уравнение диссоциации имеет вид: CO 2 = CO + O
	 */
	private double gasComposition; // todo - may be applied object type


}
