package controller;

import chart.ChartData;
import chart.SingleWindowLineChart;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import stage1.ReferenceCalc;
import stage1.elements.GeneralElementStage1;
import ui.ChartsExporterFrame;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 */
public class ChartsExporterFrameController {

	private ReferenceCalc resolver;
	private ChartData chartData;

	public ChartsExporterFrameController() {
		resolver = new ReferenceCalc();
		chartData = resolver.buildChartsXTemperYValues();
		// copied collection for chart 4 from reference
		chartData.setChart4Formula2p1Data(resolver.buildChart4XTemperYValue().getChart4Formula2p1Data());
		chartData.setChart5Data(resolver.buildChart5XTimeYValues().getChart5Data());
	}

	// TODO: 20.06.17 may be simplify code in case block, for example ...
//	    return new SingleWindowLineChart().initChart(
//		    prepareDataToChart(chartData.getChart1Formula3Data())
//		);
	public JFreeChart showChart(ChartsExporterFrame.Stages stage, String chartNum){
		XYSeriesCollection dataSet = null;
		SingleWindowLineChart.InitData initData = null;
		if (stage == ChartsExporterFrame.Stages.STAGE_1) {
			if (chartNum.equals(ChartsInStage1.CHART_1.getNameOfChart())) {
				dataSet = prepareDataToChart(chartData.getChart1Formula3Data());
				initData = new SingleWindowLineChart().new InitData(
					ChartsInStage1.CHART_1.getNameOfChart(),
					ChartsInStage1.CHART_1.getxAxisLabel(),
					ChartsInStage1.CHART_1.getyAxisLabel(),
					dataSet
				);

			} else if (chartNum.equals(ChartsInStage1.CHART_2.getNameOfChart())) {
				dataSet = prepareDataToChart(chartData.getChart2Formula4Data());
				initData = new SingleWindowLineChart().new InitData(
					ChartsInStage1.CHART_2.getNameOfChart(),
					ChartsInStage1.CHART_2.getxAxisLabel(),
					ChartsInStage1.CHART_2.getyAxisLabel(),
					dataSet
				);

			} else if (chartNum.equals(ChartsInStage1.CHART_3.getNameOfChart())) {
				dataSet = prepareDataToChart(chartData.getChart3Formula7Data());
				initData = new SingleWindowLineChart().new InitData(
					ChartsInStage1.CHART_3.getNameOfChart(),
					ChartsInStage1.CHART_3.getxAxisLabel(),
					ChartsInStage1.CHART_3.getyAxisLabel(),
					dataSet
				);

			} else if (chartNum.equals(ChartsInStage1.CHART_4.getNameOfChart())) {
				dataSet = prepareDataToChart(chartData.getChart4Formula2p1Data());
				initData = new SingleWindowLineChart().new InitData(
					ChartsInStage1.CHART_4.getNameOfChart(),
					ChartsInStage1.CHART_4.getxAxisLabel(),
					ChartsInStage1.CHART_4.getyAxisLabel(),
					dataSet
				);

			} else if (chartNum.equals(ChartsInStage1.CHART_5.getNameOfChart())) {
				dataSet = prepareDataToChartXDoubleYDouble(chartData.getChart5Data());
				initData = new SingleWindowLineChart().new InitData(
					ChartsInStage1.CHART_5.getNameOfChart(),
					ChartsInStage1.CHART_5.getxAxisLabel(),
					ChartsInStage1.CHART_5.getyAxisLabel(),
					dataSet
				);
			}
			return new SingleWindowLineChart().initChart(initData);
		} else if (stage == ChartsExporterFrame.Stages.STAGE_2) {

		} else if (stage == ChartsExporterFrame.Stages.STAGE_3) {

		} else {
			throw new UnsupportedOperationException("Mismatch stage and chart number !");
		}

		return null;
	}

	public static final String STAGE_2_CHART_1 = "st2 ch1";
	public static final String STAGE_2_CHART_2 = "st2 ch2";

	public static final String STAGE_3_CHART_1 = "st3  ch1";
	public static final String STAGE_3_CHART_2 = "st3  ch2";

