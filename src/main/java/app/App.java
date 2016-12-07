package app;


import ui.Stage1Question1Frame;

import javax.swing.*;

public class App {
	public static void main(String[] args) {

		Stage1 stage1 = new Stage1();

		double checkAllElemSum = stage1.checkAllElementsSum();
		System.out.println("checkAllElemSum = " + checkAllElemSum);

		double[] moles = stage1.moleFractionAllElements();
		for (double mole : moles) {
			System.out.println("moles = " + mole * 100);
		}

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
