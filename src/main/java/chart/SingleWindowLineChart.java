package chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;

import java.awt.*;

/**
 *
 */
public class SingleWindowLineChart {



	/**
	 * Creates and init a chart.
	 *
	 * @param dataSet the data for the chart.
	 *
	 * @return a chart.
	 */
	public JFreeChart initChart(XYDataset dataSet) {
// create the chart...
		JFreeChart chart = ChartFactory.createXYLineChart(
			"Line Chart 2",
			"Время (с)",
			"Концентрация (%)",
			dataSet,
			PlotOrientation.VERTICAL,
			true, // include legend
			true, // tooltips
			false // urls
		);
// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
		chart.setBackgroundPaint(Color.white);
// get a reference to the plot for further customisation...
		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.white);
		plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
		plot.setRangeGridlinePaint(Color.gray);     // X axis color
		plot.setDomainGridlinePaint(Color.gray);   // Y axis color
		XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
		renderer.setShapesVisible(true);
		renderer.setShapesFilled(true);
// change the auto tick unit selection to integer units only...
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
// OPTIONAL CUSTOMISATION COMPLETED.
		return chart;
	}

	public XYDataset createDataSet() {
		XYSeries series1 = new XYSeries("First");
		XYSeries series2 = new XYSeries("Second");
		XYSeries series3 = new XYSeries("Third");

		for (int i = 1; i < 11; i++) {
			series1.add((double)i, i+1.5);
		}

		series2.add(1.0, 5.0);
		series2.add(2.0, 7.0);
		series2.add(3.0, 6.0);
		series2.add(4.0, 8.0);
		series2.add(5.0, 4.0);
		series2.add(6.0, 4.0);
		series2.add(7.0, 2.0);
		series2.add(8.0, 1.0);


		series3.add(3.0, 4.0);
		series3.add(4.0, 3.0);
		series3.add(5.0, 2.0);
		series3.add(6.0, 3.0);
		series3.add(7.0, 6.0);
		series3.add(8.0, 3.0);
		series3.add(9.0, 4.0);
		series3.add(10.0, 3.0);

		XYSeriesCollection dataSet = new XYSeriesCollection();
		dataSet.addSeries(series1);
		dataSet.addSeries(series2);
		dataSet.addSeries(series3);
		return dataSet;
	}

	public XYDataset createDataSet1() {
		XYSeries series1 = new XYSeries("Fe");
		XYSeries series2 = new XYSeries("Cu");
		XYSeries series3 = new XYSeries("Mg");

		for (int i = 1; i < 5; i++) {
			series1.add((double)i, i+1.5);
		}

		for (int i = 1; i < 5; i++) {
			series2.add((double)i, i+2.5);
		}

		for (int i = 1; i < 5; i++) {
			series3.add((double)i, i+3.71);
		}


		XYSeriesCollection dataSet = new XYSeriesCollection();
		dataSet.addSeries(series1);
		dataSet.addSeries(series2);
		dataSet.addSeries(series3);
		return dataSet;
	}





}
