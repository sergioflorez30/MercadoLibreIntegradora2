package test;

import org.junit.Assert;
import org.junit.Test;
import java.util.*;
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
    public void testSearchOrdername1(){
        setUpScenario3();
        try{
            register.searchOrderName("noexiste");
        }catch (IllegalArgumentException e) {
            assertEquals("comprador no encontrado.",  e.getMessage());
        }

    }
    @Test
    public void testSearchOrdername2(){
        setUpScenario3();
        try{
            register.searchOrderName("luca");
        }catch (Exception e) {
            Assert.fail("An exception was thrown when adding a valid product");
        }

    }
    @Test
    public void testSearchOrderPrice1(){
        setUpScenario3();
        try{
            register.searchOrderPrice(400);
        }catch (Exception e) {
            Assert.fail("An exception was thrown when adding a valid product");
        }
    }
    @Test
    public void testSearchOrderPrice2(){
        setUpScenario3();
        try{
            register.searchOrderPrice(-1);
        }catch (IllegalArgumentException e) {
            assertEquals("precio no encontrado.",  e.getMessage());
        }
    }
    @Test
    public void testSearchOrderPrice3(){
        setUpScenario3();
        try{
            register.searchOrderPrice(4555434);
        }catch (IllegalArgumentException e) {
            assertEquals("precio no encontrado.",  e.getMessage());
        }
    }
    @Test
    public void testSearchOrderTime1(){
        setUpScenario3();
        ArrayList<String> products;
        products =  new ArrayList<>();
        products.add("avion");
        LocalTime time;
        time = LocalTime.now().withNano(0);
        register.addOrder("sam",products,233243, time);
        try{
            register.searchOrderTime(time);
        }catch (Exception e) {
            Assert.fail("An exception was thrown when adding a valid product");
        }
    }
    @Test
    public void testSearchOrderTime2(){
        setUpScenario3();
        ArrayList<String> products;
        products =  new ArrayList<>();
        products.add("avion");
        LocalTime time;
        String horaStr = "12:34:12";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        time = LocalTime.parse(horaStr, formatter);
                    
        register.addOrder("sam",products,233243, time);
        try{
            register.searchOrderTime(time);
        }catch (IllegalArgumentException e) {
            assertEquals("no hay orden con esta fecha.",  e.getMessage());
        }
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
    public void testDataBase1(){

        setUpScenario1();

        //Both default Data Bases are succesfully added

        assertNotNull(register.getDb().getDataBaseO());

        assertNotNull(register.getDb().getDataBaseP());
        
    }

}
