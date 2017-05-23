package controller;

import app.Utils;
import db.DBUtils;
import domain.User;
import model.Container;
import stage1.elements.BaseElementStage1;
import stage1.elements.GeneralElementStage1;
import stage2.elements.GeneralElementStage2;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class StudentCardFrameController {

	/**
	 * Все основные элементы задачи 1
	 */
	private java.util.List<GeneralElementStage1> basicElementsStage1;

	/**
	 * Все дополнительне элементы задачи 1
	 */
	private java.util.List<GeneralElementStage1> accessoryElementsStage1;

	/**
	 * Элементы (смеси газов) для задачи 2
	 */
	private List<GeneralElementStage2> gasesStage2;


	public StudentCardFrameController() {
		receiveAllElementsStage1();
		receiveAllGasesStage2();
	}


	/**
	 * Получение всех химических элементов из контейнера для задачи 1
	 */
	private void receiveAllElementsStage1(){
		basicElementsStage1 = new ArrayList<>();
		accessoryElementsStage1 = new ArrayList<>();
		for(GeneralElementStage1 el : model.Container.getInstance().getStage1().getAllElements()) {
			if (((BaseElementStage1) el).isBasic()) {
				basicElementsStage1.add(el);
			} else {
				accessoryElementsStage1.add(el);
			}
		}
	}

	private void receiveAllGasesStage2() {
		gasesStage2 = new ArrayList<>();
		for (GeneralElementStage2 el : Container.getInstance().getStage2().getAllElements()) {
			gasesStage2.add(el);
		}
	}

	public List<GeneralElementStage1> receiveBasicElementsStage1() {
		return basicElementsStage1;
	}

	public List<GeneralElementStage1> receiveAccessoryElementsStage1() {
		return accessoryElementsStage1;
	}

	public List<GeneralElementStage2> receiveGasesStage2() {
		return gasesStage2;
	}

	/**
	 * Verification of entered in JTextField data by user.
	 * Check field at: is not empty, is contains letters only, is contains number only.
	 *
	 * @param data reference to validation JTextField
	 * @param variant validation variants. All variants see in {@link ValidatorVariant} enum.
	 * @return if validation success - returned "0" in text representation,
	 *          otherwise return html formatted string with explanation input data problem.
	 *          This returned text must be showed to user in any massage label.
	 */
	public String validateInputData(JTextField data, ValidatorVariant variant) {
		String content = data.getText();
		String name = data.getName().replace(":", "");

		if (Utils.isEmpty(content)) return "<html><font color=red>Поле <i>" + name + "</i> не должно быть пустым !</font></html>";

		switch (variant) {
			case IS_TEXT:
				if (!Utils.isLettersOnly(content)) return "<html><font color=red>В поле <i>" + name + "</i> разрешены только буквы !</font></html>";
				break;

			case IS_NUMBER:
				if (!Utils.isNumber(content)) return "<html><font color=red>В поле <i>" + name + "</i> разрешены только цифры !</font></html>";
				break;
		}
		return "0";
	}

	public enum ValidatorVariant {
		IS_TEXT, IS_NUMBER
	}

	/**
	 * Save user data in internal app DB
	 */
	public void saveUser(User user){
		DBUtils.addNewUser(user.getFirstName(), user.getMiddleName(), user.getLastName(), user.getNumberOfRecordBook());
	}
}
