package controller;

import app.Utils;
import db.DBUtils;
import ui.SignInDialog;
import ui.StartFrame;

import javax.swing.*;

/**
 * Controller for {@link StartFrame} ui class
 */
public class StartFrameController implements SignInDialog.SignInDialogCallback {

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
				System.out.println("open select task frame.");
				uiFrame.closeThisFrame();
			} else {
				uiFrame.setMsg("Такой пользователь ранее не был зарегестрирован !");
			}
		}
	}

	private boolean validateSigInUserInputData(String name, String number) {
		if (Utils.isEmpty(name)) {
			uiFrame.setMsg("Поле Фамилия не должно быть пустым !");
			return false;
		}
		if (Utils.isEmpty(number)) {
			uiFrame.setMsg("Поле Номер зачетной книжки не должно быть пустым !");
			return false;
		}
		if (!Utils.isLettersOnly(name)) {
			uiFrame.setMsg("В поле Фамилия разрешены только буквы !");
			return false;
		}
		if (!Utils.isNumber(number)) {
			uiFrame.setMsg("В поле Номер зачетной книжки разрешены только цифры !");
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
