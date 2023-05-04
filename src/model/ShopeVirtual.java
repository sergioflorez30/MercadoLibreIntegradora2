package model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ShopeVirtual {
    private String directionProduct;
    private String directionOrder;
    private ArrayList<Product> products;
    private ArrayList<Order> orders;
    private Binsearch search;
    private DataBase db;

    public ShopeVirtual() {
        products = new ArrayList<>();
        orders = new ArrayList<>();
        search = new Binsearch();
        db = new DataBase();
    }

    public void addJsonP(String json, String direcion) {
        Gson gson = new Gson();
        Product[] productFromJson = gson.fromJson(json, Product[].class);
        if (productFromJson == null) {
            this.directionProduct = direcion;
            return;
        } else {
            for (Product p : productFromJson) {
                products.add(p);

            }
            this.directionProduct = direcion;
        }

    }

    public void addJsonO(String json, String direcion) {
        Gson gson = new Gson();
        Order[] OrderFromJson = gson.fromJson(json, Order[].class);
        if (OrderFromJson == null) {
            this.directionProduct = direcion;
            return;
        } else {
            for (Order p : OrderFromJson) {
                orders.add(p);

            }
            this.directionProduct = direcion;
        }

    }

    public void writeJson(ArrayList list, File direction) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalTime.class, new LocalTimeAdapter()).create();
        String json = gson.toJson(list);
    
        try {
            FileOutputStream fos = new FileOutputStream(direction);
            fos.write(json.getBytes(StandardCharsets.UTF_8));
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deletDataBases(){

        db.deletDataBases();

    }
    

    public void addDataBaseObjects(){

        products = db.fillProducts();
      
        orders = db.fillOrders();

        File pFile = db.getDataBaseP();

        File oFile = db.getDataBaseO();

        writeJson(orders, oFile);

        writeJson(products, pFile);

    }

    public void addProduct(String name, String description, int amount, double price, int typeNum) {
        Category category;
        switch (typeNum) {
            case 1:
                category = Category.LIBROS;
                break;
            case 2:
                category = Category.ELECTRONICA;
                break;
            case 3:
                category = Category.ROPAYACCESORIOS;
                break;
            case 4:
                category = Category.ALIMENTOYBEBEIDAS;
                break;
            case 5:
                category = Category.PAPELERIA;
                break;
            case 6:
                category = Category.DEPORTES;
                break;
            case 7:
                category = Category.CUIDADOPERSONAL;
            case 8:
                category = Category.JUGUETERIA;
                break;
            default:
                category = Category.OTROS;
        }
        if (price < 0|| amount<0) {
            throw new IllegalArgumentException("no puedes usar valores negativos para el precio o cantidad.");
        }
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return p1.getName().compareTo(p2.getName());
            }
        });
        
        int index = search.binsearchabb(products, name);

        if(index==-1){  
        Product product = new Product(name, description, price, amount, category, 0);
        products.add(product);
        }else{
            products.get(index).setAmount(products.get(index).getAmount()+amount);
        }

    }
    public  void addOrder(String name, ArrayList<String> listProducts, double price,LocalTime date) {
        Order order = new Order(name, listProducts,price,date);
        if(price <= 0){
            throw new IllegalArgumentException("no puedes usar valores neutros o negativos para el precio.");
        }
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return p1.getName().compareTo(p2.getName());
            }
        });
        if (listProducts == null){
            throw new IllegalArgumentException("no hay productos en tu lista.");
        }
        for (String product : listProducts) {
            try {
                int index = search.binsearchabb(products, product); 
                Product product2 = products.get(index); 
                changeAmountAvailable(product, product2.getAmount()-1);
            } catch (Exception e) {
                System.out.println("Se produjo una excepción: " + e.getMessage());
            }
        }
        if (name == null){
            throw new IllegalArgumentException("el nombre del comprador no existe");
        }
        orders.add(order);

    }

    public String searchOrderName(String name){
        String msj  = "";
        Collections.sort(orders, new Comparator<Order>() {
            @Override
            public int compare(Order p1, Order p2) {
                return p1.getNameBuyer().compareTo(p2.getNameBuyer());
            }
        });
        int index = search.binsearchabbO(orders, name);
        if (index != -1) {
            Order foundOrder = orders.get(index);
            msj = " la orden a nombre de " +  name + " tiene un precio de "+ foundOrder.getPrice() + " hecha a las  " +  foundOrder.getDateBuy().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        } else if (index == -1){
            throw new IllegalArgumentException("comprador no encontrado.");
        }
            return msj; 
    }

    public String searchOrderPrice(double price){
        String msj = "";
        Collections.sort(orders, new Comparator<Order>() {
            @Override
            public int compare(Order p1, Order p2) {
                return (int)(p1.getPrice() - p2.getPrice());
            }
        });
        int index = search.binarySearchOrderPrice(orders, price);
        if (index != -1) {
            Order foundOrder = orders.get(index);
            msj = "La orden a nombre de " + foundOrder.getNameBuyer() + " tiene un precio de " + price + " hecha a las  " +  foundOrder.getDateBuy().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        } else if (index == -1){
            throw new IllegalArgumentException("precio no encontrado.");
        }
        return msj ; 
    }
    public String searchOrderTime(LocalTime time){
        String msj = ""; 
        Collections.sort(orders, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return o1.getDateBuy().compareTo(o2.getDateBuy());
            }
        });
        int index =search.binSearchOrderByTime(orders, time);
        if (index != -1) {
            Order foundOrder = orders.get(index);
            msj = " la orden a nombre de " +  foundOrder.getNameBuyer() + " tiene un precio de "+ foundOrder.getPrice() + " hecha a las   " + time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        } else if (index == -1){
            throw new IllegalArgumentException("no hay orden con esta fecha.");
        }

    
        return msj; 
    }

    public String printProducts() {
        String msj = "Lista de productos:\n";
        msj += "--------------------\n";
        for (Product p : products) {
            msj += "Nombre: " + p.getName() + "\n";
            msj += "Precio: " + p.getPrice() + "\n";
            msj += "Cantidad disponible: " + p.getAmount() + "\n";
            msj += "--------------------\n";
        }
        return msj;
    }

    public void changeAmountAvailable(String name, int newAmount) throws Exception {
        for (Product producto : products) {
            if (producto.getName().equals(name)) {
                if (newAmount < 0) {
                    throw new Exception("No hay " + name + " disponibles.");
                }
                producto.setAmount(newAmount);
                break;
            }
        }
    }
    

    public double priceList(String[] listProducts){
        double price = 0.0;
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return (int)(p1.getPrice() - p2.getPrice());
            }
        });
       for(int i =0; i< listProducts.length; i++){
          int index = search.binsearchabb(products,listProducts[i]);
          if(index >= 0 && index < products.size()){
            price += products.get(index).getPrice();
        } else {
            price += 0; 
        }
          

       }
        return price;
    }
    public boolean verification(String[] productsA){
        return  verification(productsA,products);
    }
    private boolean verification(String[] products, ArrayList<Product> listProducts) {
        for(String product : products) {
            if(!isEmpty(listProducts, product)) {
                return false; // Si un producto no está en la lista, retorna false
            }
        }
        return true; // Si todos los productos están en la lista, retorna true
    }

    public boolean isEmpty(ArrayList<Product> listProducts, String name) {
        for(Product product : listProducts) {
            if(product.getName().equals(name)) {
                return true; // Si encuentra el producto, retorna true
            }
        }
        return false; // Si no encuentra el producto, retorna false
    }



    public String getDirectionProduct() {
        return directionProduct;
    }

    public void setDirectionProduct(String directionProduct) {
        this.directionProduct = directionProduct;
    }

    public String getDirectionOrder() {
        return directionOrder;
    }

    public void setDirectionOrder(String directionOrder) {
        this.directionOrder = directionOrder;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public Binsearch getSearch() {
        return search;
    }

    public void setSearch(Binsearch search) {
        this.search = search;
    }  

    public DataBase getDb() {
        return db;
    }

}