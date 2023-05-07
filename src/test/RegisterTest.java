package test;
import org.junit.Assert;
import org.junit.Test;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import model.*;

import static org.junit.Assert.*;

// confiedilidad = (testTotales-testfallidos)/testTotales
// confiedilidad = (testTotales-testfallidos)/testTotales

public class RegisterTest {

    private ShopeVirtual register;

    public void setUpScenario1() {
        register = new ShopeVirtual();
        register.addProduct("avion", "///", 44, 400, 8);

    }

    public void setUpScenario2() {
        setUpScenario1();
        register.addProduct("carro", "///", 1, 200, 8);

    }
    public  void setUpScenario3(){
        setUpScenario1();
        register = new ShopeVirtual();
        ArrayList<String> products;
        products =  new ArrayList<>();
        products.add("avion");
        LocalTime time;
        time = LocalTime.now().withNano(0);
        register.addOrder("luca",products,400, time);
    }

    @Test
    public  void testAddOrder1(){
        setUpScenario3();
        ArrayList<String> products;
        products =  new ArrayList<>();
        products.add("avion");
        LocalTime time;
        time = LocalTime.now().withNano(0);
        try {
            register.addOrder("luis", products, 400, time);
        } catch (Exception e) {
            Assert.fail("An exception was thrown when adding a valid product");
        }

    }
    @Test
    public  void testAddOrder2(){
        setUpScenario3();
        ArrayList<String> products;
        products =  new ArrayList<>();
        products.add("avion");
        LocalTime time;
        time = LocalTime.now().withNano(0);
        try {
            register.addOrder("luis", products, -10000, time);
        } catch (IllegalArgumentException e) {
            assertEquals("no puedes usar valores neutros o negativos para el precio.",  e.getMessage());
        }

    }
    @Test
    public  void testAddOrder3(){
        setUpScenario3();
        ArrayList<String> products;
        products =  new ArrayList<>();
        products.add("avion");
        LocalTime time;
        time = LocalTime.now().withNano(0);
        try {
            register.addOrder("luis", null, 10000, time);
        } catch (IllegalArgumentException e) {
            assertEquals("no hay productos en tu lista.",  e.getMessage());
        }

    }
    @Test
    public  void testAddOrder4(){
        setUpScenario3();
        ArrayList<String> products;
        products =  new ArrayList<>();
        products.add("avion");
        LocalTime time;
        time = LocalTime.now().withNano(0);
        try {
            register.addOrder(null, products, 10000, time);
        } catch (IllegalArgumentException e) {
            assertEquals("el nombre del comprador no existe",  e.getMessage());
        }

    }
    @Test
    public  void testAddOrder5(){
        setUpScenario3();
        ArrayList<String> products;
        products =  new ArrayList<>();
        products.add("avion");
        LocalTime time;
        time = LocalTime.now().withNano(0);
        register.addOrder("sergio", products, 10000, time);
        assertEquals("sergio", register.getOrders().get(1).getNameBuyer());
    }

    @Test
    public void testaddProduct1() {
        setUpScenario1();
        try {
            register.addProduct("Camisa", "camisa manga larga",50, 600, 3);
        } catch (Exception e) {
            Assert.fail("An exception was thrown when adding a valid product");
        }
    }

    @Test
    public void testaddProduct2() {
        setUpScenario1();
        try {
            register.addProduct("Carro", "carro rojo",10, -110, 8);
        }  catch (IllegalArgumentException e) {
            assertEquals("no puedes usar valores negativos para el precio o cantidad.", e.getMessage());
        }

    }

    @Test
    public void testaddProduct3() {
        setUpScenario1();
        register.addProduct("avion", "///", 44, 400, 8);
        assertEquals(88, register.getProducts().get(0).getAmount());
    }
    @Test
    public void testaddProduct4() {
        setUpScenario1();
        try {
            register.addProduct("Carro", "carro rojo",-10, 110, 8);
        }  catch (IllegalArgumentException e) {
            assertEquals("no puedes usar valores negativos para el precio o cantidad.", e.getMessage());
        }
    }

    @Test
    public void testaddProduct5() {
        setUpScenario1();
        try {
            register.addProduct("Carro", "carro rojo",-10, -110, 8);
        }  catch (IllegalArgumentException e) {
            assertEquals("no puedes usar valores negativos para el precio o cantidad.", e.getMessage());
        }
    }
    
    @Test
    public void writeJSON1(){

        setUpScenario1();

        //Both default Data Bases are succesfully added

        assertNotNull(register.getDb().getDataBaseO());

        assertNotNull(register.getDb().getDataBaseP());
        
    }

    @Test
    public void writeJSON2() throws IOException{

        setUpScenario1();

        register.getProducts().clear();

        String filename = "empty.json";

        File file = new File(filename);

        file.createNewFile();

        register.getDb().setDataBaseP(file);

        register.addJsonP();  

        assertEquals(0, register.getProducts().size());
        
        file.delete();
    }

    @Test
    public void writeJSON3() throws IOException{

        setUpScenario1();

        register.getProducts().clear();

        String filename = "newJSON.json";

        File file = new File(filename);

        file.createNewFile();

        register.writeJson(register.getProducts(), file);

        register.getDb().setDataBaseP(file);

        register.addJsonP();  

        register.addProduct("avion", "///", 44, 400, 8);

        assertNotNull(register.getProducts());

        assertEquals(1, register.getProducts().size());

        assertFalse(register.getProducts().isEmpty());
        
    }

}
