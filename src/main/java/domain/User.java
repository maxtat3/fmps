package domain;

public class User {
	private String firstName; // Имя
	private String middleName; // Отчество
	private String lastName; // Фамилия
	private int numberOfRecordBook; // Номер зачетной книжки


	public User(String firstName, String middleName, String lastName, int numberOfRecordBook) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.numberOfRecordBook = numberOfRecordBook;
	}

	public User(String lastName, int numOfRecBook) {
		this.lastName = lastName;
		this.numberOfRecordBook = numOfRecBook;
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
