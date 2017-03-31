package controller;

import app.Utils;
import db.DBUtils;
import ui.SignInDialog;
import ui.StartFrame;
import ui.StudentCardFrame;

import javax.swing.*;

/**
 * Controller for {@link StartFrame} ui class
 */
public class StartFrameController implements SignInDialog.SignInDialogCallback {

	public static final String TXT_USER_DOT_PREV_REGISTERED = "Такой пользователь ранее не был зарегестрирован !";
	public static final String TXT_LAST_NAME_IS_EMPTY = "Поле Фамилия не должно быть пустым !";
	public static final String TXT_NUM_OF_REC_BOOK_IS_EMPTY = "Поле Номер зачетной книжки не должно быть пустым !";
	public static final String TXT_IN_LAST_NAME_ALLOW_LETTERS = "В поле Фамилия разрешены только буквы !";
	public static final String IN_NUM_OF_REC_BOOK_ALLOW_NUMBERS = "В поле Номер зачетной книжки разрешены только цифры !";

	private JFrame jFrame;
	private StartFrame uiFrame;


	public StartFrameController(JFrame jFrame, StartFrame uiFrame) {
		this.jFrame = jFrame;
		this.uiFrame = uiFrame;
	}

	@Override
	public void userSignInData(String lastName, char[] numberOfRecordBook) {
		boolean isCorrectInputData = validateSigInUserInputData(lastName, new String(numberOfRecordBook));
		if (isCorrectInputData) {
			boolean isUserPresentInDB = DBUtils.findUser(lastName, Integer.parseInt(new String(numberOfRecordBook)));
			if (isUserPresentInDB) {
				new StudentCardFrame();
				uiFrame.closeThisFrame();
			} else {
				uiFrame.setMsg(TXT_USER_DOT_PREV_REGISTERED);
			}
		}
	}

	private boolean validateSigInUserInputData(String name, String number) {
		if (Utils.isEmpty(name)) {
			uiFrame.setMsg(TXT_LAST_NAME_IS_EMPTY);
			return false;
		}
		if (Utils.isEmpty(number)) {
			uiFrame.setMsg(TXT_NUM_OF_REC_BOOK_IS_EMPTY);
			return false;
		}
		if (!Utils.isLettersOnly(name)) {
			uiFrame.setMsg(TXT_IN_LAST_NAME_ALLOW_LETTERS);
			return false;
		}
		if (!Utils.isNumber(number)) {
			uiFrame.setMsg(IN_NUM_OF_REC_BOOK_ALLOW_NUMBERS);
			return false;
		}
		return true;
	}

	public void showDialog() {
		new SignInDialog(jFrame, this);
	}

	/**
	 * Call UI methods from {@link #uiFrame} variable.
	 */
	public interface StartFrameCallback {
		/**
		 * Stop and close this frame
		 */
		void closeThisFrame();

		/**
		 * Set massage in this frame
		 * @param msg massage
		 */
		void setMsg(String msg);
	}
}
