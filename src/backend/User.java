package backend;

public class User{

	private String Name;
	private int YearBirth;
	private char Sex;
	private String Contact;
	private String Email;
	private String Role;

	public User(String name, int yearBirth, char sex, String contact, String email, String role){
		this.Name = name;
		this.YearBirth = yearBirth;
		sex = Character.toUpperCase(sex);
		try {
			if (sex == 'M' || sex == 'F') {
				this.Sex = sex;
			} else {
				throw new Exception("SexInvalid");
			}
		}catch (Exception error){
			System.out.print("Sex Must Be M or F\n");
		}
		this.Contact = contact;
		this.Email = email;
		this.Role = role;
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