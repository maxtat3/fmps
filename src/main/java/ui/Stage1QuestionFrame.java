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
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class Stage1QuestionFrame extends JFrame {

	private static final String PERCENT_SYM = "%";
	public static final String ENT_LQ_ALLOY_SYM = "H<sub>L</sub><sup>0</sup>";
	public static final String ENT_VAPORIZATION_SYM = "&Delta;H<sub>кип</sub>";
	public static final String ENT_VAPOUR_SYM = "H<sub>g</sub>";
	public static final String VAP_PRES_OVER_ALLOY_SYM = "P<sup>p</sup>";
	private static final String DECR_MOLTEN_METAL_DUE_VAP_SYM = "&Delta;<i>v</i><sub>m</sub>";
	private static final String KJOULE_SYM = "кДж/моль";
	private static final String PASCALE_SYM = "Па";
	private static final String GRAM_TO_SEC_SYM = "гр/сек";
	private JPanel jpMain = new JPanel();
	private JPanel jpTitle = new JPanel();
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

	// Elements for question 2
	// 2.1 Расчет энтальпии жидкого сплава (кДж/моль) Enthalpy liquid alloy
	private JLabel jlEntLqAll = new JLabel("<html>Расчет энтальпии жидкого сплава " + ENT_LQ_ALLOY_SYM + " : </html>");
	private JTextField jtfEntLqAll = new JTextField();
	private JLabel jlEntLqAllUnits = new JLabel(KJOULE_SYM);
	// 2.2 Расчет энтальпии испарения (кДж/моль) Enthalpy vaporization
	private JLabel jlEntVaporization = new JLabel("<html>Расчет энтальпии испарения " + ENT_VAPORIZATION_SYM + " : </html>");
	private JTextField jtfEntVaporization = new JTextField();
	private JLabel jlEntVaporizationUnits = new JLabel(KJOULE_SYM);
	// 2.3 Энтальпия пара для сплава (кДж/моль) Enthalpy vapour
	private JLabel jlEntVapour = new JLabel("<html>Расчет энтальпии пара для сплава " + ENT_VAPOUR_SYM + " : </html>");
	private JTextField jtfEntVapour = new JTextField();
	private JLabel jlEntVapourUnits = new JLabel(KJOULE_SYM);
	// Elements for question 5
	private JLabel jlVapPresOverAlloy = new JLabel("<html>Давление пара над сплавом " + VAP_PRES_OVER_ALLOY_SYM + " : </html>");
	private JTextField jtfVapPresOverAlloy = new JTextField();
	private JLabel jlVapPresOverAlloyUnits = new JLabel(PASCALE_SYM);
	// Elements for question 9
	private JLabel jlDecrMoltenMetalDueVap = new JLabel("<html>Уменьшение массы расплавленного металла за счет " +
		"испарения " + DECR_MOLTEN_METAL_DUE_VAP_SYM + " : </html>");
	private JTextField jtfDecrMoltenMetalDueVap = new JTextField();
	private JLabel jlDecrMoltenMetalDueVapUnits = new JLabel(GRAM_TO_SEC_SYM);

	private JButton jBtnExit = new JButton("Выход");
	private JButton jBtnNext = new JButton("Далее >>>");

	private JLabel jlUserFirstName = new JLabel(); // Имя
	private JLabel jlUserMiddleName = new JLabel(); // Отчество
	private JLabel jlUserLastName = new JLabel(); // Фамилия

	private JLabel jlStatusMsg = new JLabel("");

	private PanelsTag panelsTag;

	private Stage1QuestionFrameController controller;
	private java.util.List<JTextField> jtfInputData = new ArrayList<>(); //for validate user input data only


	public Stage1QuestionFrame(User user) {
		super("Stage 1, question n");

		controller = new Stage1QuestionFrameController();

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
		jpQuestion6.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "Вопрос 6"
		));
		jpQuestion7.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "Вопрос 7"
		));
		jpQuestion8.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "Вопрос 8"
		));
		jpQuestion9.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "Вопрос 9"
		));

		jpQuestion1.add(new JLabel("Мольная доля всех элементов сплава: "));
		jpQuestion2.add(new JLabel("Энтальпия жидкого сплава, энтальпия испарения, энтальпия пара: ")); //2.1, 2.2, 2.3
		jpQuestion3.add(new JLabel("Давление пара чистых компонентов: "));
		jpQuestion4.add(new JLabel("Парциальное давление компонент над сплавом: "));
		jpQuestion5.add(new JLabel("Давление пара над сплавом: "));
		jpQuestion6.add(new JLabel("Мольная доля каждого компонента в паре: "));
		jpQuestion7.add(new JLabel("Весовая доля каждого компонента в паре: "));
		jpQuestion8.add(new JLabel("Скорость испарения из сварочной ванны каждого элемента: "));
		jpQuestion9.add(new JLabel("Скорость уменьшения массы расплавленного металла за счет испарения: "));

		jpTitle.add(new JLabel("Задача 1. Расчет процессов испарения металлов при сварке плавлением"));
		jpTitle.setLayout(new FlowLayout(FlowLayout.CENTER));

		jpMain.setLayout(new BorderLayout());
		jpMain.add(jpTitle, BorderLayout.PAGE_START);

		jpStatusAndDirection.setLayout(new BoxLayout(jpStatusAndDirection, BoxLayout.Y_AXIS));

		jBtnExit.setPreferredSize(new Dimension(100, 25));
		jBtnNext.setPreferredSize(new Dimension(500, 25));
		JPanel jpDirBtns = new JPanel(new FlowLayout());
		jpDirBtns.add(jBtnExit);
		jpDirBtns.add(jBtnNext);
		jpStatusAndDirection.add(jpDirBtns);

		JPanel jpStatAndDir = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jpStatAndDir.add(jlStatusMsg);
		jpStatusAndDirection.add(jpStatAndDir);

		JPanel jpUserFIO = new JPanel(new FlowLayout());
		jpUserFIO.add(jlUserLastName); // Ф
		jpUserFIO.add(jlUserFirstName); // И
		jpUserFIO.add(jlUserMiddleName); // О
		jpStatusAndDirection.add(jpUserFIO);
		jlUserFirstName.setText(user.getFirstName());
		jlUserMiddleName.setText(user.getMiddleName());
		jlUserLastName.setText(user.getLastName());


		// show question 1
		panelsTag = PanelsTag.PANEL_1;
		switchQuestionPanel();
		jpMain.add(jpStatusAndDirection, BorderLayout.PAGE_END);
		System.out.println("jtfInputData.size() = " + jtfInputData.size());

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
				switch (panelsTag) {
					case PANEL_1:
						if (generalValidateInputOfEachElem()) {
							checkAnswerAndMakeDirection(jtfInputData, PanelsTag.PANEL_1, PanelsTag.PANEL_2);
						}
						break;

					case PANEL_2:
						if (validateInputQuestion2()) {
							checkAnswerAndMakeDirection(
								new ArrayList<>(Arrays.asList(jtfEntLqAll, jtfEntVaporization, jtfEntVapour)),
								PanelsTag.PANEL_2,
								PanelsTag.PANEL_3
							);
						}
						break;

					case PANEL_3:
						if (generalValidateInputOfEachElem()) {
							checkAnswerAndMakeDirection(jtfInputData, PanelsTag.PANEL_3, PanelsTag.PANEL_4);
						}
						break;

					case PANEL_4:
						if (generalValidateInputOfEachElem()) {
							checkAnswerAndMakeDirection(jtfInputData, PanelsTag.PANEL_4, PanelsTag.PANEL_5);
						}
						break;

					case PANEL_5:
						if (validateInputQuestion5()) {
							checkAnswerAndMakeDirection(
								new ArrayList<>(Arrays.asList(jtfVapPresOverAlloy)),
								PanelsTag.PANEL_5,
								PanelsTag.PANEL_6
							);
						}
						break;

					case PANEL_6:
						if (generalValidateInputOfEachElem()) {
							checkAnswerAndMakeDirection(jtfInputData, PanelsTag.PANEL_6, PanelsTag.PANEL_7);
						}
						break;

					case PANEL_7:
						if (generalValidateInputOfEachElem()) {
							checkAnswerAndMakeDirection(jtfInputData, PanelsTag.PANEL_7, PanelsTag.PANEL_8);
						}
						break;

					case PANEL_8:
						if (generalValidateInputOfEachElem()) {
							checkAnswerAndMakeDirection(jtfInputData, PanelsTag.PANEL_8, PanelsTag.PANEL_9);
						}
						break;

					case PANEL_9:
						if (validateInputQuestion9()) {
							checkAnswerAndMakeDirection(jtfInputData, PanelsTag.PANEL_9, PanelsTag.PANEL_9);
						}
						break;
				}
				repaintWindow();
				System.out.println("jtfInputData.size() = " + jtfInputData.size());
			}
		});
	}

	private void checkAnswerAndMakeDirection(List<JTextField> jtfs, PanelsTag currentQuestionPanel, PanelsTag nextQuestionPanel) {
		String res = controller.checkAnswer(jtfs, currentQuestionPanel);
		if (res.equals(Stage1QuestionFrameController.SUCCESS_ANSWER)) {
			panelsTag = nextQuestionPanel;
			switchQuestionPanel();
			formTrueAnswerMsg();
		} else {
			jlStatusMsg.setText(formWrongAnswerMsg(res));
		}
	}

	/**
	 * Validate entered data which chemical elements involved.
	 */
	private boolean generalValidateInputOfEachElem() {
		int correctInputDataCounter = 0;
		for (JTextField jtf : jtfInputData) {
			String res = controller.validateInputData(jtf, InputDataController.ValidatorVariant.IS_NUMBER);
			if (!res.equals(InputDataController.SUCCESS_VALIDATE)) {
				jlStatusMsg.setText(res);
				return false;
			}
			correctInputDataCounter++;
			if (correctInputDataCounter == jtfInputData.size()) {
				jlStatusMsg.setText("");
			}
		}
		return true;
	}

	/**
	 * Validate entered data for question 2.1, 2.2, 2.3
	 */
	private boolean validateInputQuestion2() {
		String res = controller.validateInputData(jtfEntLqAll, InputDataController.ValidatorVariant.IS_NUMBER);
		if (!res.equals(InputDataController.SUCCESS_VALIDATE)) {
			jlStatusMsg.setText(res);
			return false;
		}

		res = controller.validateInputData(jtfEntVaporization, InputDataController.ValidatorVariant.IS_NUMBER);
		if (!res.equals(InputDataController.SUCCESS_VALIDATE)) {
			jlStatusMsg.setText(res);
			return false;
		}

		res = controller.validateInputData(jtfEntVapour, InputDataController.ValidatorVariant.IS_NUMBER);
		if (!res.equals(InputDataController.SUCCESS_VALIDATE)) {
			jlStatusMsg.setText(res);
			return false;
		}
		jlStatusMsg.setText("");
		return true;
	}

	/**
	 * Validate entered data for question 5
	 */
	private boolean validateInputQuestion5(){
		String res = controller.validateInputData(jtfVapPresOverAlloy, InputDataController.ValidatorVariant.IS_NUMBER);
		if (!res.equals(InputDataController.SUCCESS_VALIDATE)) {
			jlStatusMsg.setText(res);
			return false;
		}
		jlStatusMsg.setText("");
		return true;
	}

	/**
	 * Validate entered data for question 9
	 */
	private boolean validateInputQuestion9(){
		if (jtfDecrMoltenMetalDueVap == null) System.out.println("jtfDecrMoltenMetalDueVap is null !");
		else System.out.println("jtfDecrMoltenMetalDueVap is NOT null");

		String res = controller.validateInputData(jtfDecrMoltenMetalDueVap, InputDataController.ValidatorVariant.IS_NUMBER);
		if (!res.equals(InputDataController.SUCCESS_VALIDATE)) {
			jlStatusMsg.setText(res);
			return false;
		}
		jlStatusMsg.setText("");
		return true;
	}

	private void switchQuestionPanel() {
		jtfInputData.clear();
		switch (panelsTag) {
			case PANEL_1:
				buildQuestion1Panel();
				jpMain.add(jpQuestion1, BorderLayout.CENTER);
				break;
			case PANEL_2:
				buildQuestion2Panel();
				jpMain.remove(jpQuestion1);
				jpMain.add(jpQuestion2, BorderLayout.CENTER);
				break;
			case PANEL_3:
				buildQuestion3Panel();
				jpMain.remove(jpQuestion2);
				jpMain.add(jpQuestion3, BorderLayout.CENTER);
				break;
			case PANEL_4:
				buildQuestion4Panel();
				jpMain.remove(jpQuestion3);
				jpMain.add(jpQuestion4, BorderLayout.CENTER);
				break;
			case PANEL_5:
				buildQuestion5Panel();
				jpMain.remove(jpQuestion4);
				jpMain.add(jpQuestion5, BorderLayout.CENTER);
				break;
			case PANEL_6:
				buildQuestion6Panel();
				jpMain.remove(jpQuestion5);
				jpMain.add(jpQuestion6, BorderLayout.CENTER);
				break;
			case PANEL_7:
				buildQuestion7Panel();
				jpMain.remove(jpQuestion6);
				jpMain.add(jpQuestion7, BorderLayout.CENTER);
				break;
			case PANEL_8:
				buildQuestion8Panel();
				jpMain.remove(jpQuestion7);
				jpMain.add(jpQuestion8, BorderLayout.CENTER);
				break;
			case PANEL_9:
				buildQuestion9Panel();
				jpMain.remove(jpQuestion8);
				jpMain.add(jpQuestion9, BorderLayout.CENTER);
				break;
		}
		repaintWindow();
	}

	private void buildQuestion1Panel() {
		jpQuestion1.setLayout(new BoxLayout(jpQuestion1, BoxLayout.Y_AXIS));
		JPanel jpRows = new JPanel();
		jpRows.setLayout(new BoxLayout(jpRows, BoxLayout.Y_AXIS));
		for(GeneralElementStage1 el : controller.receiveAllElementsStage1(InputDataController.AccessElementsStage1.TASK)){
			addRowsInputDataToPanel(jpRows, jtfInputData, el, PERCENT_SYM);
		}
		jpQuestion1.add(jpRows);
	}

	private void buildQuestion2Panel() {
		jpQuestion2.setLayout(new BoxLayout(jpQuestion2, BoxLayout.Y_AXIS));
		JPanel jpRowQ2p1 = new JPanel();
		JPanel jpRowQ2p2 = new JPanel();
		JPanel jpRowQ2p3 = new JPanel();
		jtfEntLqAll.setName(ENT_LQ_ALLOY_SYM);
		jtfEntVaporization.setName(ENT_VAPORIZATION_SYM);
		jtfEntVapour.setName(ENT_VAPOUR_SYM);
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
	}

	private void buildQuestion3Panel() {
		jpQuestion3.setLayout(new BoxLayout(jpQuestion3, BoxLayout.Y_AXIS));
		JPanel jpRowsQ3 = new JPanel();
		jpRowsQ3.setLayout(new BoxLayout(jpRowsQ3, BoxLayout.Y_AXIS));
		for(GeneralElementStage1 el : controller.receiveAllElementsStage1(InputDataController.AccessElementsStage1.TASK)){
			addRowsInputDataToPanel(jpRowsQ3, jtfInputData, el, PASCALE_SYM);
		}
		jpQuestion3.add(jpRowsQ3);
	}

	private void buildQuestion4Panel(){
		jpQuestion4.setLayout(new BoxLayout(jpQuestion4, BoxLayout.Y_AXIS));
		JPanel jpRowsQ4 = new JPanel();
		jpRowsQ4.setLayout(new BoxLayout(jpRowsQ4, BoxLayout.Y_AXIS));
		for(GeneralElementStage1 el : controller.receiveAllElementsStage1(InputDataController.AccessElementsStage1.TASK)){
			addRowsInputDataToPanel(jpRowsQ4, jtfInputData, el, PASCALE_SYM);
		}
		jpQuestion4.add(jpRowsQ4);
	}

	private void buildQuestion5Panel(){
		jpQuestion5.setLayout(new BoxLayout(jpQuestion5, BoxLayout.Y_AXIS));
		JPanel jpRowQ5 = new JPanel();
		jtfVapPresOverAlloy.setName(VAP_PRES_OVER_ALLOY_SYM);
		jpRowQ5.add(jlVapPresOverAlloy);
		jpRowQ5.add(jtfVapPresOverAlloy);
		jpRowQ5.add(jlVapPresOverAlloyUnits);
		jtfVapPresOverAlloy.setColumns(4);
		jpQuestion5.add(jpRowQ5);
	}

	private void buildQuestion6Panel(){
		jpQuestion6.setLayout(new BoxLayout(jpQuestion6, BoxLayout.Y_AXIS));
		JPanel jpRowsQ6 = new JPanel();
		jpRowsQ6.setLayout(new BoxLayout(jpRowsQ6, BoxLayout.Y_AXIS));
		for(GeneralElementStage1 el : controller.receiveAllElementsStage1(InputDataController.AccessElementsStage1.TASK)){
			addRowsInputDataToPanel(jpRowsQ6, jtfInputData, el, PERCENT_SYM);
		}
		jpQuestion6.add(jpRowsQ6);
	}

	private void buildQuestion7Panel(){
		jpQuestion7.setLayout(new BoxLayout(jpQuestion7, BoxLayout.Y_AXIS));
		JPanel jpRowsQ7 = new JPanel();
		jpRowsQ7.setLayout(new BoxLayout(jpRowsQ7, BoxLayout.Y_AXIS));
		for(GeneralElementStage1 el : controller.receiveAllElementsStage1(InputDataController.AccessElementsStage1.TASK)){
			addRowsInputDataToPanel(jpRowsQ7, jtfInputData, el, PERCENT_SYM);
		}
		jpQuestion7.add(jpRowsQ7);
	}

	private void buildQuestion8Panel(){
		jpQuestion8.setLayout(new BoxLayout(jpQuestion8, BoxLayout.Y_AXIS));
		JPanel jpRowsQ8 = new JPanel();
		jpRowsQ8.setLayout(new BoxLayout(jpRowsQ8, BoxLayout.Y_AXIS));
		for(GeneralElementStage1 el : controller.receiveAllElementsStage1(InputDataController.AccessElementsStage1.TASK)){
			addRowsInputDataToPanel(jpRowsQ8, jtfInputData, el, GRAM_TO_SEC_SYM);
		}
		jpQuestion8.add(jpRowsQ8);
	}

	private void buildQuestion9Panel(){
		jpQuestion9.setLayout(new BoxLayout(jpQuestion9, BoxLayout.Y_AXIS));
		JPanel jpRowsQ9 = new JPanel();
		jtfDecrMoltenMetalDueVap.setName(DECR_MOLTEN_METAL_DUE_VAP_SYM);
		jpRowsQ9.add(jlDecrMoltenMetalDueVap);
		jpRowsQ9.add(jtfDecrMoltenMetalDueVap);
		jpRowsQ9.add(jlDecrMoltenMetalDueVapUnits);
		jtfDecrMoltenMetalDueVap.setColumns(4);
		jpQuestion9.add(jpRowsQ9);
	}

	private void addRowsInputDataToPanel(JPanel jpRows, List<JTextField> jtfList, GeneralElementStage1 el, String units) {
		JLabel jlName = new JLabel(el.toString() + " : ");
		JTextField jtfVal = new JTextField();
		jtfVal.setColumns(4);
		jtfVal.setName(el.toString());
		jtfList.add(jtfVal);
		JLabel jlPercent = new JLabel(units);

		JPanel jpItems = new JPanel();
		jpItems.add(jlName);
		jpItems.add(jtfVal);
		jpItems.add(jlPercent);

		jpRows.add(jpItems);
	}

	public enum PanelsTag {
		PANEL_1, PANEL_2, PANEL_3, PANEL_4, PANEL_5, PANEL_6, PANEL_7, PANEL_8, PANEL_9
	}

	/**
	 * Forming formatted massage when user gave a correct answer.
	 */
	private void formTrueAnswerMsg() {
		UIUtils.showAutoClosableMsgDialog("<html><font color=green>Ответ верный</font></html>", 1000);
	}

	/**
	 * Forming formatted massage when user gave a wrong answer.
	 */
	private String formWrongAnswerMsg(String elementName) {
		return "<html><font color=red>Неправильный ответ для " + "<i>" + elementName + "</i>" + " ! </font></html>";
	}

	private void repaintWindow() {
		revalidate();
		repaint();
		pack();
	}

}
