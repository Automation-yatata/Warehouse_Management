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
	//TODO e se User invés de id como PRIMARY KEY tivesse userName? para além deste parâmetro teria também o parâmetro name. Discutir
    public void setup() {
    	
    	try {
    		this.query = "CREATE TABLE products (reference VARCHAR(100) PRIMARY KEY, name VARCHAR(100) NOT NULL)";
    		this.statement.executeUpdate(this.query);
    		
    		this.query = "CREATE TABLE items (id SERIAL PRIMARY KEY, quantity INTEGER, totalWeight INTEGER, unit_cost REAL, inputDate DATE NOT NULL)";
    		this.statement.executeUpdate(this.query);
    		
    		this.query = "CREATE TABLE suppliers (id SERIAL PRIMARY KEY, name VARCHAR(100) UNIQUE, contact VARCHAR(100), email VARCHAR(100))";
    		this.statement.executeUpdate(this.query);
    		
    		this.query = "CREATE TABLE users (userName VARCHAR(100) PRIMARY KEY, name VARCHAR(100), year_birth INTEGER, sex VARCHAR(3), contact VARCHAR(100), email VARCHAR(100), role VARCHAR(100) NOT NULL, password VARCHAR(100) NOT NULL )";
    		this.statement.executeUpdate(this.query);
    		
    		this.query = "CREATE TABLE racks (id SERIAL PRIMARY KEY, x INTEGER NOT NULL, y INTEGER NOT NULL, z INTEGER NOT NULL, max_weight REAL NOT NULL)";
    		this.statement.executeUpdate(this.query);
    		
    		
    		
    		
    		this.query = "ALTER TABLE products ADD supplier_id INTEGER";
    		this.statement.executeUpdate(this.query);
    		this.query = "ALTER TABLE products ADD FOREIGN KEY (supplier_id) REFERENCES suppliers(id)";
    		this.statement.executeUpdate(this.query);
    		
    		this.query = "ALTER TABLE users ADD CONSTRAINT binario CHECK (sex = 'M' OR sex = 'F')";
    		this.statement.executeUpdate(this.query);
    		
    		this.query = "ALTER TABLE items ADD product_reference VARCHAR(100)";
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
    
    
	/*FIXME queries NEW -> guardar dados formatados (fazer na main app)
		- string lowercase
		- phone numbers com 12 carateres obrigatorios (ou apenas nove, nesse caso adicionar +351)
		- email com @ obrigatorio

	 */
    public void queryUserNew (User u, String userName, String password) {
    	
    	try {
    		this.query = "INSERT INTO users (userName, name, year_birth, sex, contact, email, role, password) VALUES (" + "'" + userName + "'" + "'" + u.getName() + "'" + "," + u.getyearBirth() + "," + "'" + u.getSex() + "'" + "," + "'" + u.getContact() + "'"  + "," + "'" + u.getEmail() + "'" + "," + "'" + u.getRole() + "'" + "," + "'" + password + "'" +")";
    		this.statement.executeUpdate(this.query);
    	}
    	catch (SQLException error) {
    		error.printStackTrace();
		}
    }
    public void queryProductNew (Product p) {
    	
    	try {
    		this.query = "INSERT INTO products (reference, name, supplier_id) VALUES (" + "'" + p.getReference() + "'" + "," + "'" + p.getName() +"'" + "," + "'" + querySupplierSearchName(p.getSupplier().getName()).get(0) + "'" + ")";
			this.statement.executeUpdate(this.query);
    	}
    	catch (SQLException error) {
    		error.printStackTrace();
		}
    }
    public int  queryItemNew (Item i, Integer rackId) {

		if(this.queryRackCheckEmpty(rackId) == 0){
			return 0;
		}

    	try {
    		this.query = "INSERT INTO items (quantity, totalweight, unit_cost, inputdate, product_reference, rack_id) VALUES (" + i.getQuantity() + "," + i.getTotalWeight() + "," + "'" + i.getUnitCost() + "'"+ "," + "'" + i.getInputDate() + "'" + "," + "'" + i.getProductRef() + "'" + "," + rackId + ")";
    		this.statement.executeUpdate(this.query);
    	}
    	catch (SQLException error) {
    		error.printStackTrace();
			return -1;
		}
		return 1;
    }
    public void querySupplierNew(Supplier s) {
    
    	try {
    		this.query = "INSERT INTO suppliers (name, contact, email) VALUES (" + "'" + s.getName() + "'" + "," + "'" + s.getContact() + "'" + "," + "'" + s.getEmail() + "'" + ")";
    		this.statement.executeUpdate(this.query);
    	}
    	catch (SQLException error) {
    		error.printStackTrace();
		}
    }
    public void queryRackNew (Rack r) {
    	
    	try {
    		this.query = "INSERT INTO racks (x, y, z, max_weight) VALUES (" + r.getPosition('x') + "," + r.getPosition('y') + "," + r.getPosition('z') + "," + r.getMaxWeight() +")";
    		this.statement.executeUpdate(this.query);
    	}
    	catch (SQLException error) {
    		error.printStackTrace();
		}
    }

	//search by PRIMARY KEY
	//SupplierSearch ligeiramente diferente devido à queryProductNew
	//TODO ponderar se dividir querySupplierSearch em querySupplierSearch (pesquisa por id com as restantes) e querySupplierSearchName (pesquisa por name)

	public LinkedList <String> queryUserSearch (String userName) {

    	try {
    		this.query = "SELECT * FROM users WHERE userName = " + "'" + userName + "'";
    		this.result = this.statement.executeQuery(this.query);
		} catch (SQLException error) {
			error.printStackTrace();
		}
    	
    	LinkedList <String> retorno = new LinkedList <String>();
    	
    	try {
    		while (this.result.next()) {
				retorno.add(this.result.getString("userName"));
				retorno.add(this.result.getString("name"));
    			retorno.add(this.result.getString("year_birth"));
    			retorno.add(this.result.getString("sex"));
    			retorno.add(this.result.getString("contact"));
				retorno.add(this.result.getString("email"));
    			retorno.add(this.result.getString("role"));
				retorno.add(this.result.getString("password"));
    		}
    	}
        catch(SQLException error) {
        	error.printStackTrace();
        }
    	
    	return retorno;
    	
    }
    public LinkedList <String> queryProductSearch (String productRef){
    	try {
    		this.query = "SELECT * FROM products WHERE reference = " + "'" + productRef + "'";
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
				retorno.add(this.result.getString("supplier_name"));
    		}
    	}
        catch(SQLException error) {
        	error.printStackTrace();
        }
    	
    	return retorno;
    }
	public LinkedList <String> queryRackSearch (Integer id){

		//TODO testar
		try {
			this.query = "SELECT * FROM racks WHERE id = " + String.valueOf(id);
			this.result = this.statement.executeQuery(this.query);
		} catch (SQLException error) {
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
	public LinkedList <String> querySupplierSearch (Integer id){

		try {
			this.query = "SELECT * FROM suppliers WHERE id = " + id;
			this.result = this.statement.executeQuery(this.query);
		} catch (SQLException error) {
			error.printStackTrace();
		}

		LinkedList <String> retorno = new LinkedList <String>();

		try {
			while (this.result.next()) {
				retorno.add(this.result.getString("id"));
				retorno.add(this.result.getString("name"));
				retorno.add(this.result.getString("contact"));
				retorno.add(this.result.getString("email"));
			}
		}
		catch(SQLException error) {
			error.printStackTrace();
		}

		return retorno;
	}
	public LinkedList <String> querySupplierSearchName (String name){

		try {
			this.query = "SELECT * FROM suppliers WHERE name = " + "'" + name + "'";
			this.result = this.statement.executeQuery(this.query);
		} catch (SQLException error) {
			error.printStackTrace();
		}

		LinkedList <String> retorno = new LinkedList <String>();

		try {
			while (this.result.next()) {
				retorno.add(this.result.getString("id"));
				retorno.add(this.result.getString("name"));
				retorno.add(this.result.getString("contact"));
				retorno.add(this.result.getString("email"));
			}
		}
		catch(SQLException error) {
			error.printStackTrace();
		}

		return retorno;
	}
	public LinkedList <String> queryItemSearch (Integer id){

		try {
			this.query = "SELECT * FROM items WHERE id = " + id;
			this.result = this.statement.executeQuery(this.query);
		} catch (SQLException error) {
			error.printStackTrace();
		}

		LinkedList <String> retorno = new LinkedList <String>();

		try {
			while (this.result.next()) {
				retorno.add(this.result.getString("id"));
				retorno.add(this.result.getString("quantity"));
				retorno.add(this.result.getString("totalweight"));
				retorno.add(this.result.getString("unit_cost"));
				retorno.add(this.result.getString("inputdate"));
				retorno.add(this.result.getString("product_reference"));
				retorno.add(this.result.getString("rack_id"));
			}
		}
		catch(SQLException error) {
			error.printStackTrace();
		}

		return retorno;
	}

	//complex search
	public LinkedList <String> queryRackSearchProd(String productName) {

		//returns all the racks where items are associate to the product file "productName"
    	try {
    		this.query = "SELECT * FROM items LEFT JOIN products ON items.product_reference = products.reference WHERE products.name = " + "'" + productName + "'";
    		this.result = this.statement.executeQuery(this.query);
    	}
    	catch(SQLException error) {
    		error.printStackTrace();
    	}
    	
    	
    	LinkedList <String> retorno = new LinkedList <String>();
    	
    	try {
    		while (this.result.next()) {
    			retorno.add(this.result.getString("rack_id"));
    		}
    	}
        catch(SQLException error) {
        	error.printStackTrace();
        }
    	
    	return retorno;
    	
    	
    }
	public LinkedList <String> queryRackSearchEmpty(){

		//returns all the racks that are empty
		try {
			this.query = "SELECT racks.* FROM racks LEFT JOIN items ON racks.id = items.rack_id WHERE items.rack_id IS NULL";
			this.result = this.statement.executeQuery(this.query);
		}
		catch(SQLException error) {
			error.printStackTrace();
		}


		LinkedList <String> retorno = new LinkedList <String>();

		try {
			while (this.result.next()) {
				retorno.add(this.result.getString("id"));
				//retorno.add(this.result.getString("x"));
				//retorno.add(this.result.getString("y"));
				//retorno.add(this.result.getString("z"));
				//retorno.add(this.result.getString("max_weight"));
			}
		}
		catch(SQLException error) {
			error.printStackTrace();
		}

		return retorno;


	}
	public int queryRackCheckEmpty(int rackId){

		//verifica se a essa rack está associado algum item
		try {
			this.query = "SELECT * FROM items WHERE rack_id = " + rackId;
			this.result = this.statement.executeQuery(this.query);
		}
		catch(SQLException error) {
			error.printStackTrace();
			return -1;
		}

		try {

			if (this.result.isBeforeFirst() == false){
				System.out.println("TESTE queryRackCheckEmpty: Rack Empty!");
				return 1;
			}else {
				return 0;
			}
		}catch(SQLException error){
			error.printStackTrace();
			return -1;

		}
	}


	//TODO testar queries UPDATE (principalmente verificar bugs com tipos de variáveis) para cada parâmetro (possível alterar PRIMARY KEY??)
	//TODO acabar queries UPDATE
	//TODO verificar admin para executar UPDATE
	public int queryUserUpdate(String userName, String attribute, String newValue){

		if (attribute.equals("name")){

			try {
				this.query = "UPDATE users SET name = " + "'" + newValue + "'" + "  WHERE name = " + "'" + userName + "'";
				this.statement.executeUpdate(this.query);
			} catch (SQLException error) {
				error.printStackTrace();
				return -1;
			}

		}else if (attribute.equals("year_birth")){

			try {
				this.query = "UPDATE users SET year_birth = " + Integer.parseInt(newValue) + "  WHERE name = " + "'" + userName + "'";
				this.statement.executeUpdate(this.query);
			} catch (SQLException error) {
				error.printStackTrace();
				return -1;
			}

		}else if (attribute.equals("sex")){

			try {
				this.query = "UPDATE users SET sex = " + "'" + newValue + "'" + "  WHERE name = " + "'" + userName + "'";
				this.statement.executeUpdate(this.query);
			} catch (SQLException error) {
				error.printStackTrace();
				return -1;
			}

		}else if (attribute.equals("contact")){

			try {
				this.query = "UPDATE users SET contact = " + "'" + newValue + "'" + "  WHERE name = " + "'" + userName + "'";
				this.statement.executeUpdate(this.query);
			} catch (SQLException error) {
				error.printStackTrace();
				return -1;
			}

		}else if (attribute.equals("email")){

			try {
				this.query = "UPDATE users SET email = " + "'" + newValue + "'" + "  WHERE name = " + "'" + userName + "'";
				this.statement.executeUpdate(this.query);
			} catch (SQLException error) {
				error.printStackTrace();
				return -1;
			}

		}else if (attribute.equals("role")){

			try {
				this.query = "UPDATE users SET role = " + "'" + newValue + "'" + "  WHERE name = " + "'" + userName + "'";
				this.statement.executeUpdate(this.query);
			} catch (SQLException error) {
				error.printStackTrace();
				return -1;
			}

		}else if (attribute.equals("password")){

			try {
				this.query = "UPDATE users SET password = " + "'" + newValue + "'" + "  WHERE name = " + "'" + userName + "'";
				this.statement.executeUpdate(this.query);
			} catch (SQLException error) {
				error.printStackTrace();
				return -1;
			}
		}
		return 0;
	}
	public int queryProductUpdate (User admin, String reference, String attribute, String newValue){

		if (attribute.equals("year_birth")){

			try {
				this.query = "UPDATE users SET year_birth = " + Integer.parseInt(newValue) + "  WHERE reference = " + "'" + reference + "'";
				this.statement.executeUpdate(this.query);
			} catch (SQLException error) {
				error.printStackTrace();
				return -1;
			}

		}else if (attribute.equals("supplier_name")){

			try {
				this.query = "UPDATE users SET supplier_name = " + "'" + newValue + "'" + "  WHERE reference = " + "'" + reference + "'";
				this.statement.executeUpdate(this.query);
			} catch (SQLException error) {
				error.printStackTrace();
				return -1;
			}

		}
		return 0;
	}
	public int queryItemtUpdate (User admin, Integer id, String attribute, String newValue){

		if (attribute.equals("quantity")){

			try {
				this.query = "UPDATE users SET quantity = " + Integer.parseInt(newValue) + "  WHERE id = " + id;
				this.statement.executeUpdate(this.query);
			} catch (SQLException error) {
				error.printStackTrace();
				return -1;
			}

		}else if (attribute.equals("totalweight")){

			try {
				this.query = "UPDATE users SET totalweight = " + "'" + newValue + "'" + "   WHERE id = " + id;
				this.statement.executeUpdate(this.query);
			} catch (SQLException error) {
				error.printStackTrace();
				return -1;
			}

		}else if (attribute.equals("unit_cost")){

			try {
				this.query = "UPDATE users SET unit_cost = " + "'" + newValue + "'" + "   WHERE id = " + id;
				this.statement.executeUpdate(this.query);
			} catch (SQLException error) {
				error.printStackTrace();
				return -1;
			}

		}else if (attribute.equals("inputdate")){

			try {
				this.query = "UPDATE users SET inputdate = " + "'" + newValue + "'" + "   WHERE id = " + id;
				this.statement.executeUpdate(this.query);
			} catch (SQLException error) {
				error.printStackTrace();
				return -1;
			}

		}else if (attribute.equals("product_reference")){

			try {
				this.query = "UPDATE users SET product_reference = " + "'" + newValue + "'" + "   WHERE id = " + id;
				this.statement.executeUpdate(this.query);
			} catch (SQLException error) {
				error.printStackTrace();
				return -1;
			}

		}else if (attribute.equals("rack_id")){

			try {
				this.query = "UPDATE users SET rack_id = " + "'" + newValue + "'" + "   WHERE id = " + id;
				this.statement.executeUpdate(this.query);
			} catch (SQLException error) {
				error.printStackTrace();
				return -1;
			}

		}
		return 0;
	}
	public int querySuppliertUpdate (User admin, Integer id, String attribute, String newValue){

		if (attribute.equals("name")){

			try {
				this.query = "UPDATE users SET name = " + newValue + "  WHERE id = " + id;
				this.statement.executeUpdate(this.query);
			} catch (SQLException error) {
				error.printStackTrace();
				return -1;
			}

		}else if (attribute.equals("contact")){

			try {
				this.query = "UPDATE users SET contact = " + "'" + newValue + "'" + "   WHERE id = " + id;
				this.statement.executeUpdate(this.query);
			} catch (SQLException error) {
				error.printStackTrace();
				return -1;
			}

		}else if (attribute.equals("email")){

			try {
				this.query = "UPDATE users SET email = " + "'" + newValue + "'" + "   WHERE id = " + id;
				this.statement.executeUpdate(this.query);
			} catch (SQLException error) {
				error.printStackTrace();
				return -1;
			}

		}

		return 0;
	}


	//TODO testar queries REMOVE
	//TODO verificar admin para executar REMOVE
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
    public void queryUserRemove ( String userName) {

    	try {
    		this.query = "DELETE FROM users WHERE name =" + "'" + userName + "'";
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




}

