package backend;

public class Supplier{
	
	private String Name;
	private String Contact;
	private String Email;

	public Supplier(String name, String contact, String email){
		this.Name = name;
		this.Contact = contact;
		this.Email = email;
	}

	public String getName(){
		
		return this.Name;
	}
	public String getContact(){
		
		return this.Contact;
	}
	public String getEmail(){
		
		return this.Email;
	}
	
	public int setName(User u, String newName){
		//verificar se user é Admin
		/*
			return -1 caso não seja
		*/

		this.Name = newName;
		return 0;
	}
	public int setContact(User u, String newContact){
		//verificar se user é Admin
		/*
			return -1 caso não seja
		*/

		this.Contact = newContact;
		return 0;
	}
	public int setEmail(User u, String newEmail){
		//verificar se user é Admin
		/*
			return -1 caso não seja
		*/

		this.Email = newEmail;
		return 0;
	}
	

}