package backend;

public class Product{
	
	public String Reference;
	public String Name;
	public Supplier Supp;
	

	public Product(String ref, String name, Supplier supplier){
		
		this.Reference = ref;
		this.Name = name;
		this.Supp = supplier;
	}
	
	public String getReference() {
		return this.Reference;
	}
	public String getName() {
		return this.Name;
	}
	public Supplier getSupplier() {
		return this.Supp;
	}
}