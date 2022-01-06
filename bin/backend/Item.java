package backend;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Item {
    
    public Product Prod;

    private double Quantity;
	private double TotalWeight;
    public double UnitCost;
	public LocalDate InputDate;
	

    public Item (Product prod, double quantity, double totalWeight, double costUni, LocalDate date){
        
        this.Prod = prod;

        this.Quantity = quantity;
		this.TotalWeight = totalWeight;
        this.UnitCost = costUni;
		this.InputDate = date;
    }
    
    public double getQuantity() {
    	return this.Quantity;
    }
    public double getTotalWeight() {
    	return this.TotalWeight;
    }
    public double getUnitCost() {
        return this.UnitCost;
    }
    public String getInputDate() {
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	return this.InputDate.format(formater);
    }
    public String getProductRef() {
    	return this.Prod.getReference();
    }
    

}
