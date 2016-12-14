package app;


import stage1.calc.Calc;
import ui.Stage1Question1Frame;

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
		new Stage1Question1Frame();
	}
}
