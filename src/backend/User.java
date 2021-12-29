package backend;

public class User{

	private String Name;
	private int YearBirth;
	private char Sex;
	private String Contact;
	private String Email;
	private String Role;
	private Warehouse W;

	private User(Warehouse w, String name, int yearBirth, char sex, String contact, String email, String role){
		this.Name = name;
		this.YearBirth = yearBirth;
		this.Sex = sex;
		this.Contact = contact;
		this.Email = email;
		this.Role = role;
		this.W = w;
	}

	
	public int newUser(Warehouse w, String name, int yearBirth, char sex, String contact, String email, String role){

		//verificar se user é Admin
		/*
			return -1 caso não seja
		*/
		//User u = new User("dados")
		//"dados" sao parametros
		//w.increaseUsers();
		//adicionar user à database
		return 0;
		
	}

	public String getName(){
		
		return this.Name;
	}
	public int getyearBirth(){
		
		return this.YearBirth;
	}
	public char getSex(){
		
		return this.Sex;
	}
	public String getContact(){
		
		return this.Contact;
	}
	public String getEmail(){
		
		return this.Email;
	}
	public String getRole(){
		
		return this.Role;
	}

	public int setName(){
		//verificar se user é Admin
		/*
			return -1 caso não seja
		*/
		return 0;
	}

}