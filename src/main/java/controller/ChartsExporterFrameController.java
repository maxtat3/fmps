package controller;

import app.ReferenceCalculationsStage1;
import chart.SingleWindowLineChart;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import ui.ChartsExporterFrame;

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
					resolver.buildChart1();
					SingleWindowLineChart chart = new SingleWindowLineChart();
					XYDataset data = chart.createDataSet();
					return chart.initChart(data);

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
}
