package app;


import db.DBUtils;
import ui.StudentCardFrame;

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
		new StudentCardFrame();
	}
}
