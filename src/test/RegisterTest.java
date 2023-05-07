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
    public void testSearchRangePrice1(){
        setUpScenario3();
        try{
            register.searchRangePrice(300,500,1);
        }catch (Exception e) {
            Assert.fail("An exception was thrown when adding a valid product");
        }
    }
    @Test
    public  void  testSearchRangePrice2(){
        setUpScenario3();
        try{
            register.searchRangePrice(100,50,2);
        }catch (Exception e) {
            assertEquals("el rango inferior no puede ser mayor al superior",  e.getMessage());
        }

    }
   @Test
   public void testSearchRangePrice3(){
       setUpScenario3();
       try{
           register.searchRangePrice(5,20,3);
       }catch (Exception e) {
           assertEquals("tipo invalido",  e.getMessage());
       }
   }
   @Test
   public  void  testSearchRangePref1(){
        setUpScenario3();
       try{
           register.searchPrefOrder("a","es");
       }catch (Exception e) {
           Assert.fail("An exception was thrown when adding a valid product");
       }
   }
   @Test
   public  void  testSearchRanfePref2(){
        setUpScenario3();
       try{
           register.searchPrefOrder(null,"es");
       }catch (Exception e) {
           assertEquals("prefijos diferentes a nulos",  e.getMessage());
       }
   }
    @Test
    public  void  testSearchRanfePref3(){
        setUpScenario3();
        try{
            register.searchPrefOrder("ser",null);
        }catch (Exception e) {
            assertEquals("prefijos diferentes a nulos",  e.getMessage());
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

        file.delete();
        
    }

    @Test
    public void writeJSON4(){

        setUpScenario2();

        File file = new File("newFile.json");

        register.writeJson(register.getProducts(), file);

        assertEquals(2, register.getProducts().size());

        assertFalse(register.getProducts().isEmpty());

        register.getProducts().clear();

        assertEquals(0, register.getProducts().size());

        assertTrue(register.getProducts().isEmpty());

    }

    @Test

    public void writeJSON5(){

        setUpScenario2();

        File file = new File("newFile.json");

        register.getDb().setDataBaseP(file);

        register.writeJson(register.getProducts(), file);

        assertNotNull(file);

        assertFalse(register.getProducts().isEmpty());

        file.delete();
    }

    @Test

    public void addJsonO1(){

        setUpScenario1();

        File file = new File("newFile.json");

        register.getDb().setDataBaseO(file);

        register.addJsonO();

        assertNotNull(register.getDb().getDataBaseO());

    }

    @Test

    public void addJsonO2(){

        setUpScenario1();

        File file = new File("newFile.json");

        register.getDb().setDataBaseO(file);

        ArrayList<String> products;

        products =  new ArrayList<>();

        products.add("avion");

        LocalTime time;

        time = LocalTime.now().withNano(0);

        register.addOrder("luca",products,400, time);

        register.addJsonO();

        assertFalse(register.getOrders().isEmpty());

    }

    @Test

    public void addJsonO3(){

        setUpScenario1();

        File file = new File("newFile.json");

        register.getDb().setDataBaseO(file);

        ArrayList<String> products;

        products =  new ArrayList<>();

        products.add("avion");

        LocalTime time;

        time = LocalTime.now().withNano(0);

        register.addOrder("luca",products,400, time);

        register.addJsonO();

        assertTrue(!register.getOrders().isEmpty());

        register.getOrders().clear();

        assertEquals(0, register.getOrders().size());

        register.addJsonO();

        assertNotNull(register.getDb().getDataBaseO());

    }

    @Test

    public void addJsonO4(){

        //The default json is added correctly

        setUpScenario1();

        register.addJsonO();

        assertNotNull(register.getDb().getDataBaseO());

    }

    @Test

    public void addJsonO5(){

        setUpScenario3();

        register.addJsonO();

        assertNotNull(register.getDb().getDataBaseO());

        register.getOrders().clear();

        assertEquals(0, register.getOrders().size());

        ArrayList<String> products;

        products =  new ArrayList<>();

        products.add("avion");

        LocalTime time;

        time = LocalTime.now().withNano(0);

        register.addOrder("luca",products,400, time);

        assertEquals(1, register.getOrders().size());

    }

    @Test

    public void addJsonP1(){

        setUpScenario1();

        register.addJsonO();

        assertNotNull(register.getDb().getDataBaseP());

    }

    @Test

    public void addJsonP2(){

        setUpScenario1();

        File file = new File("newFile.json");

        register.getDb().setDataBaseP(file);

        register.addJsonO();

        assertNotNull(register.getDb().getDataBaseP());

    }

    @Test

    public void addJsonP3(){

        setUpScenario2();

        register.getProducts().clear();

        register.addJsonO();

        assertTrue(register.getProducts().isEmpty());

    }

    @Test

    public void addJsonP4(){

        setUpScenario2();

        register.getProducts().clear();

        File file = new File("newFile.json");

        register.getDb().setDataBaseP(file);

        register.addProduct("avion", "///", 44, 400, 8);

        register.addProduct("Dino", "///", 45, 4009, 5);

        register.addJsonO();

        assertNotNull(register.getDb().getDataBaseP());

    }

    @Test

    public void addJsonP5(){

        setUpScenario2();

        register.getProducts().clear();

        File file = new File("newFile.json");

        register.getDb().setDataBaseP(file);

        register.addProduct("avion", "///", 44, 400, 8);

        register.addProduct("Dino", "///", 45, 4009, 5);

        register.addJsonO();

        assertNotNull(register.getDb().getDataBaseP());

        register.getProducts().clear();

        assertEquals(0, register.getProducts().size());

        register.addJsonO();

        assertNotNull(register.getDb().getDataBaseP());

    }


}
