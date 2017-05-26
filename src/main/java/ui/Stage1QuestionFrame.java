package ui;

import controller.InputDataController;
import controller.Stage1QuestionFrameController;
import domain.User;
import stage1.elements.GeneralElementStage1;

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
public class Stage1QuestionFrame extends JFrame {

	private JPanel jpMain = new JPanel();
	private JPanel jpTitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
	private JPanel jpQuestion1 = new JPanel();
	private JPanel jpQuestion2 = new JPanel();
	private JPanel jpQuestion3 = new JPanel();
	private JPanel jpQuestion4 = new JPanel();
	private JPanel jpQuestion5 = new JPanel();
	private JPanel jpQuestion6 = new JPanel();
	private JPanel jpQuestion7 = new JPanel();
	private JPanel jpQuestion8 = new JPanel();
	private JPanel jpQuestion9 = new JPanel();
	private JPanel jpStatusAndDirection = new JPanel();

	private JLabel jlQuestion1 = new JLabel("2 + 3 = ");
	private JTextField jtfAnswer1 = new JTextField("5");
	private JLabel jlQuestion2 = new JLabel("5 - 1 = ");
	private JTextField jtfAnswer2 = new JTextField("    ");
	private JLabel jlQuestion3 = new JLabel("11 - 7 = ");
	private JTextField jtfAnswer3 = new JTextField("    ");
	private JLabel jlQuestion4 = new JLabel("15 - 10 = ");
	private JTextField jtfAnswer4 = new JTextField("    ");
	private JLabel jlQuestion5 = new JLabel("3 * 8 = ");
	private JTextField jtfAnswer5 = new JTextField("    ");
	// Elements for question 2
	// 2.1 Расчет энтальпии жидкого сплава (кДж/моль) Enthalpy liquid alloy
	private JLabel jlEntLqAll = new JLabel("<html>Расчет энтальпии жидкого сплава H<sub>L</sub><sup>0</sup>: </html>");
	private JTextField jtfEntLqAll = new JTextField();
	private JLabel jlEntLqAllUnits = new JLabel(" кДж/моль");
	// 2.2 Расчет энтальпии испарения (кДж/моль) Enthalpy vaporization
	private JLabel jlEntVaporization = new JLabel("<html>Расчет энтальпии испарения &Delta;H<sub>кип</sub>: </html>");
	private JTextField jtfEntVaporization = new JTextField();
	private JLabel jlEntVaporizationUnits = new JLabel(" кДж/моль");
	// 2.3 Энтальпия пара для сплава (кДж/моль) Enthalpy vapour
	private JLabel jlEntVapour = new JLabel("<html>Расчет энтальпии пара для сплава H<sub>g</sub>: </html>");
	private JTextField jtfEntVapour = new JTextField();
	private JLabel jlEntVapourUnits = new JLabel(" кДж/моль");

	private JButton jBtnExit = new JButton("Выход");
	private JButton jBtnNext = new JButton("Далее >>>");

	private JLabel jlUserFirstName = new JLabel(); // Имя
	private JLabel jlUserMiddleName = new JLabel(); // Отчество
	private JLabel jlUserLastName = new JLabel(); // Фамилия

	private JLabel jlStatusMsg = new JLabel("Правильных ответов: ");

	private PanelsTag panelsTag;

	private Stage1QuestionFrameController controller;
	private java.util.List<JTextField> jtfInputData = new ArrayList<>(); //for validate user input data only


