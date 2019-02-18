import java.util.ArrayList;
import java.util.Scanner;
import java.io.ObjectOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ContactDatabase {
	private static ArrayList<Contact> contacts;
	private static Scanner keyboard = new Scanner(System.in);

	public static ArrayList<Contact> search(String sT) {
		ArrayList<Contact> listOfFoundContacts = new ArrayList<Contact>();
		for(Contact c : contacts) {
			if(c.getFirstName().contains(sT) || c.getLastName().contains(sT)
					|| c.getPhoneNumber().contains(sT) || c.getEmail().contains(sT)) {
				listOfFoundContacts.add(c);
			}
		}
		
		return listOfFoundContacts;
	}
	
	
	public static void main(String[] args) {
		String fName, lName, ph, email, delete, sT;
		ObjectInputStream inputStream = null;
		ObjectOutputStream outputStream = null;
		contacts = new ArrayList<Contact>();
		


		String username = System.getProperty("user.name");



		// create the input stream
		try {
			inputStream = new ObjectInputStream (
					new FileInputStream("/home/" + username + "/contacts.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// import the file
		while(true) {
			try {
				System.out.println("Loading contact from file");
				fName = inputStream.readUTF();
				System.out.println("Loaded first name");
				lName = inputStream.readUTF();
				System.out.println("Loaded last name");
				ph = inputStream.readUTF();
				System.out.println("Loading phone num.");
				email = inputStream.readUTF();
				System.out.println("Loaded email");
				System.out.println("Loading contact from file");
				contacts.add(new Contact(fName, lName, ph, email));
				
			} catch (EOFException e) {
				System.out.println("EOF");
				break;
			} catch (NullPointerException e) {
				System.out.println("Not reading the file since it does not exist yet.");
				break;
			} catch (IOException e) {
				System.out.print(e.getMessage());
				System.exit(0);
			}
		}
		
		// close the file
		try {
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("File /home/" + username + "contacts.txt doesn't exist");
		}
		
		
		
		while(true) {
			System.out.print("add / show / search / delete / exit: ");
			String input = keyboard.next();
			System.out.println();
			

			switch(input) {
			case "add":
				System.out.print("First Name: ");
				keyboard.nextLine();
				fName = keyboard.nextLine();
				
				System.out.print("Last Name: ");
				lName = keyboard.nextLine();
				System.out.println();
				
				System.out.print("Phone: ");
				ph = keyboard.nextLine();
				System.out.println();
				
				System.out.print("Email: ");
				email = keyboard.nextLine();
				System.out.println();
				
				contacts.add(new Contact(fName, lName, ph, email));
				//System.out.println(newContact.toString());

				break;
			case "show":
				for(Contact c : contacts) {
					System.out.println(c.toString());
				}

				break;
			
			case "search":
				System.out.print("Enter a string to search for: ");
				keyboard.nextLine();
				sT = keyboard.nextLine(); // search term 
				System.out.println();
				for(Contact c : search(sT)) {
					System.out.println(c.toString());
				}
				break;
				
			case "delete":
				System.out.print("Enter a string to search for: ");
				keyboard.nextLine();
				sT = keyboard.nextLine(); // search term 
				System.out.println();
				
				for(Contact c : search(sT)) {
					System.out.println(c.toString());
					System.out.print("Delete?: (y/n) ");
					delete = keyboard.next();
					if(delete.equals("y") || delete.equals("yes")) {
						contacts.remove(c);
						System.out.println("Contact deleted");
					}
				}
				break;
			
			case "exit":
				try {
					outputStream = new ObjectOutputStream(
							new FileOutputStream("/home/lachie/contacts.txt"));
					System.out.println("Exiting...");
					for(Contact c : contacts) {
						System.out.println("Writing contact to file");
						c.writeToFile(outputStream);
					}
					outputStream.close();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("Critical error writing or closing file");
					e.printStackTrace();
				}
				System.exit(0);
			break;
			}
		}
		
	}
	
	
}
