package model;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalTime;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class DataBase {

    private ArrayList<Product> products;

    private ArrayList<Order> orders;

    private File dataBaseP;

    private File dataBaseO;

    public DataBase(){

        products = new ArrayList<Product>();

        orders = new ArrayList<Order>();

        dataBaseP = new File("DataBaseP.json");

        dataBaseO = new File("DataBaseO.json");

       // fillProducts();

        //fillOrders();

    }







    public ArrayList<Product> fillProducts(){

        Product product1 = new Product("Cancion de hielo y fuego", "Libro extenso" , 50.00, 3, Category.LIBROS, 2);
        Product product2 = new Product("Harry Potter", "Libro divertido" , 59.00, 7, Category.LIBROS, 4);
        Product product3 = new Product("JKuegos del hambre", "Best seller" , 30.00, 2, Category.LIBROS, 1);
        Product product4 = new Product("iPad", "Excelente dispositivo" , 350.00, 10, Category.ELECTRONICA, 4);
        Product product5 = new Product("iPhone", "Buen celular" , 600.00, 15, Category.ELECTRONICA, 3);
        Product product6 = new Product("MacBook", "Increiblemente veloz" , 1100.00, 13, Category.ELECTRONICA, 9);
        Product product7 = new Product("Camiseta ", "Color beige" , 20.00, 30, Category.ROPAYACCESORIOS, 3);
        Product product8 = new Product("Caedigan", "Talla: S" , 100.00, 21, Category.ROPAYACCESORIOS, 6);
        Product product9 = new Product("Blazer", "Color negro" , 150.00, 7, Category.ROPAYACCESORIOS, 1);
        Product product10 = new Product("Yogurt", "Sabor melocoton" , 6.00, 70, Category.ALIMENTOYBEBEIDAS, 39);
        Product product11 = new Product("Leche", "Descremada" , 7.00, 100, Category.ALIMENTOYBEBEIDAS, 74);
        Product product12 = new Product("Galletas", "Sabor chocolate" , 1.00, 232, Category.ALIMENTOYBEBEIDAS, 113);
        Product product13 = new Product("Boligrafo", "Color negro" , 2.00, 85, Category.PAPELERIA, 52);
        Product product14 = new Product("Borrador", "Borra tinta" , 0.50, 178, Category.PAPELERIA, 102);
        Product product15 = new Product("Lapiz", "HB" , 1.00, 341, Category.PAPELERIA, 107);
        Product product16 = new Product("Balon", "Balon de futbol" , 50.00, 7, Category.DEPORTES, 5);
        Product product17 = new Product("Zapatos", "Talla 40" , 70.00, 9, Category.DEPORTES, 2);
        Product product18 = new Product("Vendas", "Vendas de boxing" , 25.00, 8, Category.DEPORTES, 4);
        Product product19 = new Product("Crema", "Hidratante" , 80.00, 17, Category.CUIDADOPERSONAL, 3);
        Product product20 = new Product("Exfoliante", "Para piel mixta" , 90.00, 4, Category.CUIDADOPERSONAL, 1);
        Product product21 = new Product("Bloqueador solar", "SPF 50" , 60.00, 18, Category.CUIDADOPERSONAL, 9);
        Product product22 = new Product("Gato", "Gato pequeño", 12.00 , 10, Category.OTROS , 0);
        Product product23 = new Product("Perro", "Perro pequeño", 32.00 , 2, Category.OTROS , 2);
        Product product24 = new Product("Pajaro", "Pajaro grande", 120.00 , 5, Category.OTROS , 0);
        Product product25 = new Product("Monopoly", "Edicion Colombia" , 70.00, 10, Category.JUGUETERIA, 6);
        Product product26 = new Product("Nerf", "Starter Pack" , 90.00, 15, Category.JUGUETERIA, 7);
        Product product27 = new Product("Risk", "Edicion especial" , 68.00, 7, Category.JUGUETERIA, 1);

        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);
        products.add(product7);
        products.add(product8);
        products.add(product9);
        products.add(product10);
        products.add(product11);
        products.add(product12);
        products.add(product13);
        products.add(product14);
        products.add(product15);
        products.add(product16);
        products.add(product17);
        products.add(product18);
        products.add(product19);
        products.add(product20);
        products.add(product21);
        products.add(product22);
        products.add(product23);
        products.add(product24);
        products.add(product25);
        products.add(product26);
        products.add(product27);

        return products;

    }

    public void deletDataBases(){

        dataBaseO.delete();

        dataBaseP.delete();

    }

    public ArrayList<Order> fillOrders(){
        
        ArrayList<String> o1 = new ArrayList<String>();
        o1.add("1");
        o1.add("2");
        o1.add("3");
        Order order1 = new Order("Julio", o1, 750, LocalTime.now());
        ArrayList<String> o2 = new ArrayList<String>();
        o2.add("4");
        o2.add("5");
        o2.add("6");
        Order order2 = new Order("Pepe ", o2 , 960, LocalTime.now());
        ArrayList<String> o3 = new ArrayList<String>();
        o3.add("7");
        o3.add("8");
        o3.add("9");
        Order order3 = new Order("Fernando", o3 , 1050, LocalTime.now());
        ArrayList<String> o4 = new ArrayList<String>();
        o4.add("10");
        o4.add("11");
        o4.add("12");
        Order order4 = new Order("Juan", o4 , 562, LocalTime.now());

        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        orders.add(order4);

        return orders;


    }

    public File getDataBaseP() {
        return dataBaseP;
    }

    public void setDataBaseP(File dataBaseP) {
        this.dataBaseP = dataBaseP;
    }

    public File getDataBaseO() {
        return dataBaseO;
    }

    public void setDataBaseO(File dataBaseO) {
        this.dataBaseO = dataBaseO;
    }


    public ArrayList<Product> getProducts() {

        return products;

    }

    public ArrayList<Order> getOrders() {

        return orders;
        
    }
    
}
