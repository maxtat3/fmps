package stage1;

/**
 * Дополнительные входные данные для расчета задачи 1
 */
public class ExtraInputData {

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
	 * Температура расчета (град. Цельсия)
	 */
	private int temperature;

	/**
	 * Время нахождения сплава в жидком состоянии (с)
	 */
	private double time;


	public ExtraInputData(int pressureEnv, double surfaceWeldArea, double weightMoltenMetal, int temperature, double time) {
		this.pressureEnv = pressureEnv;
		this.surfaceWeldArea = surfaceWeldArea;
		this.weightMoltenMetal = weightMoltenMetal;
		this.temperature = temperature;
		this.time = time;
	}

	public ExtraInputData() {
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
