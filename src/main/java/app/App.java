package app;


import db.DBUtils;
import ui.StartFrame;
import javax.swing.*;


public class App {
	public static void main(String[] args) {
//		new Calc();
		DBUtils.initDatabase();
//		DBUtils.addNewUser("Alex", "Semenovich", "Tanvov", 15701);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new App().startUI();
			}
		});
	}

	private void startUI() {
		new StartFrame();
	}
}
