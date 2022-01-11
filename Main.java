package frontend;

import backend.*;
import database.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;


public class Main{



    public User login(DB d, String userName, String password){

        LinkedList<String> aux = d.queryUserSearch(userName);

        if (password.equals(aux.get(7))){
            User u = new User(aux.get(1), Integer.parseInt(aux.get(2)), aux.get(3).charAt(0), aux.get(4), aux.get(5), aux.get(6));
            System.out.print("User Login\n");
            return u;
        }else{
            System.out.print("ERROR: User Login\n");
            return null;
        }
    }
    public void newUser (DB d, User admin, String userName, String name, int yearBirth, char sex, String contact, String email, String role, String password){

        if (admin.getRole().toLowerCase().equals("admin")){
            User u = new User(name, yearBirth, sex, contact, email, role);
            d.queryUserNew(u, userName, password);
        }else{
            System.out.print("ERROR: User Creation");

        }

    }

    public void uploadTestData(DB d){

        User jose = new User ("Jose", 2000, 'M', "+351910910910", "testjose@gmail.com","Admin");
        User diogo = new User ("Diogo", 2000, 'M', "+351910910910", "testjose@gmail.com","Admin");
        User pedro = new User ("Pedro", 2000, 'M', "+351910910910", "testjose@gmail.com","Admin");
        newUser(d, jose, "jojo", jose.getName(), jose.getyearBirth(), jose.getSex(), jose.getContact(), jose.getEmail(), jose.getRole(), "rococo");
        newUser(d, diogo, "diogao", diogo.getName(), diogo.getyearBirth(), diogo.getSex(), diogo.getContact(), diogo.getEmail(), diogo.getRole(), "rococo");
        newUser(d, pedro, "pp", pedro.getName(), pedro.getyearBirth(), pedro.getSex(), pedro.getContact(), pedro.getEmail(), pedro.getRole(), "rococo");

        d.queryRackNew(new Rack(0,0,0,1700));
        d.queryRackNew(new Rack(0,0,1,1600));
        d.queryRackNew(new Rack(0,0,2,1700));
        d.queryRackNew(new Rack(0,1,0,1678));
        d.queryRackNew(new Rack(0,1,1,1804));
        d.queryRackNew(new Rack(0,1,2,1900));
        d.queryRackNew(new Rack(0,2,0,1986));
        d.queryRackNew(new Rack(0,2,1,1678));
        d.queryRackNew(new Rack(0,2,2,1509));
        d.queryRackNew(new Rack(0,3,0,1900));
        d.queryRackNew(new Rack(0,3,1,1762));
        d.queryRackNew(new Rack(0,3,2,1577));

        d.queryRackNew(new Rack(1,0,0,1700));
        d.queryRackNew(new Rack(1,0,1,1600));
        d.queryRackNew(new Rack(1,0,2,1908));
        d.queryRackNew(new Rack(1,1,0,1900));
        d.queryRackNew(new Rack(1,1,1,1804));
        d.queryRackNew(new Rack(1,1,2,1678));
        d.queryRackNew(new Rack(1,2,0,1986));
        d.queryRackNew(new Rack(1,2,1,1900));
        d.queryRackNew(new Rack(1,2,2,1900));
        d.queryRackNew(new Rack(0,3,0,1900));
        d.queryRackNew(new Rack(0,3,1,1762));
        d.queryRackNew(new Rack(0,3,2,1577));

        d.queryRackNew(new Rack(2,0,0,1700));
        d.queryRackNew(new Rack(2,0,1,1600));
        d.queryRackNew(new Rack(2,0,2,1700));
        d.queryRackNew(new Rack(2,1,0,1986));
        d.queryRackNew(new Rack(2,1,1,1804));
        d.queryRackNew(new Rack(2,1,2,1678));
        d.queryRackNew(new Rack(2,2,0,1900));
        d.queryRackNew(new Rack(2,2,1,1900));
        d.queryRackNew(new Rack(2,2,2,1577));
        d.queryRackNew(new Rack(0,3,0,1986));
        d.queryRackNew(new Rack(0,3,1,1762));
        d.queryRackNew(new Rack(0,3,2,1577));

        Supplier s1 = new Supplier("Irmaos Fernando", "+351909090909", "lalal@gmail.com");
        Supplier s2 = new Supplier("EletroUS", "+351909090909", "lalal@gmail.com");
        Supplier s3 = new Supplier("Carvalho, LDA", "+351909090909", "lalal@gmail.com");
        Supplier s4 = new Supplier("Nokia", "+351909090909", "lalal@gmail.com");
        d.querySupplierNew(s1);
        d.querySupplierNew(s2);
        d.querySupplierNew(s3);
        d.querySupplierNew(s4);

        Product p1 = new Product("78988","Televisao LG", s1);
        Product p2 = new Product("98011","Camera SONY 5uni", s1);
        Product p3 = new Product("45345","Fio Cobre 13m", s3);
        Product p4 = new Product("11237","Arduino UNO R3",s2);
        Product p5 = new Product("67892","RaspberryPI",s2);
        Product p6 = new Product("67893","Osciloscopio", s2);
        Product p7 = new Product("16933","Portatil HP",s4);
        d.queryProductNew(p1);
        d.queryProductNew(p2);
        d.queryProductNew(p3);
        d.queryProductNew(p4);
        d.queryProductNew(p5);
        d.queryProductNew(p6);
        d.queryProductNew(p7);

        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate d1 = LocalDate.now();
        LocalDate d2 = d1.plusDays(5);
        LocalDate d3 = d1.minusDays(2);

        Item i1 = new Item(p1, 4, 321.12, 387.99, d3);
        Item i2 = new Item(p2, 10, 41.65, 137.99, d3);
        Item i3 = new Item(p3, 12, 114.51,27.99, d3);
        Item i4 = new Item(p1, 3, 251.52, 387.99, d1);
        Item i5 = new Item(p2, 7, 661.12, 137.99, d1);
        Item i6 = new Item(p4, 20, 21.12, 31.89, d1);
        Item i7 = new Item(p5, 30, 431.27, 48.49, d1);
        Item i8 = new Item(p6, 10, 154.23, 789.99, d2);
        Item i9 = new Item(p7, 6, 384.43, 555.99, d2);
        d.queryItemNew(i1,1);
        d.queryItemNew(i2,2);
        d.queryItemNew(i3,3);
        d.queryItemNew(i4,4);
        d.queryItemNew(i5,5);
        d.queryItemNew(i6,6);
        d.queryItemNew(i7,7);
        d.queryItemNew(i8,8);
        d.queryItemNew(i9,9);



    }

