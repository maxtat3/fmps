package controller;

import app.Utils;
import stage1.elements.BaseElementStage1;
import stage1.elements.GeneralElementStage1;
import ui.StudentCardFrame;

import javax.swing.*;
import java.util.ArrayList;

/**
 *
 */
public class StudentCardFrameController {

	private JFrame jFrame;
	private StudentCardFrame studentCardFrame;

	/**
	 * Все основные элементы задачи 1
	 */
	private java.util.List<GeneralElementStage1> basicElementsStage1;

	/**
	 * Все дополнительне элементы задачи 1
	 */
	private java.util.List<GeneralElementStage1> accessoryElementsStage1;


	public StudentCardFrameController(JFrame jFrame, StudentCardFrame studentCardFrame) {
		this.jFrame = jFrame;
		this.studentCardFrame = studentCardFrame;

		receiveAllElementsStage1();
		studentCardFrame.receiveAllBasicElementsStage1(basicElementsStage1);
		studentCardFrame.receiveAllAccessoryElementsStage1(accessoryElementsStage1);
	}

	/**
	 * Получение всех элементов для задачи 1 из БД
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


	public interface StudentCardFrameCallback {
		void receiveAllBasicElementsStage1(java.util.List<GeneralElementStage1> elements);
		void receiveAllAccessoryElementsStage1(java.util.List<GeneralElementStage1> elements);
	}

}