	public Stage1QuestionFrame(User user) {
		super("Stage 1, question n");

		controller = new Stage1QuestionFrameController();

		jpQuestion1.add(new JLabel("Мольная доля всех элементов сплава (%): "));
		jpQuestion2.add(new JLabel("Энтальпия жидкого сплава, энтальпия испарения, энтальпия пара (кДж/моль): ")); //2.1, 2.2, 2.3
		jpQuestion3.add(new JLabel("Давление пара чистых компонентов (Па): "));
		jpQuestion4.add(new JLabel("Парциальное давление компонент над сплавом (Па): "));
		jpQuestion5.add(new JLabel("Давление пара над сплавом (Па): "));
		jpQuestion6.add(new JLabel("Мольная доля каждого компонента в паре (%): "));
		jpQuestion7.add(new JLabel("Весовая доля каждого компонента в паре (%): "));
		jpQuestion8.add(new JLabel("Скорость испарения из сварочной ванны каждого элемента (гр/сек): "));
		jpQuestion9.add(new JLabel("Скорость уменьшения массы расплавленного металла за счет испарения (гр/сек): "));

		// Settings main frame this app
		jpMain.setLayout(new BorderLayout());

		jpTitle.add(new JLabel("Задача 1. Расчет процессов испарения металлов при сварку плавлением."));
		jpMain.add(jpTitle, BorderLayout.PAGE_START);

		// ======== Panel 1 =========
		jpQuestion1.setLayout(new BoxLayout(jpQuestion1, BoxLayout.Y_AXIS));
		JPanel jpRows = new JPanel();
		jpRows.setLayout(new BoxLayout(jpRows, BoxLayout.Y_AXIS));
		for(GeneralElementStage1 el : controller.receiveAllElementsStage1(InputDataController.AccessElementsStage1.TASK)){
			addRowsInputDataToPanel(jpRows, jtfInputData, el);
		}
		jpQuestion1.add(jpRows);

		// ========= Panel 2 =========
		jpQuestion2.setLayout(new BoxLayout(jpQuestion2, BoxLayout.Y_AXIS));
		JPanel jpRowQ2p1 = new JPanel();
		JPanel jpRowQ2p2 = new JPanel();
		JPanel jpRowQ2p3 = new JPanel();
		jtfEntLqAll.setName("H<sub>L</sub><sup>0</sup>");
		jtfEntVaporization.setName("&Delta;H<sub>кип</sub>");
		jtfEntVapour.setName("H<sub>g</sub>");
		jpRowQ2p1.add(jlEntLqAll);
		jpRowQ2p1.add(jtfEntLqAll);
		jpRowQ2p1.add(jlEntLqAllUnits);
		jpRowQ2p2.add(jlEntVaporization);
		jpRowQ2p2.add(jtfEntVaporization);
		jpRowQ2p2.add(jlEntVaporizationUnits);
		jpRowQ2p3.add(jlEntVapour);
		jpRowQ2p3.add(jtfEntVapour);
		jpRowQ2p3.add(jlEntVapourUnits);
		jtfEntLqAll.setColumns(4);
		jtfEntVaporization.setColumns(4);
		jtfEntVapour.setColumns(4);
		jpQuestion2.add(jpRowQ2p1);
		jpQuestion2.add(jpRowQ2p2);
		jpQuestion2.add(jpRowQ2p3);

		// ========== Panel 3 ===============
		jpQuestion3.setLayout(new BoxLayout(jpQuestion3, BoxLayout.Y_AXIS));
		JPanel jpRowsQ3 = new JPanel();
		jpRows.setLayout(new BoxLayout(jpRows, BoxLayout.Y_AXIS));
		for(GeneralElementStage1 el : controller.receiveAllElementsStage1(InputDataController.AccessElementsStage1.TASK)){
			addRowsInputDataToPanel(jpRowsQ3, jtfInputData, el);
		}
		jpQuestion3.add(jpRows);

		// ========= panel 4 ============
		jpQuestion4.setLayout(new BoxLayout(jpQuestion4, BoxLayout.Y_AXIS));
		JPanel jpRowsQ4 = new JPanel();
		jpRows.setLayout(new BoxLayout(jpRows, BoxLayout.Y_AXIS));
		for(GeneralElementStage1 el : controller.receiveAllElementsStage1(InputDataController.AccessElementsStage1.TASK)){
			addRowsInputDataToPanel(jpRowsQ4, jtfInputData, el);
		}
		jpQuestion4.add(jpRows);

		// ========= panel 5 ============
		jpQuestion5.setLayout(new FlowLayout());
		jpQuestion5.add(jlQuestion5);
		jpQuestion5.add(jtfAnswer5);

		// Settings for status and directions panel
		jpStatusAndDirection.setLayout(new BoxLayout(jpStatusAndDirection, BoxLayout.Y_AXIS));
		// add direction buttons
		JPanel jpDirBtns = new JPanel(new FlowLayout());
		jBtnExit.setPreferredSize(new Dimension(100, 25));
		jBtnNext.setPreferredSize(new Dimension(500, 25));
		jpDirBtns.add(jBtnExit);
		jpDirBtns.add(jBtnNext);
		jpStatusAndDirection.add(jpDirBtns);
		// add
		JPanel jpStatAndDir = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jpStatAndDir.add(jlStatusMsg);
		jpStatusAndDirection.add(jpStatAndDir);
		// add FIO labels
		JPanel jpUserFIO = new JPanel(new FlowLayout());
		jpUserFIO.add(jlUserLastName); // Ф
		jpUserFIO.add(jlUserFirstName); // И
		jpUserFIO.add(jlUserMiddleName); // О
		jpStatusAndDirection.add(jpUserFIO);
		jlUserFirstName.setText(user.getFirstName());
		jlUserMiddleName.setText(user.getMiddleName());
		jlUserLastName.setText(user.getLastName());

		jlStatusMsg.setText(jlStatusMsg.getText() + "1/8");


		// other panels
		// set settings for created panels
		jpQuestion1.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "Вопрос 1"
		));
		jpQuestion2.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "Вопрос 2"
		));
		jpQuestion3.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "Вопрос 3"
		));
		jpQuestion4.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "Вопрос 4"
		));
		jpQuestion5.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "Вопрос 5"
		));


		// show question 1
		panelsTag = PanelsTag.PANEL_4;
		showNextPanel();
		showOtherViewElements();

		add(jpMain);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//		setSize(500, 300);
		setLocation(200, 200);
		setVisible(true);
		pack();

