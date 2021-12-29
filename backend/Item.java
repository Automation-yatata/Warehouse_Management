package backend;
import java.util.Date;

public class Item {
    
    public Product Prod;
    public Supplier Supp;
    private float Quantity;
	private float TotalWeight;
	public Date InputDate;
	

    public Item (Product prod, float quantity, float totalWeight, Date date, Supplier supplier){
        
        this.Prod = prod;
        this.Supp = supplier;
        this.Quantity = quantity;
		this.TotalWeight = totalWeight;
		this.InputDate = date;
    }
    
    public float getQuantity() {
    	return this.Quantity;
    }
    public float getTotalWeight() {
    	return this.TotalWeight;
    }
    public Date getInputDate() {
    	return this.InputDate;
    }
    public Supplier getSupplier() {
    	return this.Supp;
    }
    public String getProductRef() {
    	return this.Prod.getReference();
    }
    

}
