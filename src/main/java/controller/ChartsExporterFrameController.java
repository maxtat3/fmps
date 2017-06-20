package controller;

import app.ReferenceCalculationsStage1;
import chart.SingleWindowLineChart;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import stage1.elements.GeneralElementStage1;
import ui.ChartsExporterFrame;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 */
public class ChartsExporterFrameController {

//	public void showChart(ShowChartSelector.Stage1 selector){
//		switch (selector.getName()) {
//			case ShowChartSelector.Stage1.CHART_1.getName():
//
//		}
//	}

	private ReferenceCalculationsStage1 resolver;

	public ChartsExporterFrameController() {
		resolver = new ReferenceCalculationsStage1();
	}

	public JFreeChart showChart(ChartsExporterFrame.Stages stage, String stageAndChartNumber){
		if (stage == ChartsExporterFrame.Stages.STAGE_1) {
			switch (stageAndChartNumber) {
				case STAGE_1_CHART_1:
					System.out.println("--- show chart 1.1");
					HashMap<GeneralElementStage1, LinkedHashMap<Integer, Double>> data = resolver.buildChart1();
					XYSeriesCollection dataSet = prepareDataToChart(data);
					SingleWindowLineChart chart = new SingleWindowLineChart();
					return chart.initChart(dataSet);

				case STAGE_1_CHART_2:
					System.out.println("--- show chart 1.2");
					resolver.buildChart1();
					SingleWindowLineChart chart1 = new SingleWindowLineChart();
					XYDataset data1 = chart1.createDataSet1();
					return chart1.initChart(data1);
			}
		} else if (stage == ChartsExporterFrame.Stages.STAGE_2) {

		} else if (stage == ChartsExporterFrame.Stages.STAGE_3) {

		} else {
			throw new UnsupportedOperationException("Mismatch stage and chart number !");
		}

		return null;
	}

	// names of charts for all stages
	public static final String STAGE_1_CHART_1 = "st1ch1";
	public static final String STAGE_1_CHART_2 = "st1ch2";

	public static final String STAGE_2_CHART_1 = "st2 ch1";
	public static final String STAGE_2_CHART_2 = "st2 ch2";

	public static final String STAGE_3_CHART_1 = "st3  ch1";
	public static final String STAGE_3_CHART_2 = "st3  ch2";

	public String[] getNamesChartsStage1(){
		return new String[]{
			STAGE_1_CHART_1,
			STAGE_1_CHART_2,
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
	private XYSeriesCollection prepareDataToChart(HashMap<GeneralElementStage1, LinkedHashMap<Integer, Double>> data) {
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
}
