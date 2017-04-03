package ui;

import app.Utils;
import controller.StudentCardFrameController;
import stage1.elements.GeneralElementStage1;
import stage2.elements.GeneralElementStage2;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class StudentCardFrame implements StudentCardFrameController.StudentCardFrameCallback {

	public static final String TXT_STUDENT_CARD = "Карточка студента";
	public static final String TXT_MAIN_ELEMENTS = "Основные элементы:";
	public static final String TXT_ACCESSORY_ELEMENTS = "Дополнительные элементы:";
	public static final String TXT_GASES_STAGE_2 = "Для защиты сварочной ванны используется смесь газов:";
	public static final String TXT_USER_FIRST_NAME = "Имя: ";
	public static final String TXT_USER_MIDDLE_NAME = "Отчество: ";
	public static final String TXT_USER_LAST_NAME = "Фамилия: ";
	public static final String TXT_USER_NUM_OF_REC_BOOK = "Номер группы: ";
	public static final String TXT_TABS_PANEL_NAME = "Входные данные";
	public static final String TXT_TAB_1_NAME = "Задача 1";
	public static final String TXT_TAB_2_NAME = "Задача 2";
	public static final String TXT_TAB_1_TOOL_TIP = "Расчет процессов испарения металлов при сварке плавлением";
	public static final String TXT_TAB_2_TOOL_TIP = "Расчет химического состава активной защитной газовой смеси";
	public static final String TXT_PRESSURE_ENV = "Давление окружающей среды (Па): ";
	public static final String TXT_SURFACE_WELD_AREA = "Площадь свободной поверхности сварочной ванны (см2): ";
	public static final String TXT_WEIGHT_MOLTEN_METAL = "Масса расплавленного металла (гр.): ";
	public static final String TXT_TEMPERATURE = "Температура расчета (град. Цельсия °C): ";
	public static final String TXT_TIME = "Время нахождения сплава в жидком состоянии (c): ";

	private JFrame studentCardFrame = new JFrame(TXT_STUDENT_CARD);
	private StudentCardFrameController controller;

	private List<GeneralElementStage1> basicElementsStage1;
	private List<GeneralElementStage1> accessoryElementsStage1;
	private List<GeneralElementStage2> gasesStage2;

	private JPanel jpUserData = new JPanel();
	private JPanel jpInputDataStage1 = new JPanel();
	private JPanel jpInputDataStage2 = new JPanel();
	private JPanel jpDirection = new JPanel();
	private JButton jbtnOk = new JButton("Ok");
	private JButton jbtnCancel = new JButton("Cancel");
	private JLabel jlMsg = new JLabel();

	/**
	 * Added {@link JTextField} in this list used for validate input data only for all stages !
	 */
	private java.util.List<JTextField> jtfList = new ArrayList<>();


	public StudentCardFrame() {
		controller = new StudentCardFrameController(studentCardFrame, this);

		studentCardFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addComponentToPane(studentCardFrame.getContentPane());
		studentCardFrame.setPreferredSize(new Dimension(500, 750));
		studentCardFrame.pack();
		studentCardFrame.setLocationRelativeTo(null);
		studentCardFrame.setVisible(true);
	}

	private void addComponentToPane(final Container contentPane) {
		// User data
		JPanel jpMsg = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel jpFirstName = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel jpMiddleName = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel jpLastName = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel jpNumOfRecBook = new JPanel(new FlowLayout(FlowLayout.LEFT));

		final JTextField jtfFirstName = new JTextField(10); // Имя
		final JTextField jtfMiddleName = new JTextField(12); // Отчество
		final JTextField jtfLastName = new JTextField(12); // Фамилия
		final JTextField jtfNumOfRecBook = new JTextField(10);

		jtfFirstName.setName(TXT_USER_FIRST_NAME);
		jtfMiddleName.setName(TXT_USER_MIDDLE_NAME);
		jtfLastName.setName(TXT_USER_LAST_NAME);
		jtfNumOfRecBook.setName(TXT_USER_NUM_OF_REC_BOOK);

		jpMsg.add(jlMsg);
		jpFirstName.add(new JLabel(TXT_USER_FIRST_NAME));
		jpFirstName.add(jtfFirstName);
		jpMiddleName.add(new JLabel(TXT_USER_MIDDLE_NAME));
		jpMiddleName.add(jtfMiddleName);
		jpLastName.add(new JLabel(TXT_USER_LAST_NAME));
		jpLastName.add(jtfLastName);
		jpNumOfRecBook.add(new JLabel(TXT_USER_NUM_OF_REC_BOOK));
		jpNumOfRecBook.add(jtfNumOfRecBook);

		jpUserData.setLayout(new BoxLayout(jpUserData, BoxLayout.Y_AXIS));
		jpUserData.add(jpMsg);
		jpUserData.add(jpLastName);
		jpUserData.add(jpMiddleName);
		jpUserData.add(jpFirstName);
		jpUserData.add(jpNumOfRecBook);


		// Elements for stage 1 (tab 1)
		JPanel jpRows = new JPanel();
		jpRows.setLayout(new BoxLayout(jpRows, BoxLayout.Y_AXIS));

		jpRows.add(new JLabel(TXT_MAIN_ELEMENTS));
		for (GeneralElementStage1 el : basicElementsStage1) {
			addRowsInputDataToPanel(jpRows, jtfList, el);
		}

		jpRows.add(new JLabel(TXT_ACCESSORY_ELEMENTS));
		for (GeneralElementStage1 el : accessoryElementsStage1) {
			addRowsInputDataToPanel(jpRows, jtfList, el);
		}

		// Extra input data for stage 1 (tab 1)
		JPanel jpPressureEnv = new JPanel();
		JPanel jpSurfaceWeldArea = new JPanel();
		JPanel jpWeightMoltenMetal = new JPanel();
		JPanel jpTemperature = new JPanel();
		JPanel jpTime = new JPanel();

		final JTextField jtfPressureEnv = new JTextField(4);
		final JTextField jtfSurfaceWeldArea = new JTextField(4);
		final JTextField jtfWeightMoltenMetal = new JTextField(4);
		final JTextField jtfTemperature = new JTextField(4);
		final JTextField jtfTime = new JTextField(4);

		jtfPressureEnv.setName(TXT_PRESSURE_ENV);
		jtfSurfaceWeldArea.setName(TXT_SURFACE_WELD_AREA);
		jtfWeightMoltenMetal.setName(TXT_WEIGHT_MOLTEN_METAL);
		jtfTemperature.setName(TXT_TEMPERATURE);
		jtfTime.setName(TXT_TIME);

		jpPressureEnv.add(new JLabel(TXT_PRESSURE_ENV));
		jpPressureEnv.add(jtfPressureEnv);
		jpSurfaceWeldArea.add(new JLabel(TXT_SURFACE_WELD_AREA));
		jpSurfaceWeldArea.add(jtfSurfaceWeldArea);
		jpWeightMoltenMetal.add(new JLabel(TXT_WEIGHT_MOLTEN_METAL));
		jpWeightMoltenMetal.add(jtfWeightMoltenMetal);
		jpTemperature.add(new JLabel(TXT_TEMPERATURE));
		jpTemperature.add(jtfTemperature);
		jpTime.add(new JLabel(TXT_TIME));
		jpTime.add(jtfTime);

		JPanel jpExtraInputData = new JPanel();
		jpExtraInputData.setLayout(new BoxLayout(jpExtraInputData, BoxLayout.Y_AXIS));
		jpExtraInputData.add(jpPressureEnv);
		jpExtraInputData.add(jpSurfaceWeldArea);
		jpExtraInputData.add(jpWeightMoltenMetal);
		jpExtraInputData.add(jpTemperature);
		jpExtraInputData.add(jpTime);

		jpInputDataStage1.setLayout(new FlowLayout());
		jpInputDataStage1.add(jpRows);
		jpInputDataStage1.add(jpExtraInputData);
		jpInputDataStage1.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createEtchedBorder(EtchedBorder.RAISED), TXT_TABS_PANEL_NAME
		));


		// Elements (gases) for stage 2 (tab 2)
		JPanel jpRowsStage2 = new JPanel();
		jpRowsStage2.setLayout(new BoxLayout(jpRowsStage2, BoxLayout.Y_AXIS));

		jpRowsStage2.add(new JLabel(TXT_GASES_STAGE_2));
		for (GeneralElementStage2 el : gasesStage2) {
			addRowsInputDataToPanelStage2(jpRowsStage2, jtfList, el);
		}

		// Extra input data for stage 2 (tab 2)
		JPanel jpTemperatureStage2 = new JPanel();

		final JTextField jtfTemperatureStage2 = new JTextField(4);
		jtfTemperatureStage2.setName(TXT_TEMPERATURE);

		jpTemperatureStage2.add(new JLabel(TXT_TEMPERATURE));
		jpTemperatureStage2.add(jtfTemperatureStage2);

		JPanel jpExtraInputDataStage2 = new JPanel();
		jpExtraInputDataStage2.setLayout(new BoxLayout(jpExtraInputDataStage2, BoxLayout.Y_AXIS));
		jpExtraInputDataStage2.add(jpTemperatureStage2);

		jpInputDataStage2.setLayout(new FlowLayout());
		jpInputDataStage2.add(jpRowsStage2);
		jpInputDataStage2.add(jpExtraInputDataStage2);
		jpInputDataStage2.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createEtchedBorder(EtchedBorder.RAISED), TXT_TABS_PANEL_NAME
		));


		// Directions - buttons Ok and Cancel
		jpDirection.setLayout(new FlowLayout());
		jpDirection.add(jbtnOk);
		jpDirection.add(jbtnCancel);

		// Add listeners to directions buttons
		jbtnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Set default values [0] in elements, gases fields
				for (JTextField jtf : jtfList) {
					if (Utils.isEmpty(jtf.getText())) jtf.setText("0");
				}

				// Validations user input data
				if (isShowWarning(controller.validateInputData(jtfLastName, StudentCardFrameController.ValidatorVariant.IS_TEXT)))
					return;
				if (isShowWarning(controller.validateInputData(jtfFirstName, StudentCardFrameController.ValidatorVariant.IS_TEXT)))
					return;
				if (isShowWarning(controller.validateInputData(jtfMiddleName, StudentCardFrameController.ValidatorVariant.IS_TEXT)))
					return;
				if (isShowWarning(controller.validateInputData(jtfNumOfRecBook, StudentCardFrameController.ValidatorVariant.IS_NUMBER)))
					return;

				// Validations input fields for all stages
				for (JTextField jtf : jtfList) {
					if (isShowWarning(controller.validateInputData(jtf, StudentCardFrameController.ValidatorVariant.IS_NUMBER))) return;
				}

				// Validations extra input fields for stage 1
				if (isShowWarning(controller.validateInputData(jtfPressureEnv, StudentCardFrameController.ValidatorVariant.IS_NUMBER)))
					return;
				if (isShowWarning(controller.validateInputData(jtfSurfaceWeldArea, StudentCardFrameController.ValidatorVariant.IS_NUMBER)))
					return;
				if (isShowWarning(controller.validateInputData(jtfWeightMoltenMetal, StudentCardFrameController.ValidatorVariant.IS_NUMBER)))
					return;
				if (isShowWarning(controller.validateInputData(jtfTemperature, StudentCardFrameController.ValidatorVariant.IS_NUMBER)))
					return;
				if (isShowWarning(controller.validateInputData(jtfTime, StudentCardFrameController.ValidatorVariant.IS_NUMBER)))
					return;

				// Validations extra input fields for stage 2
				if (isShowWarning(controller.validateInputData(jtfTemperatureStage2, StudentCardFrameController.ValidatorVariant.IS_NUMBER)))
					return;

				System.out.println("If all input data is correct - start tasks frame");
			}
		});

		jbtnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThisWindow();
				new StartFrame();
			}
		});


		// Forming tabs for each stage
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab(TXT_TAB_1_NAME, null, jpInputDataStage1, TXT_TAB_1_TOOL_TIP);
		tabbedPane.addTab(TXT_TAB_2_NAME, null, jpInputDataStage2, TXT_TAB_2_TOOL_TIP);
