package ui;

import controller.InputDataController;
import controller.Stage1QuestionFrameController;
import stage1.elements.GeneralElementStage1;
import util.UIUtils;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class QuestionsStage2 implements Stage1QuestionFrame.QuestionPanel {


	private static final String PERCENT_SYM = "%";
	public static final String ENT_LQ_ALLOY_SYM = "H<sub>L</sub><sup>0</sup>";
	public static final String ENT_VAPORIZATION_SYM = "&Delta;H<sub>кип</sub>";
	public static final String ENT_VAPOUR_SYM = "H<sub>g</sub>";
	public static final String VAP_PRES_OVER_ALLOY_SYM = "P<sup>p</sup>";
	public static final String DECR_MOLTEN_METAL_DUE_VAP_SYM = "&Delta;<i>v</i><sub>m</sub>";
	private static final String KJOULE_SYM = "кДж/моль";
	private static final String PASCALE_SYM = "Па";
	private static final String GRAM_TO_SEC_SYM = "гр/сек";


	private JPanel jpQuestion1 = new JPanel();
	private JPanel jpQuestion2 = new JPanel();
	private JPanel jpQuestion3 = new JPanel();


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


	private final JPanel jpMain;
	private Stage1QuestionFrame questionFrame;
	private final JLabel jlStatusMsg;


	private Stage1QuestionFrameController controller;
	private java.util.List<JTextField> jtfInputData = new ArrayList<>(); //for validate user input data only


	private PanelsTag panelsTag;


	public QuestionsStage2(JPanel jpMain, Stage1QuestionFrame questionFrame, JLabel jlStatusMsg) {
		this.jpMain = jpMain;
		this.questionFrame = questionFrame;
		this.jlStatusMsg = jlStatusMsg;



		jpQuestion1.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "Вопрос 1 Задача 2"
		));
		jpQuestion2.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "Вопрос 2 Задача 2"
		));
		jpQuestion3.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "Вопрос 3 Задача 2"
		));

		jpQuestion1.add(new JLabel("question 1: "));
		jpQuestion2.add(new JLabel("question 2: "));
		jpQuestion3.add(new JLabel("question 3: "));


		panelsTag = PanelsTag.PANEL_1;
		controller = new Stage1QuestionFrameController();
		switchQuestionPanel();
	}


	public void reply() {
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
					checkAnswerAndMakeDirection(jtfInputData, PanelsTag.PANEL_3, PanelsTag.PANEL_3);
				}
				break;

		}
	}


	private void checkAnswerAndMakeDirection(List<JTextField> jtfs, PanelsTag currentQuestionPanel, PanelsTag nextQuestionPanel) {
		String res = "0";
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


	public void switchQuestionPanel() {
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
		}
		questionFrame.repaintWindow();
	}




	private void buildQuestion1Panel() {
		jpQuestion1.setLayout(new BoxLayout(jpQuestion1, BoxLayout.Y_AXIS));
		JPanel jpRows = new JPanel();
		jpRows.setLayout(new BoxLayout(jpRows, BoxLayout.Y_AXIS));
		for(GeneralElementStage1 el : controller.receiveUserTaskElementsStage1(InputDataController.AccessElementsStage1.TASK)){
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
		for(GeneralElementStage1 el : controller.receiveUserTaskElementsStage1(InputDataController.AccessElementsStage1.TASK)){
			addRowsInputDataToPanel(jpRowsQ3, jtfInputData, el, PASCALE_SYM);
		}
		jpQuestion3.add(jpRowsQ3);
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
		PANEL_1, PANEL_2, PANEL_3
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
}
