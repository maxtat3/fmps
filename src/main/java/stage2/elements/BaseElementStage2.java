package stage2.elements;

/**
 *
 */
public abstract class BaseElementStage2 implements GeneralElementStage2 {

	/**
	 * Процентное содержание газа в смеси, Ni (%)
	 */
	private double gasMole;


	public double getGasMole() {
		return gasMole;
	}

	public void setGasMole(double gasMole) {
		this.gasMole = gasMole;
	}
}
