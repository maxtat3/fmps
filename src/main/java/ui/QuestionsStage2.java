package ui;

import controller.InputDataController;
import controller.Stage1QuestionFrameController;
import controller.Stage2QuestionFrameController;
import stage2.reactions.BasicReaction;
import stage2.reactions.Rcn2CO2eq2COplO2;
import stage2.reactions.RcnCOeqCplO;
import stage2.reactions.RcnO2eq2O;
import util.UIUtils;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class QuestionsStage2 implements QuestionsFrame.QuestionPanel {

	private static final String PERCENT_SYM = "%";
	private static final String PASCALE_SYM = "Па";

	public static final String N_O = "N<sub>O</sub>";
	public static final String N_CO = "N<sub>CO</sub>";
	public static final String N_CO2 = "N<sub>CO2</sub>";

	private JPanel jpQuestion1 = new JPanel();
	private JPanel jpQuestion2 = new JPanel();
	private JPanel jpQuestion3 = new JPanel();
	private JPanel jpQuestion4 = new JPanel();
	private JPanel jpQuestion5 = new JPanel();

	// Elements for question 5
	private JLabel jlNO = new JLabel("<html>" + N_O + " : </html>");
	private JTextField jtfNO = new JTextField();
	private JLabel jlNOUnits = new JLabel(PERCENT_SYM);

	private JLabel jlNCO = new JLabel("<html>" + N_CO + " : </html>");
	private JTextField jtfNCO = new JTextField();
	private JLabel jlNCOUnits = new JLabel(PERCENT_SYM);

	private JLabel jlNCO2 = new JLabel("<html>" + N_CO2 + " : </html>");
	private JTextField jtfNCO2 = new JTextField();
	private JLabel jlNCO2Units = new JLabel(PERCENT_SYM);

	private List<BasicReaction> reactions = new ArrayList<>();

	private final JPanel jpMain;
	private QuestionsFrame questionFrame;
	private final JLabel jlStatusMsg;


	private InputDataController inputController;
	private Stage2QuestionFrameController controller;
	private java.util.List<JTextField> jtfInputData = new ArrayList<>(); //for validate user input data only
	private List<JComboBox> jcmboxesInputData = new ArrayList<>();


	private PanelsTag panelsTag;


	public QuestionsStage2(JPanel jpMain, QuestionsFrame questionFrame, JLabel jlStatusMsg) {
		this.jpMain = jpMain;
		this.questionFrame = questionFrame;
		this.jlStatusMsg = jlStatusMsg;

		fillReactions();

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

		jpQuestion1.add(new JLabel("Изменение энергии Гиббса для следующих реакций: "));
		jpQuestion2.add(new JLabel("Определение константы равновесия для следующих реакций: "));
		jpQuestion3.add(new JLabel("Определения коэффициента π состояния в начальный момент: "));
		jpQuestion4.add(new JLabel("Определение направления протекания реакций: "));
		JLabel jlQ5TitlePart1 = new JLabel("Определение состава газовой смеси для данной температуры, если изначально она ");
		JLabel jlQ5TitlePart2 = new JLabel("<html>содержит только углекислый газ, а уравнение диссоциации имеет вид: " +
			"CO<sub>2</sub> = CO + O :</html>");
		jpQuestion5.add(jlQ5TitlePart1);
		jpQuestion5.add(jlQ5TitlePart2);

		panelsTag = PanelsTag.PANEL_1;
		inputController = new InputDataController();
		controller = new Stage2QuestionFrameController();

		switchQuestionPanel();
	}


	private void fillReactions() {
		reactions.add(new Rcn2CO2eq2COplO2());
		reactions.add(new RcnO2eq2O());
		reactions.add(new RcnCOeqCplO());
	}


	public void reply() {
		switch (panelsTag) {
			case PANEL_1:
				if (generalValidateInputOfEachElem()) {
					checkAnswerAndMakeDirection(jtfInputData, PanelsTag.PANEL_1, PanelsTag.PANEL_2);
				}
				break;

			case PANEL_2:
				if (generalValidateInputOfEachElem()) {
					checkAnswerAndMakeDirection(jtfInputData, PanelsTag.PANEL_2, PanelsTag.PANEL_3);
				}
				break;

			case PANEL_3:
				if (generalValidateInputOfEachElem()) {
					checkAnswerAndMakeDirection(jtfInputData, PanelsTag.PANEL_3, PanelsTag.PANEL_4);
				}
				break;

			case PANEL_4:
				// in this question no validation - user must be chosen one of the proposed variant
				checkAnswer4AndMakeDirection(jcmboxesInputData);
				break;

			case PANEL_5:
				if (validateInputQuestion5()) {
					// ...
				}
				break;

		}
	}


	private void checkAnswerAndMakeDirection(List<JTextField> jtfs, PanelsTag currentQuestionPanel, PanelsTag nextQuestionPanel) {
		String res = controller.checkAnswerJTFs(jtfs, currentQuestionPanel);
		if (res.equals(Stage1QuestionFrameController.SUCCESS_ANSWER)) {
			panelsTag = nextQuestionPanel;
			switchQuestionPanel();
			formTrueAnswerMsg();
		} else {
			jlStatusMsg.setText(formWrongAnswerMsg(res));
		}
	}

	private void checkAnswer4AndMakeDirection(List<JComboBox> jcmbox) {
		// ...
	}

	/**
	 * Validate entered data for all reactions field.
	 */
	private boolean generalValidateInputOfEachElem() {
		int correctInputDataCounter = 0;
		for (JTextField jtf : jtfInputData) {
			String res = inputController.validateInputData(jtf, InputDataController.ValidatorVariant.IS_NUMBER);
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

	private boolean validateInputQuestion5() {
		String res = inputController.validateInputData(jtfNO, InputDataController.ValidatorVariant.IS_NUMBER);
		if (!res.equals(InputDataController.SUCCESS_VALIDATE)) {
			jlStatusMsg.setText(res);
			return false;
		}

		res = inputController.validateInputData(jtfNCO, InputDataController.ValidatorVariant.IS_NUMBER);
		if (!res.equals(InputDataController.SUCCESS_VALIDATE)) {
			jlStatusMsg.setText(res);
			return false;
		}

		res = inputController.validateInputData(jtfNCO2, InputDataController.ValidatorVariant.IS_NUMBER);
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
			case PANEL_4:
				buildQuestion4Panel();
				jpMain.add(jpQuestion4, BorderLayout.CENTER);
				break;
			case PANEL_5:
				buildQuestion5Panel();
				jpMain.add(jpQuestion5, BorderLayout.CENTER);
				break;
		}
		questionFrame.repaintWindow();
	}

	private void buildQuestion1Panel() {
		jpQuestion1.setLayout(new BoxLayout(jpQuestion1, BoxLayout.Y_AXIS));
		JPanel jpRows = new JPanel();
		jpRows.setLayout(new BoxLayout(jpRows, BoxLayout.Y_AXIS));
		for(BasicReaction rcn : reactions){
			addRowsInputDataToPanel(jpRows, jtfInputData, rcn, PERCENT_SYM);
		}
		jpQuestion1.add(jpRows);
	}

	private void buildQuestion2Panel() {
		jpQuestion2.setLayout(new BoxLayout(jpQuestion2, BoxLayout.Y_AXIS));
		JPanel jpRows = new JPanel();
		jpRows.setLayout(new BoxLayout(jpRows, BoxLayout.Y_AXIS));
		for(BasicReaction rcn : reactions){
			addRowsInputDataToPanel(jpRows, jtfInputData, rcn, PERCENT_SYM);
		}
		jpQuestion2.add(jpRows);
	}

	private void buildQuestion3Panel() {
		jpQuestion3.setLayout(new BoxLayout(jpQuestion3, BoxLayout.Y_AXIS));
		JPanel jpRowsQ3 = new JPanel();
		jpRowsQ3.setLayout(new BoxLayout(jpRowsQ3, BoxLayout.Y_AXIS));
		for(BasicReaction rcn : reactions){
			addRowsInputDataToPanel(jpRowsQ3, jtfInputData, rcn, PASCALE_SYM);
		}
		jpQuestion3.add(jpRowsQ3);
	}

	private void buildQuestion4Panel() {
		jpQuestion4.setLayout(new BoxLayout(jpQuestion4, BoxLayout.Y_AXIS));
		JPanel jpRowsQ4 = new JPanel();
		jpRowsQ4.setLayout(new BoxLayout(jpRowsQ4, BoxLayout.Y_AXIS));
		for(BasicReaction rcn : reactions){
			addComboBoxesToPanel(jpRowsQ4, jcmboxesInputData, rcn, PASCALE_SYM);
		}
		jpQuestion4.add(jpRowsQ4);
	}

	private void buildQuestion5Panel() {
		jpQuestion5.setLayout(new BoxLayout(jpQuestion5, BoxLayout.Y_AXIS));
		JPanel jpRowQ5p1 = new JPanel();
		JPanel jpRowQ5p2 = new JPanel();
		JPanel jpRowQ5p3 = new JPanel();
		jtfNO.setName(N_O);
		jtfNCO.setName(N_CO);
		jtfNCO2.setName(N_CO2);
		jpRowQ5p1.add(jlNO);
		jpRowQ5p1.add(jtfNO);
		jpRowQ5p1.add(jlNOUnits);
		jpRowQ5p2.add(jlNCO);
		jpRowQ5p2.add(jtfNCO);
		jpRowQ5p2.add(jlNCOUnits);
		jpRowQ5p3.add(jlNCO2);
		jpRowQ5p3.add(jtfNCO2);
		jpRowQ5p3.add(jlNCO2Units);
		jtfNO.setColumns(4);
		jtfNCO.setColumns(4);
		jtfNCO2.setColumns(4);
		jpQuestion5.add(jpRowQ5p1);
		jpQuestion5.add(jpRowQ5p2);
		jpQuestion5.add(jpRowQ5p3);
	}


	private void addRowsInputDataToPanel(JPanel jpRows, List<JTextField> jtfList, BasicReaction rcn, String units) {
		JLabel jlName = new JLabel(rcn.toString());
		JTextField jtfVal = new JTextField();
		jtfVal.setColumns(4);
		jtfVal.setName(rcn.toString());
		jtfList.add(jtfVal);
		JLabel jlPercent = new JLabel(units);

		JPanel jpItems = new JPanel();
		jpItems.add(jlName);
		jpItems.add(jtfVal);
		jpItems.add(jlPercent);

		jpRows.add(jpItems);
	}

	private void addComboBoxesToPanel(JPanel jpRows, List<JComboBox> jcmboxList, BasicReaction rcn, String units) {
		JLabel jlName = new JLabel(rcn.toString());

		@SuppressWarnings("unchecked")
		JComboBox<String> jcmb = new JComboBox<>();
		List<String> directions = new ArrayList<>();
		for(DirectionOfReactions d : DirectionOfReactions.values() ) {
			directions.add(d.getName());
		}
		jcmb.setModel(new DefaultComboBoxModel<>(directions.toArray(new String[0])));
		jcmboxList.add(jcmb);

		JLabel jlPercent = new JLabel(units);

		JPanel jpItems = new JPanel();
		jpItems.add(jlName);
		jpItems.add(jcmb);
		jpItems.add(jlPercent);

		jpRows.add(jpItems);
	}

	public enum DirectionOfReactions {
		LEFT("←"), NOT_CHANGED("0"), RIGHT("→");

		private String name;

		DirectionOfReactions(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	public enum PanelsTag {
		PANEL_1, PANEL_2, PANEL_3, PANEL_4, PANEL_5
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
