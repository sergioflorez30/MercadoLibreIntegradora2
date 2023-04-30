package model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.time.LocalTime;

import com.google.gson.Gson;

public class ShopeVirtual {
    private String directionProduct;
    private String directionOrder;
    private ArrayList<Product> products;
    private ArrayList<Order> orders;
    private Binsearch search;

    public ShopeVirtual() {
        products = new ArrayList<>();
        orders = new ArrayList<>();
        search = new Binsearch();
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

    public void writeJson(ArrayList list, String direction) {
        Gson gson = new Gson();
        String json = gson.toJson(list);

        try {
            FileOutputStream fos = new FileOutputStream(new File(direction));
            fos.write(json.getBytes(StandardCharsets.UTF_8));
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addProduct(String name, String description, int amount, double price, int typeNum) {
        String type = "";
        switch (typeNum) {
            case 1:
                type = "Libros";
                break;
            case 2:
                type = "Electronica";
                break;
            case 3:
                type = "Ropa y accesorio";
                break;
            case 4:
                type = "Alimentos y bebidas";
                break;
            case 5:
                type = "Papeleria";
                break;
            case 6:
                type = "Deportes";
                break;
            case 7:
                type = "Productos de belleza y cuidado personal";
            case 8:
                type = "Juguetes y juegos";
                break;
        }
        if (price < 0|| amount<0) {
            throw new IllegalArgumentException("no puedes usar valores negativos para el precio o cantidad.");
        }
        int index = search.binsearchabb(products, name);

        if(index==-1){  
        Product product = new Product(name, description, price, amount, type, 0);
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
        if (listProducts == null){
            throw new IllegalArgumentException("no hay productos en tu lista.");
        }
        if (name == null){
            throw new IllegalArgumentException("el nombre del comprador no existe");
        }
        orders.add(order);

    }
    public void printProducts() {
        System.out.println("Lista de productos:");
        System.out.println("--------------------");
        for (Product p : products) {
            System.out.println("Nombre: " + p.getName());
            System.out.println("Precio: " + p.getPrice());
            System.out.println("Cantidad disponible: " + p.getAmount());
            System.out.println("--------------------");
        }
    }

    public double priceList(String[] listProducts){
        double price = 0.0;
       for(int i =0; i< listProducts.length; i++){
          int index = search.binsearchabb(products,listProducts[i]);
          price += products.get(index).getPrice();

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



    

}