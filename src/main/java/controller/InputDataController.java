package controller;

import db.DBUtils;
import model.Container;
import stage1.elements.BaseElementStage1;
import stage1.elements.GeneralElementStage1;
import stage2.elements.GeneralElementStage2;
import util.Utils;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Общий контроллер для всех этапов.
 */
public class InputDataController {

	/**
	 * Основные элементы задачи 1
	 */
	private java.util.List<GeneralElementStage1> basicElementsStage1;

	/**
	 * Дополнительне (легирующие) элементы задачи 1
	 */
	private java.util.List<GeneralElementStage1> accessoryElementsStage1;


	public InputDataController() {
		separateElementsStage1();
	}


	/**
	 * Выполнение разделения всех элементов на основные и дополнительные (легирующие).
	 */
	private void separateElementsStage1(){
		basicElementsStage1 = new ArrayList<>();
		accessoryElementsStage1 = new ArrayList<>();
		for(GeneralElementStage1 el : model.Container.getInstance().getStage1().getElements().getAllElements()) {
			if (((BaseElementStage1) el).isBasic()) {
				basicElementsStage1.add(el);
			} else {
				accessoryElementsStage1.add(el);
			}
		}
	}

	/**
	 * Получить только основные элементы задачи 1.
	 *
	 * @return список осноыных химичесиких элементов
	 */
	public List<GeneralElementStage1> receiveBasicElementsStage1() {
		return basicElementsStage1;
	}

	/**
	 * Получить только дополнительне (легирующие) элементы задачи 1.
	 *
	 * @return список дополнительных (легирующих) химичесиких элементов
	 */
	public List<GeneralElementStage1> receiveAccessoryElementsStage1() {
		return accessoryElementsStage1;
	}

	/**
	 * Получение только тех химических элементов которые заданы пользователю в задании для задачи 1.
	 *
	 * @param access варианты получения элементов
	 * @return список элементов полученных иходя из варианта {@link AccessElementsStage1}
	 */
	public List<GeneralElementStage1> receiveUserTaskElementsStage1(AccessElementsStage1 access) {
		Container.Stage1.Elements data = DBUtils.getMainMsrDataStage1(1);
		Container.getInstance().getStage1().setElements(data);

		List<GeneralElementStage1> elements = new ArrayList<>();
		for(GeneralElementStage1 el : model.Container.getInstance().getStage1().getElements().getAllElements()) {
			if (access == AccessElementsStage1.TASK && el.getAlloyCompWeight() != BaseElementStage1.ELEM_NOT_DEFINED) {
				elements.add(el);
			} else if (access == AccessElementsStage1.ALL) {
				elements.add(el);
			}
		}
		return elements;
	}

	/**
	 * Варианты получения элеметов для метода {@link #receiveUserTaskElementsStage1(AccessElementsStage1)}:
	 * - {@link AccessElementsStage1#ALL} - получить список всех элементов
	 * - {@link AccessElementsStage1#TASK} - получить список только у которых задана массовая доля вещества {@link BaseElementStage1#alloyCompWeight}
	 */
	public enum AccessElementsStage1 {
		ALL, TASK
	}


	/**
	 * Получить все смеси газов для задачи 2.
	 *
	 * @return полный список газов
	 */
	public List<GeneralElementStage2> receiveGasesStage2() {
		List<GeneralElementStage2> gases = new ArrayList<>();
		for (GeneralElementStage2 el : Container.getInstance().getStage2().getAllElements()) {
			gases.add(el);
		}
		return gases;
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
		return SUCCESS_VALIDATE;
	}

	public enum ValidatorVariant {
		IS_TEXT, IS_NUMBER
	}

	public static final String SUCCESS_VALIDATE = "0";
}
