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


	private List<BasicReaction> correctJTFAnswers = new ArrayList<>();

	public Stage2QuestionFrameController() {
		Rcn2CO2eq2COplO2 rcn1 = new Rcn2CO2eq2COplO2();
		RcnO2eq2O rcn2 = new RcnO2eq2O();
		RcnCOeqCplO rcn3 = new RcnCOeqCplO();
		// q1
		rcn1.setDeltaGT(10.1);
		rcn2.setDeltaGT(20.2);
		rcn3.setDeltaGT(29.5);
		// q2
		rcn1.setKp(11);
		rcn2.setKp(10);
		rcn3.setKp(12);
		// q3
		rcn1.setPi(5);
		rcn2.setPi(1);
		rcn3.setPi(7);
		// q4
		rcn1.setDirectionOfReactions(QuestionsStage2.DirectionOfReactions.LEFT);
		rcn2.setDirectionOfReactions(QuestionsStage2.DirectionOfReactions.RIGHT);
		rcn3.setDirectionOfReactions(QuestionsStage2.DirectionOfReactions.RIGHT);

		correctJTFAnswers.add(rcn1);
		correctJTFAnswers.add(rcn2);
		correctJTFAnswers.add(rcn3);
	}

	public String checkAnswerJTFs(List<JTextField> jtfs, QuestionsStage2.PanelsTag question) {
		String res;
		switch (question) {
			case PANEL_1:
			case PANEL_2:
			case PANEL_3:
				res = checkEachInputField(jtfs, correctJTFAnswers, question);
				if (res.equals(SUCCESS_ANSWER)) break;
				else return res;

			case PANEL_5:
				// ...
				break;
		}
		return SUCCESS_ANSWER;
	}

	public String checkAnswerJCMBs(List<JComboBox> jcmbs, QuestionsStage2.PanelsTag question) {
		switch (question) {
			case PANEL_4:
				String res = checkAnswerForQuestion4(jcmbs, correctJTFAnswers);
				if (res.equals(SUCCESS_ANSWER)) break;
				else return res;
		}
		return SUCCESS_ANSWER;
	}


	/**
	 * Проверка каждого значения введенного пользрвателем (jtf) на предмет правильности.
	 * Если ответ для конкретной реакции правильный: возвращается {@link #SUCCESS_ANSWER} иначе
	 * имя поля для которого значение не правильное.
	 *
	 * @param jtfs список с введенными полями (значениями) пользователя
	 * @param correctAnswers список с правильными ответами
	 * @param question вопрос для которого проверятся правильность значений
	 * @return если ответ праваильный - {@link #SUCCESS_ANSWER} , иначе - реакция для которой ответ не верный
	 */
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

	/**
	 * Получение искомого значения согласно вопросу для реакциии.
	 *
	 * @param rcn реакция
	 * @param question вопрос
	 * @return значение
	 */
	private double getReactionCompareProperty(BasicReaction rcn, QuestionsStage2.PanelsTag question){
		switch (question) {
			case PANEL_1:
				return rcn.getDeltaGT();

			case PANEL_2:
				return rcn.getKp();

			case PANEL_3:
				return rcn.getPi();

			case PANEL_5:
				return 5;
		}
		return -1;
	}

	/**
	 * Проверка направления протекания реакций, только для вопроса 4.
	 *
	 * @param jcmboxes коллекция с выпадающими списками с установленными пользователем значениями.
	 * @param correctAnswers правильные ответы
	 * @return если ответ праваильный - {@link #SUCCESS_ANSWER} , иначе - реакция для которой ответ не верный
	 */
	private String checkAnswerForQuestion4(List<JComboBox> jcmboxes, List<BasicReaction> correctAnswers){
		for (BasicReaction correct : correctAnswers) {
			for (JComboBox jcmbox : jcmboxes) {
				if (correct.toString().equals(jcmbox.getName())) {
					if (!correct.getDirectionOfReactions().getName().equals(jcmbox.getSelectedItem().toString())) {
						return jcmbox.getName();
					}
				}
			}
		}
		return SUCCESS_ANSWER;
	}

	private void checkAnswerForQuestions5(List<JTextField> jtfs){

	}
}
