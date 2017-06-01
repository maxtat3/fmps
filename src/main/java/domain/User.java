package domain;

public class User {
	private int id; // id пользователя в БД
	private String firstName; // Имя
	private String middleName; // Отчество
	private String lastName; // Фамилия
	private int numberOfRecordBook; // Номер зачетной книжки


	/**
	 * User of this app
	 *
	 * @param firstName Имя
	 * @param middleName Отчество
	 * @param lastName Фамилия
	 * @param numberOfRecordBook Номер зачетной книжки
	 */
	public User(String firstName, String middleName, String lastName, int numberOfRecordBook) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.numberOfRecordBook = numberOfRecordBook;
	}

	/**
	 * User of this app
	 *
	 * @param id id пользователя в БД
	 * @param firstName Имя
	 * @param middleName Отчество
	 * @param lastName Фамилия
	 * @param numberOfRecordBook Номер зачетной книжки
	 */
	public User(int id, String firstName, String middleName, String lastName, int numberOfRecordBook) {
		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.numberOfRecordBook = numberOfRecordBook;
	}

	/**
	 * User of this app
	 *
	 * @param lastName Фамилия
	 * @param numOfRecBook Номер зачетной книжки
	 */
	public User(String lastName, int numOfRecBook) {
		this.lastName = lastName;
		this.numberOfRecordBook = numOfRecBook;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getNumberOfRecordBook() {
		return numberOfRecordBook;
	}

	public void setNumberOfRecordBook(int numberOfRecordBook) {
		this.numberOfRecordBook = numberOfRecordBook;
	}
}