    //TODO Testar Item i/o
    public int itemInput (DB d, String productRef, double quantity, double totalWeight, double costUnit, Integer rackId){


        LinkedList <String> aux1 = d.queryProductSearch(productRef);

        if (aux1.isEmpty() == true ){
            return 0;
        }

        LinkedList <String> aux2 = d.querySupplierSearch(Integer.parseInt(aux1.get(2)));

        if (aux2.isEmpty() == true){
            return 0;
        }

        Supplier s = new Supplier(aux2.get(1), aux2.get(2), aux2.get(3));
        Product p = new Product(aux1.get(0), aux1.get(1), s);


        if (d.queryRackCheckEmpty(rackId) == 0){
            System.out.println("Error: Item Input: Rack" + rackId + "Occupied!!");
            return 0;
        }


        if (Integer.parseInt(d.queryRackSearch(rackId).get(4)) >= totalWeight){
            Item i = new Item(p, quantity, totalWeight, costUnit, this.getDate());
            d.queryItemNew(i,rackId);
            return 1;
        }else{
            System.out.println("ERROR: Item Input: maxWeight Exceeded!");
            return 0;
        }



    }

    public int itemOutput (DB d, int itemId, int quantityNew){

        if (quantityNew == -1){
            return d.queryItemRemove(itemId);
        }
        int quantity = Integer.parseInt (d.queryItemSearch(itemId).get(1));

        if(quantity > quantityNew){
            return d.queryItemLess(itemId, quantityNew);

        }else if(quantity == quantityNew){
            return d.queryItemRemove(itemId);

        }else if(quantity < quantityNew){
            return 0;
        }
        return 0;
    }

    public LocalDate getDate(){
        return LocalDate.now();
    }



    public static void main(String[] args){

        DB database = DB.getInstance();
        database.connect();
        database.dropAll();
        database.setup();
        Main app = new Main();
        app.uploadTestData(database);



        //app.itemInput(database, "67893", 4, 134.21, 781.89,1);
        //app.itemInput(database, "67893", 4, 134.21, 781.89,10);
        //database.queryItemtUpdate(6, "quantity", String.valueOf(15));
        //app.itemOutput(database,6, 5);

        //User pp = app.login(database, "Pedro", "zelindo");

        //app.newUser(database, jose, "Diogo", 2000, 'M', "+351900770056", "emailTest@gmail.com", "Admin", "pswtop");
        //Warehouse w = new Warehouse();

        //a.queryUserNew(u);



        //LinkedList <String> aux = a.queryCheckUser("Jose");
        //System.out.println(aux.get(1));


        //LinkedList <String> racksEmpty = database.queryRackSearchEmpty();
        //System.out.println(racksEmpty);

        //LinkedList <String> racksProd = database.queryRackSearchProd("Camera SONY 5uni");
        //System.out.println(racksProd);





    }

}
