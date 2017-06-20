package chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleInsets;

import java.awt.*;

public class SingleWindowLineChart {

	/**
	 * Creates and init a chart.
	 *
	 * @param dataSet the data for the chart.
	 * @return a chart.
	 */
	public JFreeChart initChart(XYDataset dataSet) {
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

		chart.setBackgroundPaint(Color.white);

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

		return chart;
	}

}
