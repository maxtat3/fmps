package ui;

import controller.StartFrameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 *
 */
public class StartFrame implements StartFrameController.StartFrameCallback{

	private JFrame frameStart = new JFrame("FMPS");
	private JLabel jlMsg = new JLabel();
	private StartFrameController controller;

	public StartFrame() {
		frameStart.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addComponentToPane(frameStart.getContentPane());
		frameStart.pack();
		frameStart.setLocationRelativeTo(null);
		frameStart.setVisible(true);

		controller = new StartFrameController(frameStart, this);
	}

	private void addComponentToPane(Container contentPane) {
		JPanel jpMain = new JPanel();
		JButton jbtnSignIn = new JButton("Войти");
		JButton jbtnNewStudent = new JButton("Новый студент");
		JButton jbtnHelp = new JButton("Помощь");

		jbtnSignIn.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.showDialog();
			}
		});

		jpMain.add(jbtnSignIn);
		jpMain.add(jlMsg);
		contentPane.add(jpMain, BorderLayout.PAGE_START);
	}

	private void closeThisWindow(){
		frameStart.setVisible(false);
		frameStart.dispose();
	}


	@Override
	public void closeThisFrame() {
		closeThisWindow();
	}

	@Override
	public void setMsg(String msg) {
		jlMsg.setText(msg);
		frameStart.pack();
	}
}
