package ui;

import java.util.Scanner;
import java.util.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
        int option = 0;
        System.out.println("<<<<< Mercado Libre Virtual Shope >>>>>");
        System.out.println(
                "1. Agregar Producto. \n" +
                        "2. Agregar Pedido. \n" +
                        "3. opcion 3 \n" +
                        "4. Busqueda de ordenes\n" +
                        "0. Salir.");

        option =  validateIntegerInput();
        return option;
    }

    public void executeOption(int option){
        String msj;         
        String name;
        String description;
        String listProducts;
        int amount;
        int typeProduct;
        int type; 
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

                timeBuy = LocalTime.now().withNano(0);
                String formattedTime = String.format("%02d:%02d:%02d", timeBuy.getHour(), timeBuy.getMinute(), timeBuy.getSecond());
                System.out.println("la fecha de su compra es: " + formattedTime);
                ArrayList<String> productsArray = new ArrayList<>(Arrays.asList(products));
                controller.addOrder(name,productsArray,price,timeBuy);
                break;

            case 3:

                break;

            case 4:
            System.out.println(
                "Ingrese que tipo de busqueda quiere tener"+
                "1.Busqueda por nombre "+
                "2. Busqueda por precio total" +
                "3. fecha de compra.");
                type = reader.nextInt();
                if(type <1 || type >4){
                    System.out.println("seleccione un tipo valido");
                    break; 
                }
                if(type ==1){
                    System.out.println("ingrese el nombre del comprador");
                    name = reader.next();
                   msj = controller.searchOrderName(name); 
                   System.out.println(msj);                    
                }
                 else if(type == 2){
                    System.out.println("ingrese el precio");
                    price = reader.nextDouble();
                    msj = controller.searchOrderPrice(price);
                    System.out.println(msj);
                }else{
                    System.out.println("ingrese la fecha de compra");
                    System.out.print("Ingrese una hora en formato HH:mm:ss ");
                    String horaStr = reader.next();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    LocalTime hora;
                    try {
                        hora = LocalTime.parse(horaStr, formatter);
                        msj = controller.searchOrderTime(hora); 
                        System.out.println(msj);
                    } catch (DateTimeParseException e) {
                        System.out.println("La hora ingresada no está en el formato esperado (HH:mm:ss)");
                    }
                    
                }
                


                break;

            case 0:
                System.out.println("Exit program.");
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
