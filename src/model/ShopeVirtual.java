package model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public class ShopeVirtual {
    private ArrayList<Product> products;
    private ArrayList<Order> orders;
    private Binsearch search;
    private DataBase db;


    public ShopeVirtual() {
        products = new ArrayList<>();
        orders = new ArrayList<>();
        search = new Binsearch();
        db = new DataBase();
        addJsonO();
        addJsonP();

    }

    public void addJsonP() {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(db.getDataBaseP())) {
            Product[] productsFromJson = gson.fromJson(reader, Product[].class);
            if (productsFromJson == null) {
                return;
            } else {
                for (Product p : productsFromJson) {
                    products.add(p);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        } catch (JsonSyntaxException e) {
            System.err.println("Error al analizar el archivo JSON: " + e.getMessage());
        }
    }

    
    public void addJsonO() {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalTime.class, new LocalTimeAdapter()).create();
        try (Reader reader = new FileReader(db.getDataBaseO())) {
            Order[] productsFromJson = gson.fromJson(reader, Order[].class);
            if (productsFromJson == null) {
                return;
            } else {
                for (Order p : productsFromJson) {
                    orders.add(p);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        } catch (JsonSyntaxException e) {
            System.err.println("Error al analizar el archivo JSON: " + e.getMessage());
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
        writeJson(products,db.getDataBaseP());

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
                products.get(index).setNumber_bought(products.get(index).getNumber_bought()+1);
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
        writeJson(orders,db.getDataBaseO());
        writeJson(products,db.getDataBaseP());

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
            msj = " la orden a nombre de " +  name + " tiene un precio de "+ foundOrder.getPrice() + " y fue  hecha a las  " +  foundOrder.getDateBuy().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        } else if (index == -1){
            System.out.println("comprador no encontrado.");
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
            msj = "La orden a nombre de " + foundOrder.getNameBuyer() + " tiene un precio de " + price + "  y fue hecha a las  " +  foundOrder.getDateBuy().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        } else if (index == -1){
            System.out.println("precio no encontrado.");
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
            msj = " la orden a nombre de " +  foundOrder.getNameBuyer() + " tiene un precio de "+ foundOrder.getPrice() + " y fue hecha a las   " + time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        } else if (index == -1){
            System.out.println("no hay orden con esta fecha.");
        }

    
        return msj; 
    }
    public String searchRangePrice(int numinf, int nummax, int type){
        String msj ="Las ordenes en su rango de precios son:\n ";
        if (numinf > nummax){
            throw new IllegalArgumentException("el rango inferior no puede ser mayor al superior");
        }
        if (type < 1 || type > 2){
            throw new IllegalArgumentException("tipo invalido");
        }

        Collections.sort(orders, new Comparator<Order>() {
            @Override
            public int compare(Order p1, Order p2) {
                return (int)(p1.getPrice() - p2.getPrice());
            }
        });

        int start = search.binsearchabbRangePriceIz(orders, numinf);
        if (start < 0) {
            start = -(start + 1);
        }
        int end = search.binsearchabbRangePriceDe(orders, nummax);
        if (end < 0) {
            end = -(end + 2);
        }

        ArrayList<Order> resultados = new ArrayList<Order>();
        for (int i = start; i <= end; i++) {
            Order orden = orders.get(i);
             resultados.add(orden);
        }

        if (type == 1) {
            resultados.sort(Comparator.comparing(Order::getPrice).reversed());
            msj +="Las órdenes en el rango de precio de mayor a menor son:\n";
        } else {
            resultados.sort(Comparator.comparing(Order::getPrice));
            msj +="Las órdenes en el rango de precio de menor a mayor son:\n";
        }

        for (int i = 0; i < resultados.size(); i++) {
            msj += " la orden de precio  "+resultados.get(i).getPrice() + "  a nombre de " + resultados.get(i).getNameBuyer() +"\n";
        }

        return  msj;
    }
    public  String searchPrefOrder(String start, String end){
        if (start == null || end  == null){
            throw new IllegalArgumentException("prefijos diferentes a nulos o a numeros");
        }
        StringBuilder result = new StringBuilder();
        result.append("Sus ordenes con sus prefijos son: \n");
        Collections.sort(orders, new Comparator<Order>() {
            @Override
            public int compare(Order p1, Order p2) {
                return p1.getNameBuyer().compareTo(p2.getNameBuyer());
            }
        });
        int startIndex = search.binsearchabbPrefIz(orders, start);
        int endIndex = search.binsearchabbPrefDe(orders,end);
        if (startIndex < 0) {
            startIndex = -(startIndex + 1);
        }
        if (endIndex < 0) {
            endIndex = -(endIndex + 1);
        }


        ArrayList<Order> resultados = new ArrayList<Order>();
        for (int i = startIndex; i <= endIndex; i++) {
            Order orden = orders.get(i);
             resultados.add(orden);
        }

        for (Order orden : resultados) {
                result.append("orden a nombre de ").append(orden.getNameBuyer()).append("\n");
        }

        return result.toString();

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


    public String searchProductName(String name){
        String msj  = "";
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return p1.getName().compareTo(p2.getName());
            }
        });
        int index = search.binsearchabb(products, name);
        if (index != -1) {
            Product foundProduct = products.get(index);
            msj = " el producto a nombre de " +  name + " que tiene como descripcion "+ foundProduct.getDescription() + " y con un precio de  " +  foundProduct.getPrice()+ " ,con una cantidad disponible de "+ foundProduct.getAmount();
        } else if (index == -1){
            System.out.println("producto no encontrado.");
        }
            return msj; 
    }

    public String searchProductPrice(double price){
        String msj  = "";
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return (int)(p1.getPrice() - p2.getPrice());
            }
        });
        int index = search.binarySearchProductPrice(products, price);
        if (index != -1) {
            Product foundProduct = products.get(index);
            msj = " el producto a nombre de " +  foundProduct.getName() + " que tiene como descripcion "+ foundProduct.getDescription() + " y con un precio de  " +  price+ " ,con una cantidad disponible de "+ foundProduct.getAmount();
        } else if (index == -1){
            System.out.println("producto no encontrado.");
        }
            return msj; 
    }

    public String searchProductCategory(int Categori){
        String msj  = "";
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return p1.getCategory().compareTo(p2.getCategory());
            }
        });
        Category category;
        switch (Categori) {
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
        int index = search.binarySearchProductCategory(products, category);
        if (index != -1) {
            Product foundProduct = products.get(index);
            msj = " el producto a nombre de " +  foundProduct.getName() + " que tiene como descripcion "+ foundProduct.getDescription() + " y con un precio de  " +  foundProduct.getPrice()+ " ,con una cantidad disponible de "+ foundProduct.getAmount();
        } else if (index == -1){
            System.out.println("producto no encontrado.");
        }
            return msj; 
    }

    public String searchProductTotalBought(int amount){
        String msj  = "";
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return p1.getNumber_bought() - p2.getNumber_bought();
            }
        });
        int index = search.binarySearchProductNumberB(products, amount);
        if (index != -1) {
            Product foundProduct = products.get(index);
            msj = " el producto a nombre de " +  foundProduct.getName() + " que tiene como descripcion "+ foundProduct.getDescription() + " y con un precio de  " +  foundProduct.getPrice()+ " ,con una cantidad disponible de "+ foundProduct.getAmount();
        } else if (index == -1){
            System.out.println("producto no encontrado.");
        }
            return msj; 
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