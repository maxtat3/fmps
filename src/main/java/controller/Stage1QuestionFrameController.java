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

		elements.add(fe);
		elements.add(c);
		elements.add(mn);

		switch (question) {
			case PANEL_1:
				for (GeneralElementStage1 calcEl : elements) {
					for (JTextField jtfEl : jtfElements) {
						if (calcEl.toString().equals(jtfEl.getName())) {
							if (!Accuracy.checkValueInRange(calcEl.getMoleFractionAlloyElem(), Double.parseDouble(jtfEl.getText()), Accuracy.ACCURACY)) {
								return jtfEl.getName();
							}
						}
					}
				}
				break;

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
		}
		System.out.println("Increment user progress in DB from user id.");
		return SUCCESS_ANSWER;
	}
}
