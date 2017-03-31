package ui;

import javax.swing.*;


/**
 * Диалог для входа пользователя в свою учетную запись
 */
public class SignInDialog extends JPanel {

	public SignInDialog(JFrame rootFrame, SignInDialogCallback callback) {
		JTextField jtfLastName = new JTextField();
		JPasswordField jpswNumberOfRecordBook = new JPasswordField();

		final JComponent[] inputs = new JComponent[]{
			new JLabel("Фамилия: "),
			jtfLastName,
			new JLabel("Номер зачетной книжки: "),
			jpswNumberOfRecordBook,
		};

		int result = JOptionPane.showConfirmDialog(rootFrame, inputs, "My custom dialog", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			callback.userSignInData(jtfLastName.getText(), jpswNumberOfRecordBook.getPassword());
		} else {
			System.out.println("User canceled / closed the dialog, result = " + result);
		}
	}

	public interface SignInDialogCallback {
		void userSignInData(String lastName, char[] numberOfRecordBook);
	}
}
