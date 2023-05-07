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

public class SearchTest {

    private ShopeVirtual register;

    public void setUpScenario1() {
        register = new ShopeVirtual();
        register.addProduct("avion", "///", 44, 400, 8);

    }
    public  void setUpScenario2(){
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
    public void testSearchOrdername1(){
        setUpScenario2();
        try{
            register.searchOrderName("noexiste");
        }catch (IllegalArgumentException e) {
            assertEquals("comprador no encontrado.",  e.getMessage());
        }

    }
    @Test
    public void testSearchOrdername2(){
        setUpScenario2();
        try{
            register.searchOrderName("luca");
        }catch (Exception e) {
            Assert.fail("An exception was thrown when adding a valid product");
        }

    }
    @Test
    public void testSearchOrderPrice1(){
        setUpScenario2();
        try{
            register.searchOrderPrice(400);
        }catch (Exception e) {
            Assert.fail("An exception was thrown when adding a valid product");
        }
    }
    @Test
    public void testSearchOrderPrice2(){
        setUpScenario2();
        try{
            register.searchOrderPrice(-1);
        }catch (IllegalArgumentException e) {
            assertEquals("precio no encontrado.",  e.getMessage());
        }
    }
    @Test
    public void testSearchOrderPrice3(){
        setUpScenario2();
        try{
            register.searchOrderPrice(4555434);
        }catch (IllegalArgumentException e) {
            assertEquals("precio no encontrado.",  e.getMessage());
        }
    }
    @Test
    public void testSearchOrderTime1(){
        setUpScenario2();
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
        setUpScenario2();
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
        setUpScenario2();
        try{
            register.searchRangePrice(300,500,1);
        }catch (Exception e) {
            Assert.fail("An exception was thrown when adding a valid product");
        }
    }
    @Test
    public  void  testSearchRangePrice2(){
        setUpScenario2();
        try{
            register.searchRangePrice(100,50,2);
        }catch (Exception e) {
            assertEquals("el rango inferior no puede ser mayor al superior",  e.getMessage());
        }

    }
    @Test
    public void testSearchRangePrice3(){
        setUpScenario2();
        try{
            register.searchRangePrice(5,20,3);
        }catch (Exception e) {
            assertEquals("tipo invalido",  e.getMessage());
        }
    }
    @Test
    public  void  testSearchRangePref1(){
        setUpScenario2();
        try{
            register.searchPrefOrder("a","es");
        }catch (Exception e) {
            fail("An exception was thrown when adding a valid product");
        }
    }
    @Test
    public  void  testSearchRanfePref2(){
        setUpScenario2();
        try{
            register.searchPrefOrder(null,"es");
        }catch (Exception e) {
            assertEquals("prefijos diferentes a nulos o a numeros",  e.getMessage());
        }
    }
    @Test
    public  void  testSearchRanfePref3(){
        setUpScenario2();
        try{
            register.searchPrefOrder("ser",null);
        }catch (Exception e) {
            assertEquals("prefijos diferentes a nulos o a numeros",  e.getMessage());
        }
    }
    @Test
    public  void  testSearchRangePref4(){
        setUpScenario2();
        try{
            register.searchPrefOrder("c","es");
        }catch (Exception e) {
            fail("An exception was thrown when adding a valid product");
        }
    }

    @Test
    public  void  testSearchRangePref5(){
        setUpScenario2();
        try{
            register.searchPrefOrder(null, null);
        }catch (Exception e) {
            assertEquals("prefijos diferentes a nulos o a numeros",  e.getMessage());
        }
    }
    @Test
    public  void  testSearchRangePref6(){
        setUpScenario2();
        try{
            register.searchPrefOrder(Integer.toString(5), Integer.toString(10));
        }catch (Exception e) {
            assertEquals("prefijos diferentes a nulos o a numeros",  e.getMessage());
        }
    }

}
