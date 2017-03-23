package ui;

import javax.swing.*;


/**
 * Диалог для входа пользователя в свою учетную запись
 */
public class SignInDialog extends JPanel {

	public SignInDialog(JFrame rootFrame) {
		JTextField jtfFirstName = new JTextField();
		JTextField jtfLastName = new JTextField();
		JTextField jtfMiddleName = new JTextField();
		JPasswordField jpswNumberOfRecordBook = new JPasswordField();
		JLabel jlMsg = new JLabel("Not valid data");
		jlMsg.setVisible(false);

		final JComponent[] inputs = new JComponent[]{
			new JLabel("Имя: "),
			jtfFirstName,
			new JLabel("Фамилия: "),
			jtfLastName,
			new JLabel("Отчество: "),
			jtfMiddleName,
			new JLabel("Номер зачетной книжки: "),
			jpswNumberOfRecordBook,
			jlMsg
		};

		int result = JOptionPane.showConfirmDialog(rootFrame, inputs, "My custom dialog", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {

		} else {
			System.out.println("User canceled / closed the dialog, result = " + result);
		}
	}
}
