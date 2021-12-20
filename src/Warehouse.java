package backend;

import java.util.List;
import java.util.LinkedList;



class Warehouse{

	private int NumberRacks = 0;
	private int NumberUsers = 0;
	private LinkedList <Rack> rackList = new LinkedList<Rack>();


	public Warehouse(){
		
	}

	public int getNumberRacks(){
		
		return this.NumberRacks;
	}
	public int getNumberUsers(){
		
		return this.NumberUsers;
	}
	
	public void increaseUsers(){
		
		this.NumberUsers += 1;
	}
	public void decreaseUsers(){
		
		this.NumberUsers -= 1;
	}
	private void increseRacks(){
		
		this.NumberRacks += 1;
	}
	private void decreseRacks(){
		
		this.NumberRacks -= 1;
	}

	public int addRack(User u, Rack r){
		//verificar se user é Admin
		/*
			return -1 caso não seja
		*/
		this.rackList.add(r);
		this.increseRacks();
		return 0;
		
	}
	/*
	public User login(String userName, String password){

		//aceder à Database e criar um user com os dados de lá apenas!!
		//no código todos os objetos User devem ser criados apartir deste metodo
		//User u = new User("dados")
		//return u;
	}
	*/


}