package backend;

import java.util.Date;

class InputRecord{
	
	private Product P;
	private Supplier S;
	private Date D;

	public InputRecord(Product p, Supplier s, Date d){
		this.P = p;
		this.S = s;
		this.D = d;
	}
}