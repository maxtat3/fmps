package stage1;

/**
 * Дополнительные данные для расчета задачи 1
 */
public class ExtraInputDataStage1 {

	/**
	 * Давление окружающей среды (Па)
	 */
	private int pressureEnv;

	/**
	 * Площадь свободной поверхности сварочной ванны (см2)
	 */
	private double surfaceWeldArea;

	/**
	 * Масса расплавленного металла (гр.)
	 */
	private double weightMoltenMetal;

	/**
	 * Температура расчета (граж. Цельсия)
	 */
	private int temperature;

	/**
	 * Температура расчета (c)
	 */
	private double time;


	public ExtraInputDataStage1(int pressureEnv, double surfaceWeldArea, double weightMoltenMetal, int temperature, double time) {
		this.pressureEnv = pressureEnv;
		this.surfaceWeldArea = surfaceWeldArea;
		this.weightMoltenMetal = weightMoltenMetal;
		this.temperature = temperature;
		this.time = time;
	}

	public ExtraInputDataStage1() {
	}


	public int getPressureEnv() {
		return pressureEnv;
	}

	public void setPressureEnv(int pressureEnv) {
		this.pressureEnv = pressureEnv;
	}

	public double getSurfaceWeldArea() {
		return surfaceWeldArea;
	}

	public void setSurfaceWeldArea(double surfaceWeldArea) {
		this.surfaceWeldArea = surfaceWeldArea;
	}

	public double getWeightMoltenMetal() {
		return weightMoltenMetal;
	}

	public void setWeightMoltenMetal(double weightMoltenMetal) {
		this.weightMoltenMetal = weightMoltenMetal;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

}
