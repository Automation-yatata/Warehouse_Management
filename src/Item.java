package backend;

public class Item {
    
    public Product Prod;
    public float Quantity;
	public float TotalWeight;
	public float UnitCost;


    public Item (Product prod, float quantity, float totalWeight, float unitCost){
        
        this.Prod = prod;
        this.Quantity = quantity;
		this.TotalWeight = totalWeight;
		this.UnitCost = unitCost;
    }

}