//		addComponentsListeners();

		jBtnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// validate entered data which chemical elements involved.
				int correctInputDataCounter = 0;
				for (JTextField jtf : jtfInputData) {
					String res = controller.validateInputData(jtf, InputDataController.ValidatorVariant.IS_NUMBER);
					if (!res.equals(InputDataController.SUCCESS_VALIDATE)) {
						jlStatusMsg.setText(res);
						return;
					}
					correctInputDataCounter++;
					if (correctInputDataCounter == jtfInputData.size()) {
						jlStatusMsg.setText("");
					}
				}

				// validate entered data for question 2.1, 2.2, 2.3
				String res = controller.validateInputData(jtfEntLqAll, InputDataController.ValidatorVariant.IS_NUMBER);
				if (!res.equals(InputDataController.SUCCESS_VALIDATE)) {
					jlStatusMsg.setText(res);
					return;
				}

				res = controller.validateInputData(jtfEntVaporization, InputDataController.ValidatorVariant.IS_NUMBER);
				if (!res.equals(InputDataController.SUCCESS_VALIDATE)) {
					jlStatusMsg.setText(res);
					return;
				}

				res = controller.validateInputData(jtfEntVapour, InputDataController.ValidatorVariant.IS_NUMBER);
				if (!res.equals(InputDataController.SUCCESS_VALIDATE)) {
					jlStatusMsg.setText(res);
					return;
				}

				jlStatusMsg.setText("");
			}
		});
	}

	private void addRowsInputDataToPanel(JPanel jpRows, List<JTextField> jtfList, GeneralElementStage1 el) {
		JLabel jlName = new JLabel(el.toString() + " : ");
		JTextField jtfVal = new JTextField();
		jtfVal.setColumns(4);
		jtfVal.setName(el.toString());
		jtfList.add(jtfVal);
		JLabel jlPercent = new JLabel(" %");

		JPanel jpItems = new JPanel();
		jpItems.add(jlName);
		jpItems.add(jtfVal);
		jpItems.add(jlPercent);

		jpRows.add(jpItems);
	}

	private void checkAnswer() {
		System.out.println(jtfAnswer2.getText());
		if (jtfAnswer1.getText().equals("5") && panelsTag == PanelsTag.PANEL_1) {
			panelsTag = PanelsTag.PANEL_2;

		} else if (jtfAnswer2.getText().equals("4") && panelsTag == PanelsTag.PANEL_2) {
			panelsTag = PanelsTag.PANEL_3;
			System.out.println("correct answer 2");

		} else if (jtfAnswer3.getText().equals("8") && panelsTag == PanelsTag.PANEL_3){
			panelsTag = PanelsTag.PANEL_4;
		}
		else {
			JOptionPane.showMessageDialog(rootPane, "Не правильный ответ", "Info", JOptionPane.INFORMATION_MESSAGE);
		}
		showNextPanel();
	}

	private void showNextPanel() {
		switch (panelsTag) {
			case PANEL_1:
				jpMain.add(jpQuestion1, BorderLayout.CENTER);
				break;
			case PANEL_2:
				jpMain.remove(jpQuestion1);
				jpMain.add(jpQuestion2, BorderLayout.CENTER);
				break;
			case PANEL_3:
				jpMain.remove(jpQuestion2);
				jpMain.add(jpQuestion3, BorderLayout.CENTER);
				break;
			case PANEL_4:
				jpMain.remove(jpQuestion3);
				jpMain.add(jpQuestion4, BorderLayout.CENTER);
				break;
		}
		showOtherViewElements();
		revalidate();
		repaint();
		pack();
	}

	private enum PanelsTag {
		PANEL_1,
		PANEL_2,
		PANEL_3,
		PANEL_4,
		PANEL_5;
	}

	private void showOtherViewElements() {
		jpMain.add(jpStatusAndDirection, BorderLayout.PAGE_END);
	}

}
