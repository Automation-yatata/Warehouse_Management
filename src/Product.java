package backend;

class Product{
	
	public String Name;
	public float Quantity;
	public float TotalWeight;
	public float UnitCost;

	public Product(String name, float quantity, float totalWeight, float unitCost){

		this.Name = name;
		this.Quantity = quantity;
		this.TotalWeight = totalWeight;
		this.UnitCost = unitCost;
	}
}