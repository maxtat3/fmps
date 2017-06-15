package ui;

import controller.ChartsExporterFrameController;

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
public class ChartsExporterFrame {


	public static final String TXT_STAGE_SELECT = "Отображение результатов";
	public static final String TXT_STAGE_1 = "Задача 1";
	public static final String TXT_STAGE_2 = "Задача 2";
	public static final String TXT_STAGE_3 = "Задача 3";
	public static final String TXT_CANCEL = "Отмена";
	private JButton jbtnPrint = new JButton("Печать всех графиков выбранной задачи");
	private JButton jbtnSaveAsImgs = new JButton("Сохранить все графики в файл для выбранной задачи");

	private final JComboBox<String> jcmbSelectChart = new JComboBox<>();

	private Stages stage = Stages.STAGE_1;
	private JFrame mainFrame = new JFrame();

	private ChartsExporterFrameController controller;


	public ChartsExporterFrame() {
		controller = new ChartsExporterFrameController();

		addComponentsToPane(mainFrame.getContentPane());
		mainFrame.setPreferredSize(new Dimension(500, 300));
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
//				controller.showChart();
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
//				System.out.println(jcmbSelectChart.getSelectedItem().toString());
				// TODO: 12.06.17 пердполагаем что имена всех графиков уникальны для всех этапов. Только при этом условии метод будет корректно работать.
				controller.showChart(stage, jcmbSelectChart.getSelectedItem().toString());
			}
		});
		jcmbSelectChart.setSelectedIndex(0);

		JPanel jpChart = new JPanel();
		BoxLayout boxLayout = new BoxLayout(jpChart, BoxLayout.PAGE_AXIS);
		jpChart.setLayout(boxLayout);
		jpChart.add(jcmbSelectChart);
		jpChart.add(new JLabel("Displayed chart"));
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

	public enum Stages {
		STAGE_1, STAGE_2, STAGE_3
	}

}
