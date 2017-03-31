package ui;

import controller.StartFrameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 *
 */
public class StartFrame implements StartFrameController.StartFrameCallback{

	public static final String FRAME_NAME = "FMPS";
	public static final String TXT_SIGN_IN = "Войти";
	public static final String TXT_NEW_REGISTER_NEW_STUDENT = "Новый студент";
	public static final String TXT_HELP = "Помощь";

	private JFrame frameStart = new JFrame(FRAME_NAME);
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
		jpMain.setLayout(new BoxLayout(jpMain, BoxLayout.Y_AXIS));
		JButton jbtnSignIn = new JButton(TXT_SIGN_IN);
		JButton jbtnNewStudent = new JButton(TXT_NEW_REGISTER_NEW_STUDENT);
		JButton jbtnHelp = new JButton(TXT_HELP);

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
