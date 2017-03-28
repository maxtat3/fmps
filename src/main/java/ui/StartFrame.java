package ui;

import app.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 *
 */
public class StartFrame implements SignInDialog.SignInDialogCallback{

	private JFrame frameStart;
	private StartFrame startFrame = this;

	public StartFrame() {
		frameStart = new JFrame("FMPS");
		frameStart.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addComponentToPane(frameStart.getContentPane());
		frameStart.pack();
		frameStart.setLocationRelativeTo(null);
		frameStart.setVisible(true);
	}

	private void addComponentToPane(Container contentPane) {
		JPanel jpMain = new JPanel();
		JButton jbtnSignIn = new JButton("Войти");
		JButton jbtnNewStudent = new JButton("Новый студент");
		JButton jbtnHelp = new JButton("Помощь");

		jbtnSignIn.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("move to next frame0");
				new SignInDialog(frameStart, startFrame);
//				closeThisWindow();
			}
		});

		jpMain.add(jbtnSignIn);
		contentPane.add(jpMain, BorderLayout.PAGE_START);
	}

	private void closeThisWindow(){
		frameStart.setVisible(false);
		frameStart.dispose();
	}


	@Override
	public void userSignInData(String lastName, char[] numOfRecBook) {
		System.out.println("lastName = " + lastName);
		String strNum = new String(numOfRecBook);
		if (Utils.isNumber(strNum)) {
			System.out.println("Number of record book = " + Integer.parseInt(strNum));
		}
	}
}
