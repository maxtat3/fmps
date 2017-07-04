package app;


import db.DBUtils;
import domain.User;
import ui.Stage1QuestionFrame;

import javax.swing.*;


public class App {
	public static void main(String[] args) {
		DBUtils.initDatabase();

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new App().startUI();
			}
		});
	}

	private void startUI() {
//		new StartFrame();
//		new Stage1QuestionFrame(new User("Имя..........", "Отчество.......", "Фамилия......" , 17554187));
//		new StudentCardFrame();
//		new ChartsExporterFrame();


		// show chart data
//		ReferenceCalculationsStage1 ref = new ReferenceCalculationsStage1();
////		ref.performRefCalcStage1(new User(1, "Имя..........", "Отчество.......", "Фамилия......" , 155));
//		ref.performRefCalcStage1(new User(2, "Имя", "Отчество", "Фамилия" , 834563));
//		new ChartsExporterFrame();

		new Stage1QuestionFrame(new User(1, "My name", "Отчество", "Фамилия", 834563), Stage1QuestionFrame.Stage.STAGE_1);
	}
}
