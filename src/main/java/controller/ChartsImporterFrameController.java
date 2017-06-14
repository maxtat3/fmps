package controller;

import ui.ChartsExporterFrame;

/**
 *
 */
public class ChartsImporterFrameController {

//	public void showChart(ShowChartSelector.Stage1 selector){
//		switch (selector.getName()) {
//			case ShowChartSelector.Stage1.CHART_1.getName():
//
//		}
//	}

	public void showChart(ChartsExporterFrame.Stages stage, String stageAndChartNumber){
		if (stage == ChartsExporterFrame.Stages.STAGE_1) {
			switch (stageAndChartNumber) {
				case STAGE_1_CHART_1:
					System.out.println("--- show chart 1.1");
					break;

				case STAGE_1_CHART_2:
					System.out.println("--- show chart 1.2");
					break;
			}
		} else if (stage == ChartsExporterFrame.Stages.STAGE_2) {

		} else if (stage == ChartsExporterFrame.Stages.STAGE_3) {

		} else {
			throw new UnsupportedOperationException("Mismatch stage and chart number !");
		}

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
