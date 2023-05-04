package ui;

import java.util.Scanner;
import java.util.*;
import java.time.LocalTime;
// importamos el controller
import model.ShopeVirtual;


public class Main {

    //definimos el tipo de dato del lector
    private Scanner reader;
    // definimos el dato de la clase controller
    private ShopeVirtual controller;


    public Main() {
        reader = new Scanner(System.in);
        //definimos nombre del controller
        controller = new ShopeVirtual();


    }
    //returns the class, that is, the class becomes visible to the main method.
    public Scanner getReader(){
        return reader;
    }

    //hacemos visible el controller
    public ShopeVirtual getController(){
        return controller;
    }

    public static void main(String[] args) {

        // creación del objeto.
        Main main = new Main();
        // llamdo a uno de los metodos de la clase.
        int option = 0;

        do{

            option = main.getOptionShowMenu();
            main.executeOption(option);

        }while(option != 0);

        main.getReader().close();

    }

    public int getOptionShowMenu(){
        controller.addDataBaseObjects();
        int option = 0;
        System.out.println("<<<<< Mercado Libre Virtual Shope >>>>>");
        System.out.println(
                "1. Agregar Producto. \n" +
                        "2. Agregar Pedido. \n" +
                        "3. opcion 3 \n" +
                        "4. opcion 4\n" +
                        "0. Salir.");

        option =  validateIntegerInput();
        return option;
    }

    public void executeOption(int option){
        String name;
        String description;
        String listProducts;
        int amount;
        int typeProduct;
        double price;
        LocalTime timeBuy;
        switch(option){
            case 1:
                System.out.println("Agrega el nombre del producto.");
                name = reader.next();
                reader.nextLine();
                System.out.println("Agrega una descripcion del producto");
                description = reader.nextLine();
                System.out.println("Agrega la cantidad disponible de este producto");
                amount = reader.nextInt();
                System.out.println("Ingrese el precio del producto");
                price = reader.nextDouble();
                System.out.println("ingrese el tipo de producto entre\n " +
                        "1. Libros\n"+
                        "2.Electronica\n " +
                        "3. Ropa y accesorios\n"+
                        "4. Alimentos y bebidas\n"+
                        "5. Papeleria\n" +
                        "6.Deportes\n" +
                        "7. Belleza y cuidado personal\n"+
                        "8. Jugueteria y juegos\n");
                typeProduct = reader.nextInt();
                if(typeProduct <1 || typeProduct >8){
                    System.out.println("opcion del producto invalida :(");
                    break;
                }
                controller.addProduct(name,description,amount,price,typeProduct);
                break;

            case 2:
                System.out.println("Ingresa el nombre del comprador");
                name= reader.next();
                reader.nextLine();
                System.out.println(controller.printProducts());
                System.out.println("Ingrese los productos que quiere comprar separados por , ");
                listProducts = reader.nextLine();
                String[] products = listProducts.split(",");
                Boolean isEmpty = controller.verification(products);
                if(isEmpty == false){
                    System.out.println("un producto no esta en la lista");
                    break;
                }
                price = controller.priceList(products);
                System.out.println("El precio de su compra es: " + price);

                timeBuy = LocalTime.now();
                System.out.println("la fecha de su compra es: " + timeBuy);
                ArrayList<String> productsArray = new ArrayList<>(Arrays.asList(products));
                controller.addOrder(name,productsArray,price,timeBuy);
                break;

            case 3:

                break;

            case 4:

                break;

            case 0:
                System.out.println("Exit program.");
                controller.deletDataBases();
                break;

            default:
                System.out.println("Invalid Option");
                break;
        }
    }
    /**
     * validateIntegerInput: this method validates that the option entered by the user is actually an integer data type
     * @return option: is a int option.
     */

    public int validateIntegerInput(){
        int option = 0;

        if(reader.hasNextInt()){
            option = reader.nextInt();
        }
        else{
            // clear reader.
            reader.nextLine();
            option = -1;
        }

        return option;
    }
    /**
     * validateDoubleInput: this method validates that the option entered by the user is actually an double data type
     * @return option: is a double or int  option.
     */

}
