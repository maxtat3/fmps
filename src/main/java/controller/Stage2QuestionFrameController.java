package controller;

import app.Accuracy;
import stage2.reactions.BasicReaction;
import stage2.reactions.Rcn2CO2eq2COplO2;
import stage2.reactions.RcnCOeqCplO;
import stage2.reactions.RcnO2eq2O;
import ui.QuestionsStage2;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Stage2QuestionFrameController extends InputDataController {

	// TODO: 11.07.17 may be moved this flag from all stages to const ?
	/**
	 * Returned string result for {@link Stage2QuestionFrameController} method.
	 * If all questions has true results, returned value determined in this const.
	 * Otherwise returned question field name who has a wrong value.
	 */
	public static final String SUCCESS_ANSWER = "0";


	private List<BasicReaction> correctAnswers = new ArrayList<>();

	public Stage2QuestionFrameController() {
		RcnO2eq2O rcn1 = new RcnO2eq2O();
		Rcn2CO2eq2COplO2 rcn2 = new Rcn2CO2eq2COplO2();
		RcnCOeqCplO rcn3 = new RcnCOeqCplO();
		// q1
		rcn1.setDeltaGT(10.2);
		rcn2.setDeltaGT(20.1);
		rcn3.setDeltaGT(29.5);
		// q2
		rcn1.setKp(10);
		rcn2.setKp(11);
		rcn3.setKp(12);
		// q3
		rcn1.setPi(1);
		rcn2.setPi(5);
		rcn3.setPi(7);

		correctAnswers.add(rcn2);
		correctAnswers.add(rcn1);
		correctAnswers.add(rcn3);
	}

	public String checkAnswerJTFs(List<JTextField> jtfs, QuestionsStage2.PanelsTag question) {
		String res;
		switch (question) {
			case PANEL_1:
				res = checkEachInputField(jtfs, correctAnswers, question);
				if (res.equals(SUCCESS_ANSWER)) break;
				else return res;
//				for (JTextField jtf : jtfs) {
//					System.out.println(jtf.getName());
//				}

			case PANEL_2:
				// ...
				break;

			case PANEL_3:
				// ...
				break;

			case PANEL_5:
				// ...
				break;
		}
		return SUCCESS_ANSWER;
	}

	public String checkAnswerJCMBs(List<JComboBox> jcmbs, QuestionsStage2.PanelsTag question) {
		switch (question) {
			case PANEL_4:
				// ...
				break;
		}
		return null;
	}



	private String checkEachInputField(List<JTextField> jtfs, List<BasicReaction> correctAnswers, QuestionsStage2.PanelsTag question) {
		for (BasicReaction correct : correctAnswers) {
			for (JTextField jtf : jtfs) {
				if (correct.toString().equals(jtf.getName())) {
					if (!Accuracy.checkValueInRange(
						getReactionCompareProperty(correct, question),
						Double.parseDouble(jtf.getText()),
						Accuracy.ACCURACY)
						) {
						return jtf.getName();
					}
				}
			}
		}
		return SUCCESS_ANSWER;
	}

	private double getReactionCompareProperty(BasicReaction rcn, QuestionsStage2.PanelsTag question){
		switch (question) {
			case PANEL_1:
				return rcn.getDeltaGT();

			case PANEL_2:
				return 2;

			case PANEL_3:
				return 3;

			case PANEL_4:
				return 4;

			case PANEL_5:
				return 5;
		}
		return -1;
	}

	private void checkAnswerForQuestion4(List<JComboBox> jcmboxes){
		for (JComboBox jcmbox : jcmboxes) {
			System.out.println(jcmbox.getName());
		}
	}

	private void checkAnswerForQuestions5(List<JTextField> jtfs){

	}
}
