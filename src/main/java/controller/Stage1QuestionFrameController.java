package controller;

import app.Accuracy;
import model.Container;
import stage1.elements.C;
import stage1.elements.Fe;
import stage1.elements.GeneralElementStage1;
import stage1.elements.Mn;
import ui.QuestionsStage1;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Stage1QuestionFrameController extends InputDataController {

	/**
	 * Returned string result for {@link Stage1QuestionFrameController#checkAnswer} method.
	 * If all elements has true results, returned value determined in this const.
	 * Otherwise returned element name who has a wrong value.
	 */
	public static final String SUCCESS_ANSWER = "0";

	// TODO: 13.06.17 не забыть поуюирать заглушки при проверке правильных ответов, а также добавить все элементы
	/**
	 * Check user answer on all questions.
	 *
	 * @param jtfElements text fields chemical elements with user filled data for checking
	 * @param question what be question is being checked
	 * @return {@link #SUCCESS_ANSWER} - if user gave a correct answer otherwise name of chemical element name
	 * is the answer for which is not correct
	 */
	public String checkAnswer(List<JTextField> jtfElements, QuestionsStage1.PanelsTag question) {
		ArrayList<GeneralElementStage1> elements = new ArrayList<>();
		Fe fe = new Fe();
		C c = new C();
		Mn mn = new Mn();
		// q1
		fe.setMoleFractionAlloyElem(1);
		c.setMoleFractionAlloyElem(2);
		mn.setMoleFractionAlloyElem(5);
		// q2
		Container.getInstance().getStage1().getCommonCalculatedData().setEnthalpyLiquidAlloyF2p1(15);
		Container.getInstance().getStage1().getCommonCalculatedData().setEnthalpyVaporizationF2p2(11);
		Container.getInstance().getStage1().getCommonCalculatedData().setEnthalpyVaporF2p3(5);
		// q3
		fe.setVaporPressureOfPureComps(11);
		c.setVaporPressureOfPureComps(15);
		mn.setVaporPressureOfPureComps(55);
		// q5
		Container.getInstance().getStage1().getCommonCalculatedData().setVaporPressureOverAlloyF5(12.177);
		// q9
		Container.getInstance().getStage1().getCommonCalculatedData().setDecreaseMoltenMetalDueVaporizationF9(556.1);

		elements.add(fe);
		elements.add(c);
		elements.add(mn);

		String res;
		switch (question) {
			case PANEL_1:
			case PANEL_3:
			case PANEL_4:
			case PANEL_6:
			case PANEL_7:
			case PANEL_8:
				res = checkEachElement(jtfElements, elements, question);
				if (res.equals(SUCCESS_ANSWER)) break;
				else return res;

			case PANEL_2:
				res = checkAnswerForQuestion2(jtfElements);
				if (res.equals(SUCCESS_ANSWER)) break;
				else return res;

			case PANEL_5:
				res = checkAnswerForQuestion5(jtfElements);
				if (res.equals(SUCCESS_ANSWER)) break;
				else return res;

			case PANEL_9:
				res = checkAnswerForQuestion9(jtfElements);
				if (res.equals(SUCCESS_ANSWER)) break;
				else return res;
		}
		System.out.println("Increment user progress in DB from user id.");
		return SUCCESS_ANSWER;
	}

	/**
	 * Enumeration and check each of chemical element with using accuracy.
	 *
	 * @param jtfs list of user entered values
	 * @param calcElementsValues reference calculated values
	 * @return {@link #SUCCESS_ANSWER} if all elements corrected otherwise returned element name
	 *
	 * @see Accuracy
	 */
	private String checkEachElement(List<JTextField> jtfs,
	                                List<GeneralElementStage1> calcElementsValues,
	                                QuestionsStage1.PanelsTag question) {
		for (GeneralElementStage1 calcElVal : calcElementsValues) {
			for (JTextField jtfEl : jtfs) {
				if (calcElVal.toString().equals(jtfEl.getName())) {
					if (!Accuracy.checkValueInRange(
						getElementCompareProperty(calcElVal, question),
						Double.parseDouble(jtfEl.getText()), Accuracy.ACCURACY)
						) {
						return jtfEl.getName();
					}
				}
			}
		}
		return SUCCESS_ANSWER;
	}

	// TODO: 31.05.17 may be refactored this mechanism
	// only for questions 1, 3, 4, 6. 7. 8
	// if not correct present question panel - returned -1
	private double getElementCompareProperty(GeneralElementStage1 el, QuestionsStage1.PanelsTag question){
		switch (question) {
			case PANEL_1:
				return el.getMoleFractionAlloyElem();

			case PANEL_3:
				return el.getVaporPressureOfPureComps();

			case PANEL_4:
				return el.getPartialPressureCompsOverAlloy();

			case PANEL_6:
				return el.getMoleFractionEachElemInVapor();

			case PANEL_7:
				return el.getWeightFractionEachElemInVapor();

			case PANEL_8:
				return el.getRateVaporizationEachElemOfWeldPool();
		}
		return -1;
	}

	private String checkAnswerForQuestion2(List<JTextField> jtfs){
		double entLqAlloy = Container.getInstance().getStage1().getCommonCalculatedData().getEnthalpyLiquidAlloyF2p1(); //2.1
		double entVaporization = Container.getInstance().getStage1().getCommonCalculatedData().getEnthalpyVaporizationF2p2(); //2.2
		double entVapor = Container.getInstance().getStage1().getCommonCalculatedData().getEnthalpyVaporF2p3(); //2.3

		for (JTextField jtfEl : jtfs) {
			switch (jtfEl.getName()) {
				case QuestionsStage1.ENT_LQ_ALLOY_SYM:
					if (! Accuracy.checkValueInRange(entLqAlloy, Double.parseDouble(jtfEl.getText()), Accuracy.ACCURACY)) {
						return jtfEl.getName();
					}
					break;

				case QuestionsStage1.ENT_VAPORIZATION_SYM:
					if (! Accuracy.checkValueInRange(entVaporization, Double.parseDouble(jtfEl.getText()), Accuracy.ACCURACY)) {
						return jtfEl.getName();
					}
					break;

				case QuestionsStage1.ENT_VAPOUR_SYM:
					if (! Accuracy.checkValueInRange(entVapor, Double.parseDouble(jtfEl.getText()), Accuracy.ACCURACY)) {
						return jtfEl.getName();
					}
					break;
			}
		}
		return SUCCESS_ANSWER;
	}

	private String checkAnswerForQuestion5(List<JTextField> jtfs){
		double vapPrOverAlloy = Container.getInstance().getStage1().getCommonCalculatedData().getVaporPressureOverAlloyF5();

		for (JTextField jtfEl : jtfs) {
			switch (jtfEl.getName()) {
				case QuestionsStage1.VAP_PRES_OVER_ALLOY_SYM:
					if (! Accuracy.checkValueInRange(vapPrOverAlloy, Double.parseDouble(jtfEl.getText()), Accuracy.ACCURACY)) {
						return jtfEl.getName();
					}
					break;
			}
		}
		return SUCCESS_ANSWER;
	}

	private String checkAnswerForQuestion9(List<JTextField> jtfs) {
		double decMetalVap = Container.getInstance().getStage1().getCommonCalculatedData().getDecreaseMoltenMetalDueVaporizationF9();

		for (JTextField jtfEl : jtfs) {
			switch (jtfEl.getName()) {
				case QuestionsStage1.DECR_MOLTEN_METAL_DUE_VAP_SYM:
					if (! Accuracy.checkValueInRange(decMetalVap, Double.parseDouble(jtfEl.getText()), Accuracy.ACCURACY)) {
						return jtfEl.getName();
					}
					break;
			}
		}
		return SUCCESS_ANSWER;
	}
}
