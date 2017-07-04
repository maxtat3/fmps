package util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIUtils {

	public static void showAutoClosableMsgDialog(final String msg, int delayTimeMs) {
		final JLabel label = new JLabel();

		new Timer(delayTimeMs , new ActionListener() {
			int timeLeft = 1;
			@Override
			public void actionPerformed(ActionEvent e) {
				if (timeLeft > 0) {
					label.setText(msg);
					timeLeft--;
				} else {
					((Timer)e.getSource()).stop();
					Window win = SwingUtilities.getWindowAncestor(label);
					win.setVisible(false);
				}
			}
		}){{setInitialDelay(0);}}.start();

		JOptionPane.showMessageDialog(null, label);
	}

	public static void showAutoClosableLabel(final JLabel jlMsg, final String msg, int delayTimeMs) {
		final JLabel label = new JLabel();

		new Timer(delayTimeMs , new ActionListener() {
			int timeLeft = 1;
			@Override
			public void actionPerformed(ActionEvent e) {
				if (timeLeft > 0) {
					jlMsg.setText(msg);
					timeLeft--;
				} else {
					((Timer)e.getSource()).stop();
					jlMsg.setText("");
				}
			}
		}){{setInitialDelay(0);}}.start();
	}

}
