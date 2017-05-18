package stage2.reactions;

/**
 *
 */
public abstract class BasicReaction {

	public static final int Patm = 100000; // pressure in pascals

	// formula 1 t1
	private double deltaH298;

	// formula 1 t2
	private double deltaS298;

	// formula 1 t3
	private double deltaCp298;

	// formula 1
	private double deltaGT;

	// formula 2.1
	private double lgKp;

	// formula 2.2
	private double kp;

	// formula 3
	private double pi;

	// formula 4
	private enum ReactionDirection {
		LEFT, RIGHT, ZERO
	}

	// formula 5.1
	private double deltaGTFormula5;

	// formula 5.2
	private double lgKpFormula5;

	// formula 5.3
	private double P0Formula5;

	// formula 5.4
	private double PCO2Formula5;

	// formula 5.5
	private double NCO2Formula5;


	public double getDeltaH298() {
		return deltaH298;
	}

	public void setDeltaH298(double deltaH298) {
		this.deltaH298 = deltaH298;
	}

	public double getDeltaS298() {
		return deltaS298;
	}

	public void setDeltaS298(double deltaS298) {
		this.deltaS298 = deltaS298;
	}

	public double getDeltaCp298() {
		return deltaCp298;
	}

	public void setDeltaCp298(double deltaCp298) {
		this.deltaCp298 = deltaCp298;
	}

	public double getDeltaGT() {
		return deltaGT;
	}

	public void setDeltaGT(double deltaGT) {
		this.deltaGT = deltaGT;
	}

	public double getLgKp() {
		return lgKp;
	}

	public void setLgKp(double lgKp) {
		this.lgKp = lgKp;
	}

	public double getKp() {
		return kp;
	}

	public void setKp(double kp) {
		this.kp = kp;
	}

	public double getPi() {
		return pi;
	}

	public void setPi(double pi) {
		this.pi = pi;
	}

	public double getDeltaGTFormula5() {
		return deltaGTFormula5;
	}

	public void setDeltaGTFormula5(double deltaGTFormula5) {
		this.deltaGTFormula5 = deltaGTFormula5;
	}

	public double getLgKpFormula5() {
		return lgKpFormula5;
	}

	public void setLgKpFormula5(double lgKpFormula5) {
		this.lgKpFormula5 = lgKpFormula5;
	}

	public double getP0Formula5() {
		return P0Formula5;
	}

	public void setP0Formula5(double p0Formula5) {
		P0Formula5 = p0Formula5;
	}

	public double getPCO2Formula5() {
		return PCO2Formula5;
	}

	public void setPCO2Formula5(double PCO2Formula5) {
		this.PCO2Formula5 = PCO2Formula5;
	}

	public double getNCO2Formula5() {
		return NCO2Formula5;
	}

	public void setNCO2Formula5(double NCO2Formula5) {
		this.NCO2Formula5 = NCO2Formula5;
	}
}
