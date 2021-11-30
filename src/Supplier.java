package backend;

class Supplier{
	
	private String Name;
	private String Contact;
	private String Email;
	private Location Loc;

	public Supplier(String name, String contact, String email, Location loc){
		this.Name = name;
		this.Contact = contact;
		this.Email = email;
		this.Loc = loc;
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
	public Location getLocation(){
		
		return this.Loc;
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
	public int setLocation(User u, Location newLocation){
		//verificar se user é Admin
		/*
			return -1 caso não seja
		*/

		this.Loc = newLocation;
		return 0;
	}

}