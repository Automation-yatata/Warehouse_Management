package database;
import java.sql.*;
import backend.*;
import java.util.LinkedList;




public class DB{

	//utilização de modelo Singleton de modo a garantir a conexão e utilização de um Object DB
	private static DB single_instance = null;

	   
    private String connectionURL = "jdbc:postgresql://db.fe.up.pt:5432/up202102685";
    private String username = "up202102685";
    private String password = "BB7PhXVu5";
	private Connection connection = null;
    private Statement statement;
    private String query = null;
    private ResultSet result = null;
    

	private DB()
    {
        
    }

    public static DB getInstance()
    {
        if (single_instance == null)
            single_instance = new DB();
 
        return single_instance;
    }

    public void connect(){
    
        try {
            
            this.connection = DriverManager.getConnection(this.connectionURL, this.username, this.password);
            this.connection.setSchema("up202102685");
            
            System.out.println("Conectou");
            this.statement = this.connection.createStatement();
            
        }
        catch (SQLException error) {
            error.printStackTrace();
        }

    }

    public void setup() {
    	
    	try {
    		this.query = "CREATE TABLE products (reference INTEGER PRIMARY KEY, name VARCHAR(100) NOT NULL, unit_cost REAL)";
    		this.statement.executeUpdate(this.query);
    		
    		this.query = "CREATE TABLE items (id SERIAL PRIMARY KEY, quantity INTEGER, totalWeight INTEGER, inputDate DATE NOT NULL)";
    		this.statement.executeUpdate(this.query);
    		
    		this.query = "CREATE TABLE suppliers (name VARCHAR(100) PRIMARY KEY, contact VARCHAR(100), email VARCHAR(100))";
    		this.statement.executeUpdate(this.query);
    		
    		this.query = "CREATE TABLE users (name VARCHAR(100) PRIMARY KEY, year_birth INTEGER, sex VARCHAR(3), contact VARCHAR(100), role VARCHAR(100) NOT NULL)";
    		this.statement.executeUpdate(this.query);
    		
    		this.query = "CREATE TABLE racks (id SERIAL PRIMARY KEY, x INTEGER NOT NULL, y INTEGER NOT NULL, z INTEGER NOT NULL, max_weight REAL NOT NULL)";
    		this.statement.executeUpdate(this.query);
    		
    		
    		
    		
    		this.query = "ALTER TABLE items ADD supplier_id INTEGER";
    		this.statement.executeUpdate(this.query);
    		this.query = "ALTER TABLE items ADD FOREIGN KEY (supplier_id) REFERENCES suppliers(id)";
    		this.statement.executeUpdate(this.query);
    		
    		this.query = "ALTER TABLE users ADD CONSTRAINT binario CHECK (sex = 'M' OR sex = 'F')";
    		this.statement.executeUpdate(this.query);
    		
    		this.query = "ALTER TABLE items ADD product_reference INTEGER";
    		this.statement.executeUpdate(this.query);
    		this.query = "ALTER TABLE items ADD FOREIGN KEY (product_reference) REFERENCES products(reference)";
    		this.statement.executeUpdate(this.query);
    		
    		this.query = "ALTER TABLE items ADD rack_id INTEGER";
    		this.statement.executeUpdate(this.query);
    		this.query = "ALTER TABLE items ADD FOREIGN KEY (rack_id) REFERENCES racks(id)";
    		this.statement.executeUpdate(this.query);
    		
    		
    		
    	}
        catch (SQLException error) {
            error.printStackTrace();
        }

    }
    
    public void dropAll() {
    	try {
    		this.query = "DROP TABLE products CASCADE;";
    		this.statement.executeUpdate(this.query);
    		this.query = "DROP TABLE items CASCADE;";
    		this.statement.executeUpdate(this.query);
    		this.query = "DROP TABLE suppliers CASCADE;";
    		this.statement.executeUpdate(this.query);
    		this.query = "DROP TABLE users CASCADE;";
    		this.statement.executeUpdate(this.query);
    		this.query = "DROP TABLE racks CASCADE;";
    		this.statement.executeUpdate(this.query);
    		
    	}
        catch (SQLException error) {
            error.printStackTrace();
        }
    }
    
    

