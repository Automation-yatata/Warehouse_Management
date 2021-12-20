package backend;

import java.util.Date;



class InputRecord{
	
	private Item I;
	private Supplier S;
	private Date D;

	public InputRecord(Item i, Supplier s, Date d){
		this.I = i;
		this.S = s;
		this.D = d;
	}
}