//		tabbedPane.addTab("Задача 3", null, panelTab3, "tooltip tab 3");

		// add main panel to main frame
		contentPane.add(jpUserData, BorderLayout.NORTH);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		contentPane.add(jpDirection, BorderLayout.SOUTH);
	}

	private void addRowsInputDataToPanel(JPanel jpRows, List<JTextField> jtfList, GeneralElementStage1 el) {
		JLabel jlElName = new JLabel(el.toString() + " : ");
		JTextField jtfElVal = new JTextField();
		jtfElVal.setColumns(4);
		jtfElVal.setName(el.toString());
		jtfList.add(jtfElVal);
		JLabel jlPercent = new JLabel(" %");

		JPanel jpItems = new JPanel();
		jpItems.add(jlElName);
		jpItems.add(jtfElVal);
		jpItems.add(jlPercent);

		jpRows.add(jpItems);
	}

	private void addRowsInputDataToPanelStage2(JPanel jpRows, List<JTextField> jtfList, GeneralElementStage2 el) {
		JLabel jlElName = new JLabel(el.toString() + " : ");
		JTextField jtfElVal = new JTextField();
		jtfElVal.setColumns(4);
		jtfElVal.setName(el.toString());
		jtfList.add(jtfElVal);
		JLabel jlPercent = new JLabel(" %");

		JPanel jpItems = new JPanel();
		jpItems.add(jlElName);
		jpItems.add(jtfElVal);
		jpItems.add(jlPercent);

		jpRows.add(jpItems);
	}

	/**
	 * Check is showed warning massage in this frame.
	 *
	 * @param text warning massage
	 * @return <tt>true</tt> if massage will be shown
	 */
	private boolean isShowWarning(String text) {
		if (!text.equals("0")) {
			jlMsg.setText(text);
			studentCardFrame.pack();
			return true;
		}
		return false;
	}

	private void closeThisWindow() {
		studentCardFrame.setVisible(false);
		studentCardFrame.dispose();
	}

	@Override
	public void receiveAllBasicElementsStage1(List<GeneralElementStage1> elements) {
		basicElementsStage1 = elements;
	}

	@Override
	public void receiveAllAccessoryElementsStage1(List<GeneralElementStage1> elements) {
		accessoryElementsStage1 = elements;
	}

	@Override
	public void receiveAllGasesStage2(List<GeneralElementStage2> gases) {
		gasesStage2 = gases;
	}
}
