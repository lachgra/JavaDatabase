import java.io.IOException;
import java.io.ObjectOutputStream;

public class Contact {
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String emailAddress;
	
	
	public Contact(String fName, String lName, String ph, String email) {
		this.firstName = fName;
		this.lastName = lName;
		this.phoneNumber = ph;
		this.emailAddress = email;
	}
	
	public void writeToFile(ObjectOutputStream outputStream) {
		try {
			outputStream.writeUTF(firstName);
			outputStream.writeUTF(lastName);
			outputStream.writeUTF(phoneNumber);
			outputStream.writeUTF(emailAddress);
		} catch (IOException e) {
			System.out.println("Unhandled IO Exception while writing to file");
			System.out.println(e.getMessage());
			//System.exit(0);
		}
	}
	
	/**
	 * All the fields are Strings, therefore they are immutable
	 * and can be returned directly
	 *
	 */
	public String getFirstName() {
		return this.firstName;
	}
	public String getLastName() {
		return this.lastName;
	}
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	public String getEmail() {
		return this.emailAddress;
	}
	

	public void setFirstName(String fName) {
		this.firstName = fName;
	}
	public void setLastName(String lName) {
		this.lastName = lName;
	}
	public void setPhoneNumber(String ph) {
		this.phoneNumber = ph;
	}
	public void setEmail(String email) {
		this.emailAddress = email;
	}
	
	/* Convert to string */
	public String toString() {
		return "name = " + firstName + " last name = " + lastName + " phone = " + phoneNumber 
							+ " email = " + emailAddress;
	}



}
