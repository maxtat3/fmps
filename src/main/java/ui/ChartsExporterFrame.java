package ui;

import app.ShowChartSelector;
import controller.ChartsImporterFrameController;

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

	private JFrame mainFrame = new JFrame();

	private ChartsImporterFrameController controller;


	public ChartsExporterFrame() {
		controller = new ChartsImporterFrameController();

		addComponentsToPane(mainFrame.getContentPane());
		mainFrame.setPreferredSize(new Dimension(500, 300));
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}

	private void addComponentsToPane(Container container){
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
				System.out.println("st1");
			}
		});
		jtbtnStage2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("st2");
			}
		});
		jtbtnStage3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("st3");
			}
		});

		jpSelectTask.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createEtchedBorder(EtchedBorder.RAISED), TXT_STAGE_SELECT
		));

		// chart selector
		ShowChartSelector.Stage1[] chartValues = ShowChartSelector.Stage1.values();
		String[] chartNames = new String[chartValues.length];
		for (int i = 0; i < chartValues.length; i++) {
			chartNames[i] = chartValues[i].getName();
		}
		jcmbSelectChart.setModel(new DefaultComboBoxModel<>(chartNames));
		jcmbSelectChart.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				System.out.println(jcmbSelectChart.getSelectedItem().toString());
			}
		});
		jcmbSelectChart.setSelectedIndex(0);

		JPanel jpChart = new JPanel();
		jpChart.setLayout(new GridLayout(4, 0));
		jpChart.add(jcmbSelectChart);
		jpChart.add(new JLabel("Displayed chart"));
		jpChart.add(jbtnPrint);
		jpChart.add(jbtnSaveAsImgs);

		container.add(jpSelectTask, BorderLayout.NORTH);
		container.add(jpChart, BorderLayout.CENTER);
		container.add(new JButton(TXT_CANCEL), BorderLayout.SOUTH);
	}
}
