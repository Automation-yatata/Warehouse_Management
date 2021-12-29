package backend;

public class Product{
	
	public String Reference;
	public String Name;
	public float UnitCost;
	

	public Product(String ref, String name, float cost){
		
		this.Reference = ref;
		this.Name = name;
		this.UnitCost = cost;
	}
	
	public String getReference() {
		return this.Reference;
	}
	public String getName() {
		return this.Name;
	}
	public float getUnitCost() {
		return this.UnitCost;
	}
}