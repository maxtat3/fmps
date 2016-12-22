package app;


import ui.UserInitDataStage1Frame;

import javax.swing.*;


public class App {
	public static void main(String[] args) {
//		new Calc();

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new App().startUI();
			}
		});
	}

	private void startUI() {
		new UserInitDataStage1Frame();
	}
}
