package chart;

import stage1.elements.GeneralElementStage1;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class ChartData {

	/**
	 * Data collection for chart 1. It perform calculated
	 * from {@link stage1.calc.Calc#findVaporPressureOfPureCompsF3(List, int)} formula.
	 */
	private Map<GeneralElementStage1, LinkedHashMap<Integer, Double>> chart1Formula3Data = new LinkedHashMap<>();

	/**
	 * Data collection for chart 2. It perform calculated
	 * from {@link stage1.calc.Calc#findPartialPressureCompsOverAlloyF4(List, int)} formula.
	 */
	private Map<GeneralElementStage1, LinkedHashMap<Integer, Double>> chart2Formula4Data = new LinkedHashMap<>();

	/**
	 * Data collection for chart 3. It perform calculated
	 * from {@link stage1.calc.Calc#findWeightFractionEachElemInVaporF7(List, int)} formula.
	 */
	private Map<GeneralElementStage1, LinkedHashMap<Integer, Double>> chart3Formula7Data = new LinkedHashMap<>();

	/**
	 * Data collection for chart 4. It perform calculated
	 * from {@link stage1.calc.Calc#findEnthalpyLiquidAlloyF2p1(List, int, int)} formula.
	 */
	private LinkedHashMap<Integer, Double> chart4Formula2p1Data = new LinkedHashMap<>();

	/**
	 * Data collection for chart 5. It perform calculated
	 */
	private Map<GeneralElementStage1, LinkedHashMap<Double, Double>> chart5Data = new LinkedHashMap<>();



	public Map<GeneralElementStage1, LinkedHashMap<Integer, Double>> getChart1Formula3Data() {
		return chart1Formula3Data;
	}

	public void setChart1Formula3Data(Map<GeneralElementStage1, LinkedHashMap<Integer, Double>> chart1Formula3Data) {
		this.chart1Formula3Data = chart1Formula3Data;
	}

	public Map<GeneralElementStage1, LinkedHashMap<Integer, Double>> getChart2Formula4Data() {
		return chart2Formula4Data;
	}

	public void setChart2Formula4Data(Map<GeneralElementStage1, LinkedHashMap<Integer, Double>> chart2Formula4Data) {
		this.chart2Formula4Data = chart2Formula4Data;
	}

	public Map<GeneralElementStage1, LinkedHashMap<Integer, Double>> getChart3Formula7Data() {
		return chart3Formula7Data;
	}

	public void setChart3Formula7Data(Map<GeneralElementStage1, LinkedHashMap<Integer, Double>> chart3Formula7Data) {
		this.chart3Formula7Data = chart3Formula7Data;
	}

	public LinkedHashMap<Integer, Double> getChart4Formula2p1Data() {
		return chart4Formula2p1Data;
	}

	public void setChart4Formula2p1Data(LinkedHashMap<Integer, Double> chart4Formula2p1Data) {
		this.chart4Formula2p1Data = chart4Formula2p1Data;
	}

	public Map<GeneralElementStage1, LinkedHashMap<Double, Double>> getChart5Data() {
		return chart5Data;
	}

	public void setChart5Data(Map<GeneralElementStage1, LinkedHashMap<Double, Double>> chart5Data) {
		this.chart5Data = chart5Data;
	}
}
