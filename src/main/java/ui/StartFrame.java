package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 *
 */
public class StartFrame {

	private JFrame frameStart;

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
				new SignInDialog(frameStart);
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
}