	public enum ChartsInStage1 {
		CHART_1("Зависимость паров чистых компонент от температуры", Axis.TEMPERATURE, Axis.LG_P),
		CHART_2("Зависимость парциального давления компонент от температуры", Axis.TEMPERATURE, Axis.LG_P_P),
		CHART_3("Влияние температуры на весовой состав сплава", Axis.TEMPERATURE, Axis.CONCENTRATION),
		CHART_4("Влияние температуры на энтальпию сплава", Axis.TEMPERATURE, Axis.H),
		CHART_5("Изменение концентрации легирующих элементов сплава за счет испарения", Axis.TEMPERATURE, Axis.CONCENTRATION);

		private static class Axis {
			private static final String LG_P = "lg(P), (Па)";
			private static final String LG_P_P = "lg(Pp), (Па)";
			private static final String CONCENTRATION = "Концентрация, (%)";
			private static final String H = "H, (кДж/моль)";
			private static final String TEMPERATURE = "Температура (°C)";
		}

		private String nameOfChart;
		private String xAxisLabel;
		private String yAxisLabel;

		ChartsInStage1(String nameOfChart, String xAxisLabel, String yAxisLabel) {
			this.nameOfChart = nameOfChart;
			this.xAxisLabel = xAxisLabel;
			this.yAxisLabel = yAxisLabel;
		}

		public String getNameOfChart() {
			return nameOfChart;
		}

		public String getxAxisLabel() {
			return xAxisLabel;
		}

		public String getyAxisLabel() {
			return yAxisLabel;
		}
	}

	public String[] getNamesChartsStage1(){
		return new String[]{
			ChartsInStage1.CHART_1.getNameOfChart(),
			ChartsInStage1.CHART_2.getNameOfChart(),
			ChartsInStage1.CHART_3.getNameOfChart(),
			ChartsInStage1.CHART_4.getNameOfChart(),
			ChartsInStage1.CHART_5.getNameOfChart(),
		};
	}

	public String[] getNamesChartsStage2(){
		return new String[]{
			STAGE_2_CHART_1,
			STAGE_2_CHART_2
		};
	}

	public String[] getNamesChartsStage3(){
		return new String[]{
			STAGE_3_CHART_1,
			STAGE_3_CHART_2
		};
	}

	/**
	 * Bind calculated data user task elements to chart.
	 * Collection may be contained one or more lines (elements).
	 * Each line composed as many points.
	 * Each point represents x(int) and y(double) coordinates.
	 *
	 * @param data calculated data: data in map. Key represents as elements which line is build in chart, value represents
	 *             point. Point contain x and y coordinates.
	 * @return prepared collections curves for elements.
	 */
	private XYSeriesCollection prepareDataToChart(Map<GeneralElementStage1, LinkedHashMap<Integer, Double>> data) {
		XYSeriesCollection dataSet = new XYSeriesCollection();

		for (Map.Entry<GeneralElementStage1, LinkedHashMap<Integer, Double>> entry : data.entrySet()) {
			GeneralElementStage1 el = entry.getKey();
			XYSeries xySeries = new XYSeries(el.toString());

			for (Map.Entry<Integer, Double> pData : entry.getValue().entrySet()) {
				xySeries.add(pData.getKey(), pData.getValue());
			}
			dataSet.addSeries(xySeries);
		}

		return dataSet;
	}

	// TODO: 27.06.17 merge two methods to one - in LinkedHashMap<Double, Double> embedded list change two Double types to java.lang.Number
	private XYSeriesCollection prepareDataToChartXDoubleYDouble(Map<GeneralElementStage1, LinkedHashMap<Double, Double>> data) {
		XYSeriesCollection dataSet = new XYSeriesCollection();

		for (Map.Entry<GeneralElementStage1, LinkedHashMap<Double, Double>> entry : data.entrySet()) {
			GeneralElementStage1 el = entry.getKey();
			XYSeries xySeries = new XYSeries(el.toString());

			for (Map.Entry<Double, Double> pData : entry.getValue().entrySet()) {
				xySeries.add(pData.getKey(), pData.getValue());
			}
			dataSet.addSeries(xySeries);
		}

		return dataSet;
	}

	private XYSeriesCollection prepareDataToChart(LinkedHashMap<Integer, Double> data) {
		XYSeriesCollection dataSet = new XYSeriesCollection();
		XYSeries xySeries = new XYSeries("");

		for (Map.Entry<Integer, Double> pData : data.entrySet()) {
			xySeries.add(pData.getKey(), pData.getValue());
		}
		dataSet.addSeries(xySeries);

		return dataSet;
	}
}
