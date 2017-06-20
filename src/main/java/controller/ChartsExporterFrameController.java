package controller;

import app.ReferenceCalculationsStage1;
import chart.ChartData;
import chart.SingleWindowLineChart;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import stage1.elements.GeneralElementStage1;
import ui.ChartsExporterFrame;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 */
public class ChartsExporterFrameController {

	private ReferenceCalculationsStage1 resolver;
	private ChartData chartData;

	public ChartsExporterFrameController() {
		resolver = new ReferenceCalculationsStage1();
		chartData = resolver.buildChartsXTemperYValues();
		// copied collection for chart 4 from reference
		chartData.setChart4Formula2p1Data(resolver.buildChart4XTemperYValue().getChart4Formula2p1Data());
	}

	// TODO: 20.06.17 may be simplify code in case block, for example ...
//	    return new SingleWindowLineChart().initChart(
//		    prepareDataToChart(chartData.getChart1Formula3Data())
//		);
	public JFreeChart showChart(ChartsExporterFrame.Stages stage, String stageAndChartNumber){
		if (stage == ChartsExporterFrame.Stages.STAGE_1) {
			switch (stageAndChartNumber) {
				case STAGE_1_CHART_1:
					XYSeriesCollection dataSet = prepareDataToChart(chartData.getChart1Formula3Data());
					SingleWindowLineChart chart = new SingleWindowLineChart();
					return chart.initChart(dataSet);

				case STAGE_1_CHART_2:
					XYSeriesCollection dataSet1 = prepareDataToChart(chartData.getChart2Formula4Data());
					SingleWindowLineChart chart1 = new SingleWindowLineChart();
					return chart1.initChart(dataSet1);

				case STAGE_1_CHART_3:
					XYSeriesCollection dataSet2 = prepareDataToChart(chartData.getChart3Formula7Data());
					SingleWindowLineChart chart2 = new SingleWindowLineChart();
					return chart2.initChart(dataSet2);

				case STAGE_1_CHART_4:
					XYSeriesCollection dataSet3 = prepareDataToChart(chartData.getChart4Formula2p1Data());
					SingleWindowLineChart chart3 = new SingleWindowLineChart();
					return chart3.initChart(dataSet3);

				case STAGE_1_CHART_5:
					//...
					break;
			}
		} else if (stage == ChartsExporterFrame.Stages.STAGE_2) {

		} else if (stage == ChartsExporterFrame.Stages.STAGE_3) {

		} else {
			throw new UnsupportedOperationException("Mismatch stage and chart number !");
		}

		return null;
	}

	// names of charts for all stages
	public static final String STAGE_1_CHART_1 = "Зависимость паров чистых компонент от температуры";
	public static final String STAGE_1_CHART_2 = "Зависимость парциального давления компонент от температуры";
	public static final String STAGE_1_CHART_3 = "Влияние температуры на весовой состав сплава";
	public static final String STAGE_1_CHART_4 = "Влияние температуры на энтальпию сплава";
	public static final String STAGE_1_CHART_5 = "Изменение концентрации легирующих элементов сплава за счет испарения";

	public static final String STAGE_2_CHART_1 = "st2 ch1";
	public static final String STAGE_2_CHART_2 = "st2 ch2";

	public static final String STAGE_3_CHART_1 = "st3  ch1";
	public static final String STAGE_3_CHART_2 = "st3  ch2";

	public String[] getNamesChartsStage1(){
		return new String[]{
			STAGE_1_CHART_1,
			STAGE_1_CHART_2,
			STAGE_1_CHART_3,
			STAGE_1_CHART_4,
			STAGE_1_CHART_5
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
