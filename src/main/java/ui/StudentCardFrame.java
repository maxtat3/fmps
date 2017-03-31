package ui;

import controller.StudentCardFrameController;
import stage1.elements.BaseElementStage1;
import stage1.elements.GeneralElementStage1;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 *
 */
public class StudentCardFrame implements StudentCardFrameController.StudentCardFrameCallback {

	public static final String TXT_STUDENT_CARD = "Карточка студента";
	public static final String TXT_MAIN_ELEMENTS = "Основные элементы:";
	public static final String TXT_ACCESSORY_ELEMENTS = "Дополнительные элементы:";
	public static final String TXT_USER_FIRST_NAME = "Имя: ";
	public static final String TXT_USER_MIDDLE_NAME = "Отчество: ";
	public static final String TXT_USER_LAST_NAME = "Фамилия: ";
	public static final String TXT_USER_NUM_OF_REC_BOOK = "Номер группы: ";
	public static final String TXT_STAGE_1_MAIN_PANEL_NAME = "Химический состав элементов";
	public static final String TXT_TAB_1_NAME = "Задача 1";
	public static final String TXT_TAB_1_TOOL_TIP = "Расчет процессов испарения металлов при сварке плавлением";

	private JFrame studentCardFrame = new JFrame(TXT_STUDENT_CARD);
	private StudentCardFrameController controller;

	private List<GeneralElementStage1> basicElementsStage1;
	private List<GeneralElementStage1> accessoryElementsStage1;

	private JPanel jpUserData = new JPanel();
	private JPanel jpElementsStage1 = new JPanel();
	private JPanel jpDirection = new JPanel();
	private JButton jbtnOk = new JButton("Ok");
	private JButton jbtnCancel = new JButton("Cancel");
	private JLabel jlMsg = new JLabel();

	private java.util.List<JTextField> jtfAlloyComWeights = new ArrayList<>();


	public StudentCardFrame() {
		controller = new StudentCardFrameController(studentCardFrame, this);

		studentCardFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addComponentToPane(studentCardFrame.getContentPane());
		studentCardFrame.setPreferredSize(new Dimension(500, 500));
		studentCardFrame.pack();
		studentCardFrame.setLocationRelativeTo(null);
		studentCardFrame.setVisible(true);
	}

	private void addComponentToPane(final Container contentPane) {
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


		JPanel jpRows = new JPanel();
		jpRows.setLayout(new BoxLayout(jpRows, BoxLayout.Y_AXIS));

		jpRows.add(new JLabel(TXT_MAIN_ELEMENTS));
		for (GeneralElementStage1 el : basicElementsStage1) {
			addRowsToPanel(jpRows, el);
		}

		jpRows.add(new JLabel(TXT_ACCESSORY_ELEMENTS));
		for (GeneralElementStage1 el : accessoryElementsStage1) {
			addRowsToPanel(jpRows, el);
		}


		jpElementsStage1.setLayout(new FlowLayout());
		jpElementsStage1.add(jpRows);

		jpDirection.setLayout(new FlowLayout());
		jpDirection.add(jbtnOk);
		jpDirection.add(jbtnCancel);

		jbtnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (JTextField jtf : jtfAlloyComWeights) {
					System.out.println(jtf.getName() + " : " + jtf.getText());
				}

				if (isShowWarning(controller.validateInputData(jtfLastName, StudentCardFrameController.ValidatorVariant.IS_TEXT)))
					return;
				if (isShowWarning(controller.validateInputData(jtfFirstName, StudentCardFrameController.ValidatorVariant.IS_TEXT)))
					return;
				if (isShowWarning(controller.validateInputData(jtfMiddleName, StudentCardFrameController.ValidatorVariant.IS_TEXT)))
					return;
				if (isShowWarning(controller.validateInputData(jtfNumOfRecBook, StudentCardFrameController.ValidatorVariant.IS_NUMBER)))
					return;

				for (JTextField jtf : jtfAlloyComWeights) {
					if (isShowWarning(controller.validateInputData(jtf, StudentCardFrameController.ValidatorVariant.IS_NUMBER))) return;
				}

			}
		});

		jbtnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThisWindow();
				new StartFrame();
			}
		});

		// set settings for created panels
		jpElementsStage1.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createEtchedBorder(EtchedBorder.RAISED), TXT_STAGE_1_MAIN_PANEL_NAME
		));

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab(TXT_TAB_1_NAME, null, jpElementsStage1, TXT_TAB_1_TOOL_TIP);
//		tabbedPane.addTab("Задача 2", null, jpUserData, "tooltip tab 2");
//		tabbedPane.addTab("Задача 3", null, panelTab3, "tooltip tab 3");

		// add main panel to main frame
		contentPane.add(jpUserData, BorderLayout.NORTH);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		contentPane.add(jpDirection, BorderLayout.SOUTH);
	}

	private void addRowsToPanel(JPanel jpRows, GeneralElementStage1 el) {
		JLabel jlElementName = new JLabel(el.toString() + " : ");
		JTextField jtfElementVal = new JTextField();
		jtfElementVal.setColumns(4);
		jtfElementVal.setName(el.toString());
		jtfAlloyComWeights.add(jtfElementVal);
		JLabel jlPercent = new JLabel(" %");

		JPanel jpItems = new JPanel();
		jpItems.add(jlElementName);
		jpItems.add(jtfElementVal);
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
}
