package controller;

import db.DBUtils;
import domain.User;

/**
 *
 */
public class StudentCardFrameController extends InputDataController {

	public StudentCardFrameController() {
	}


	/**
	 * Save user data in internal app DB
	 */
	public void saveUser(User user){
		DBUtils.addNewUser(user.getFirstName(), user.getMiddleName(), user.getLastName(), user.getNumberOfRecordBook());
	}
}
