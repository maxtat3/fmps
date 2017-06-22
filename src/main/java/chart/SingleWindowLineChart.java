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
	 * @param init the init data for the chart: chart title, names of x and y axis
	 *             and data set.
	 * @return a chart.
	 * @see {@link InitData}
	 */
	public JFreeChart initChart(InitData init) {
		if (init == null) return null; // if data is not available - do not show chart
		JFreeChart chart = ChartFactory.createXYLineChart(
			init.getTitle(),
			init.getxAxisLabels(),
			init.getyAxisLabel(),
			init.getDataSet(),
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

	public class InitData {
		private final String title;
		private final String xAxisLabels;
		private final String yAxisLabel;
		private final XYDataset dataSet;

		public InitData(String title, String xAxisLabels, String yAxisLabel, XYDataset dataSet) {
			this.title = title;
			this.xAxisLabels = xAxisLabels;
			this.yAxisLabel = yAxisLabel;
			this.dataSet = dataSet;
		}

		public String getTitle() {
			return title;
		}

		public String getxAxisLabels() {
			return xAxisLabels;
		}

		public String getyAxisLabel() {
			return yAxisLabel;
		}

		public XYDataset getDataSet() {
			return dataSet;
		}
	}


}
