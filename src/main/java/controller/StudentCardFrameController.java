package controller;

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
	public void receiveAllElementsStage1(){
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

	public interface StudentCardFrameCallback {
		void receiveAllBasicElementsStage1(java.util.List<GeneralElementStage1> elements);
		void receiveAllAccessoryElementsStage1(java.util.List<GeneralElementStage1> elements);
	}

}