    public void queryUserNew (User u) {
    	
    	try {
    		
    		this.query = "INSERT INTO users (name, year_birth, contact, role) VALUES (" + u.getName() + "," + u.getyearBirth() + "," + u.getContact() + "," + u.getRole() + ")";
    		this.statement.executeUpdate(this.query);
    	}
    	catch (SQLException error) {
    		error.printStackTrace();
		}
    }
    public void queryProductNew(Product p) {
    	
    	try {
    		this.query = "INSERT INTO products (reference, name, unit_cost) VALUES (" + "'" + p.getReference() + "'"+ "," + "'" + p.getName() +"'"+ "," + "'"+p.getUnitCost() + "'"+ ")";
			this.statement.executeUpdate(this.query);
    	}
    	catch (SQLException error) {
    		error.printStackTrace();
		}
    }
    public void queryItemNew (Item i, Integer rackId) {
    	
    	   	
    	try {
    		this.query = "INSERT INTO items (quantity, totalweight, inputdate, supplier_id, product_reference, rack_id) VALUES (" + i.getQuantity() + "," + i.getTotalWeight() + "," + i.getInputDate() + "," + i.getSupplier() + "," + i.getProductRef() + rackId + ")";
    		this.statement.executeUpdate(this.query);
    	}
    	catch (SQLException error) {
    		error.printStackTrace();
		}
    }
    public void querySupplierNew(Supplier s) {
    
    	try {
    		this.query = "INSERT INTO suppliers (name, contact, email) VALUES (" + s.getName() + "," + s.getContact() + "," + s.getEmail() + ")";
    		this.statement.executeUpdate(this.query);
    	}
    	catch (SQLException error) {
    		error.printStackTrace();
		}
    }
    public void queryRackNew (Rack r) {
    	
    	try {
    		this.query = "INSERT INTO racks (x, y, z, max_weight) VALUES (" + r.getPosition('x') + "," + r.getPosition('y') + "," + r.getPosition('z') + r.getMaxWeight() +")";
    		this.statement.executeUpdate(this.query);
    	}
    	catch (SQLException error) {
    		error.printStackTrace();
		}
    }
       
    
    public LinkedList <String> queryUserSearch(String name) {

    	try {
    		this.query = "SELECT * FROM users WHERE name = " + "'" + name + "'";
    		this.result = this.statement.executeQuery(this.query);
		} catch (SQLException error) {
			error.printStackTrace();
		}
    	
    	LinkedList <String> retorno = new LinkedList <String>();
    	
    	try {
    		while (this.result.next()) {
    			retorno.add(this.result.getString("name"));
    			retorno.add(this.result.getString("year_birth"));
    			retorno.add(this.result.getString("sex"));
    			retorno.add(this.result.getString("contact"));
    			retorno.add(this.result.getString("role"));
    		
    		}
    	}
        catch(SQLException error) {
        	error.printStackTrace();
        }
    	
    	return retorno;
    	
    }
    public LinkedList <String> queryProductSearch(String productName){
    	try {
    		this.query = "SELECT * FROM products WHERE name = " + "'" + productName + "'";
    		this.result = this.statement.executeQuery(this.query);
		} catch (SQLException error) {
			error.printStackTrace();
		}
    	
    	LinkedList <String> retorno = new LinkedList <String>();
    	
    	try {
    		while (this.result.next()) {
    			retorno.add(this.result.getString("reference"));
    			retorno.add(this.result.getString("name"));
    			retorno.add(this.result.getString("unit_cost"));    		
    		}
    	}
        catch(SQLException error) {
        	error.printStackTrace();
        }
    	
    	return retorno;
    }
    public LinkedList <String> queryRackSearch(String productName) {
    	
    	try {
    		this.query = "SELECT * FROM racks WHERE name = " + "'" + productName + "'";
    		this.result = this.statement.executeQuery(this.query);
    	}
    	catch(SQLException error) {
    		error.printStackTrace();
    	}
    	
    	
    	LinkedList <String> retorno = new LinkedList <String>();
    	
    	try {
    		while (this.result.next()) {
    			retorno.add(this.result.getString("id"));
    			retorno.add(this.result.getString("x"));
    			retorno.add(this.result.getString("y"));
    			retorno.add(this.result.getString("z")); 
    			retorno.add(this.result.getString("max_weight")); 
    		}
    	}
        catch(SQLException error) {
        	error.printStackTrace();
        }
    	
    	return retorno;
    	
    	
    }
    
    public void queryItemRemove(Integer itemId){
    
    	try {
    		this.query = "DELETE FROM items WHERE id =" + itemId;
    		this.result = this.statement.executeQuery(this.query);
    	}
    	catch(SQLException error) {
    		error.printStackTrace();
    	}
    	
    }
    public void queryItemLess (Integer id, Integer n) {
    	
    	try {
    		this.query = "SELECT quantity FROM items WHERE id = " + "'" + id + "'";
    		this.result = this.statement.executeQuery(this.query);
		} catch (SQLException error) {
			error.printStackTrace();
			return;
		}
    	
    	
    	int quantity=0;
    	
    	try {
    		quantity = Integer.parseInt (this.result.getString("quantity"));
    	}
    	catch(SQLException error) {
    		error.printStackTrace();
    		return;
    	}
    	
    	
    	if (quantity < n) {
    		System.out.print("Quantidade menor!!!");
    		return;
    	}
    	
    	try {
    		this.query = "UPDATE items SET quantity = " + (quantity - n) + "WHERE id =" + id;
    		this.result = this.statement.executeQuery(this.query);
		} catch (SQLException error) {
			error.printStackTrace();
		}
    }
    public void queryItemMore (Integer id, Integer n) {
    	
    	try {
    		this.query = "SELECT quantity FROM items WHERE id = " + "'" + id + "'";
    		this.result = this.statement.executeQuery(this.query);
		} catch (SQLException error) {
			error.printStackTrace();
			return;
		}
    	
    	
    	int quantity = 0;
    	
    	try {
    		quantity = Integer.parseInt (this.result.getString("quantity"));
    	}
    	catch(SQLException error) {
    		error.printStackTrace();
    		return;
    	}
    	
    	
    	try {
    		this.query = "UPDATE items SET quantity = " + (quantity + n) + "WHERE id =" + id;
    		this.result = this.statement.executeQuery(this.query);
		} catch (SQLException error) {
			error.printStackTrace();
		}
    }
    public void queryUserRemove (String name) {
    	
    	try {
    		this.query = "DELETE FROM users WHERE name =" + "'" + name + "'";
    		this.result = this.statement.executeQuery(this.query);
    	}
    	catch(SQLException error) {
    		error.printStackTrace();
    	}
    }
    public void querySupplierRemove (String name) {
    	
    	try {
    		this.query = "DELETE FROM suppliers WHERE name =" + "'" + name + "'";
    		this.result = this.statement.executeQuery(this.query);
    	}
    	catch(SQLException error) {
    		error.printStackTrace();
    	}
    }
    
    public static void main (String[] args){
        
    	DB a = DB.getInstance();
        a.connect();
        //a.dropAll();
        a.setup();
        
        //LinkedList <String> aux = a.queryCheckUser("Jose");
        //System.out.println(aux.get(1));
        
    }

}

