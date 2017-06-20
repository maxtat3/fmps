package ui;

import controller.ChartsExporterFrameController;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


/**
 * UI для выбора различнеых вариантов сохранения графиков - файл или печать.
 */
public class ChartsExporterFrame extends ApplicationFrame{

	/**
	 * Positions of chart in chart panel.
	 */
	private static final int CHART_POSITION = 1;

	/**
	 * Dimensions of this frame.
	 */
	private final Dimension frameDim = new Dimension(950, 700);

	/**
	 * Dimensions of chart placed in {@link #jpChart} panel.
	 * This dimensions affected to size chart inside container!
	 */
	private final Dimension chartDim = new Dimension(700, 700);
	public static final String TXT_STAGE_SELECT = "Отображение результатов";
	public static final String TXT_STAGE_1 = "Задача 1";
	public static final String TXT_STAGE_2 = "Задача 2";
	public static final String TXT_STAGE_3 = "Задача 3";
	public static final String TXT_CANCEL = "Отмена";
	private JButton jbtnPrint = new JButton("Печать всех графиков выбранной задачи");
	private JButton jbtnSaveAsImgs = new JButton("Сохранить все графики в файл для выбранной задачи");

	private final JComboBox<String> jcmbSelectChart = new JComboBox<>();

	private Stages stage = Stages.STAGE_1;
	private JPanel jpChart = new JPanel();
	private JFrame mainFrame = new JFrame("Графические результаты расчетов");

	private ChartsExporterFrameController controller;


	public ChartsExporterFrame() {
		super("");
		controller = new ChartsExporterFrameController();

		addComponentsToPane(mainFrame.getContentPane());
		mainFrame.setPreferredSize(frameDim);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}

	private void addComponentsToPane(final Container container){
		// stage selector
		JPanel jpSelectTask = new JPanel(new FlowLayout(FlowLayout.CENTER));
		ButtonGroup btnGr = new ButtonGroup();
		JToggleButton jtbtnStage1 = new JToggleButton(TXT_STAGE_1);
		JToggleButton jtbtnStage2 = new JToggleButton(TXT_STAGE_2);
		JToggleButton jtbtnStage3 = new JToggleButton(TXT_STAGE_3);
		btnGr.add(jtbtnStage1);
		btnGr.add(jtbtnStage2);
		btnGr.add(jtbtnStage3);
		jpSelectTask.add(jtbtnStage1);
		jpSelectTask.add(jtbtnStage2);
		jpSelectTask.add(jtbtnStage3);
		jtbtnStage1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stage = Stages.STAGE_1;
				updateComboBoxModel(stage);
			}
		});
		jtbtnStage2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stage = Stages.STAGE_2;
				updateComboBoxModel(stage);
			}
		});
		jtbtnStage3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stage = Stages.STAGE_3;
				updateComboBoxModel(stage);
			}
		});

		jpSelectTask.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createEtchedBorder(EtchedBorder.RAISED), TXT_STAGE_SELECT
		));

		// chart selector
		updateComboBoxModel(Stages.STAGE_1);
		jcmbSelectChart.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO: 12.06.17 пердполагаем что имена всех графиков уникальны для всех этапов. Только при этом условии метод будет корректно работать.
				displayChart(stage, jcmbSelectChart.getSelectedItem().toString());
			}
		});
		jcmbSelectChart.setSelectedIndex(0);

		ChartPanel chartPanel = new ChartPanel(controller.showChart(stage, jcmbSelectChart.getSelectedItem().toString()));
		chartPanel.setPreferredSize(chartDim);

		BoxLayout boxLayout = new BoxLayout(jpChart, BoxLayout.PAGE_AXIS);
		jpChart.setLayout(boxLayout);
		jpChart.add(jcmbSelectChart);
		jpChart.add(chartPanel);
		jpChart.add(jbtnPrint);
		jpChart.add(jbtnSaveAsImgs);

		container.add(jpSelectTask, BorderLayout.NORTH);
		container.add(jpChart, BorderLayout.CENTER);
		container.add(new JButton(TXT_CANCEL), BorderLayout.SOUTH);
	}

	public void updateComboBoxModel(Stages stages){
		if (jcmbSelectChart != null) {
			switch (stages) {
				case STAGE_1:
					jcmbSelectChart.setModel(new DefaultComboBoxModel<>(controller.getNamesChartsStage1()));
					break;
				case STAGE_2:
					jcmbSelectChart.setModel(new DefaultComboBoxModel<>(controller.getNamesChartsStage2()));
					break;
				case STAGE_3:
					jcmbSelectChart.setModel(new DefaultComboBoxModel<>(controller.getNamesChartsStage3()));
					break;
			}
		}
	}


	// TODO: 20.06.17 may be fix 2 calls this method when jcmbSelectChart handles
	private void displayChart(Stages stage, String chartNum){
		JFreeChart jfChart = controller.showChart(stage, chartNum);

		ChartPanel chp = new ChartPanel(jfChart);
		chp.setPreferredSize(chartDim);

		jpChart.remove(CHART_POSITION);
		jpChart.add(chp, CHART_POSITION);

		mainFrame.revalidate();
	}

	public enum Stages {
		STAGE_1, STAGE_2, STAGE_3
	}

}
