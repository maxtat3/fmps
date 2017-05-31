package controller;

import app.Accuracy;
import model.Container;
import stage1.elements.C;
import stage1.elements.Fe;
import stage1.elements.GeneralElementStage1;
import stage1.elements.Mn;
import ui.Stage1QuestionFrame;

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

	/**
	 * Check user answer on all questions.
	 *
	 * @param jtfElements text fields chemical elements with user filled data for checking
	 * @param question what be question is being checked
	 * @return {@link #SUCCESS_ANSWER} - if user gave a correct answer otherwise name of chemical element name
	 * is the answer for which is not correct
	 */
	public String checkAnswer(List<JTextField> jtfElements, Stage1QuestionFrame.PanelsTag question) {
		ArrayList<GeneralElementStage1> elements = new ArrayList<>();
		Fe fe = new Fe();
		C c = new C();
		Mn mn = new Mn();
		// q1
		fe.setMoleFractionAlloyElem(1);
		c.setMoleFractionAlloyElem(2);
		mn.setMoleFractionAlloyElem(5);
		// q2
		Container.getInstance().getStage1().getCalcDataStage1().setEnthalpyLiquidAlloy(15);
		Container.getInstance().getStage1().getCalcDataStage1().setEnthalpyVaporization(11);
		Container.getInstance().getStage1().getCalcDataStage1().setEnthalpyVapor(5);
		// q3
		fe.setVaporPressureOfPureComps(11);
		c.setVaporPressureOfPureComps(15);
		mn.setVaporPressureOfPureComps(55);

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
				for (JTextField jtfEl : jtfElements) {
					switch (jtfEl.getName()) {
						case Stage1QuestionFrame.ENT_LQ_ALLOY_SYM:
							if (!Accuracy.checkValueInRange(
								Container.getInstance().getStage1().getCalcDataStage1().getEnthalpyLiquidAlloy(),
								Double.parseDouble(jtfEl.getText()),
								Accuracy.ACCURACY)
								) {
								return jtfEl.getName();
							}
							break;

						case Stage1QuestionFrame.ENT_VAPORIZATION_SYM:
							if (!Accuracy.checkValueInRange(
								Container.getInstance().getStage1().getCalcDataStage1().getEnthalpyVaporization(),
								Double.parseDouble(jtfEl.getText()),
								Accuracy.ACCURACY)
								) {
								return jtfEl.getName();
							}
							break;

						case Stage1QuestionFrame.ENT_VAPOUR_SYM:
							if (!Accuracy.checkValueInRange(
								Container.getInstance().getStage1().getCalcDataStage1().getEnthalpyVapor(),
								Double.parseDouble(jtfEl.getText()),
								Accuracy.ACCURACY)
								) {
								return jtfEl.getName();
							}
							break;
					}
				}
				break;

			case PANEL_5:
				//...
				break;

			case PANEL_9:
				//...
				break;
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
	                                Stage1QuestionFrame.PanelsTag question) {
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
	private double getElementCompareProperty(GeneralElementStage1 el, Stage1QuestionFrame.PanelsTag question){
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
}
