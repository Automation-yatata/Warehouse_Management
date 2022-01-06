package backend;

import java.util.LinkedList;



public class Warehouse{

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





}
