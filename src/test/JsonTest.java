package test;
import org.junit.Test;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;

import model.*;

import static org.junit.Assert.*;
public class JsonTest {

    private ShopeVirtual json;

    public void setUpScenario1() {
        json = new ShopeVirtual();
        json.addProduct("avion", "///", 44, 400, 8);

    }
    public void setUpScenario2() {
        setUpScenario1();
        json.addProduct("carro", "///", 1, 200, 8);

    }
    public  void setUpScenario3(){
        setUpScenario1();
        json = new ShopeVirtual();
        ArrayList<String> products;
        products =  new ArrayList<>();
        products.add("avion");
        LocalTime time;
        time = LocalTime.now().withNano(0);
        json.addOrder("luca",products,400, time);
    }

    @Test
    public void writeJSON1(){

        setUpScenario1();

        //Both default Data Bases are succesfully added

        assertNotNull(json.getDb().getDataBaseO());

        assertNotNull(json.getDb().getDataBaseP());

    }

    @Test
    public void writeJSON2() throws IOException{

        setUpScenario1();

        json.getProducts().clear();

        String filename = "empty.json";

        File file = new File(filename);

        file.createNewFile();

        json.getDb().setDataBaseP(file);

        json.addJsonP();

        assertEquals(0, json.getProducts().size());

        file.delete();
    }

    @Test
    public void writeJSON3() throws IOException{

        setUpScenario1();

        json.getProducts().clear();

        String filename = "newJSON.json";

        File file = new File(filename);

        file.createNewFile();

        json.writeJson(json.getProducts(), file);

        json.getDb().setDataBaseP(file);

        json.addJsonP();

        json.addProduct("avion", "///", 44, 400, 8);

        assertNotNull(json.getProducts());

        assertEquals(1, json.getProducts().size());

        assertFalse(json.getProducts().isEmpty());

        file.delete();

    }

    @Test
    public void writeJSON4(){

        setUpScenario2();

        File file = new File("newFile.json");

        json.writeJson(json.getProducts(), file);

        assertEquals(8, json.getProducts().size());

        assertFalse(json.getProducts().isEmpty());

        json.getProducts().clear();

        assertEquals(0, json.getProducts().size());

        assertTrue(json.getProducts().isEmpty());

    }

    @Test

    public void writeJSON5(){

        setUpScenario2();

        File file = new File("newFile.json");

        json.getDb().setDataBaseP(file);

        json.writeJson(json.getProducts(), file);

        assertNotNull(file);

        assertFalse(json.getProducts().isEmpty());

        file.delete();
    }

    @Test

    public void addJsonO1(){

        setUpScenario1();

        File file = new File("newFile.json");

        json.getDb().setDataBaseO(file);

        json.addJsonO();

        assertNotNull(json.getDb().getDataBaseO());

    }

    @Test

    public void addJsonO2(){

        setUpScenario1();

        File file = new File("newFile.json");

        json.getDb().setDataBaseO(file);

        ArrayList<String> products;

        products =  new ArrayList<>();

        products.add("avion");

        LocalTime time;

        time = LocalTime.now().withNano(0);

        json.addOrder("luca",products,400, time);

        json.addJsonO();

        assertFalse(json.getOrders().isEmpty());

    }

    @Test

    public void addJsonO3(){

        setUpScenario1();

        File file = new File("newFile.json");

        json.getDb().setDataBaseO(file);

        ArrayList<String> products;

        products =  new ArrayList<>();

        products.add("avion");

        LocalTime time;

        time = LocalTime.now().withNano(0);

        json.addOrder("luca",products,400, time);

        json.addJsonO();

        assertTrue(!json.getOrders().isEmpty());

        json.getOrders().clear();

        assertEquals(0, json.getOrders().size());

        json.addJsonO();

        assertNotNull(json.getDb().getDataBaseO());

    }

    @Test

    public void addJsonO4(){

        //The default json is added correctly

        setUpScenario1();

        json.addJsonO();

        assertNotNull(json.getDb().getDataBaseO());

    }

    @Test

    public void addJsonO5(){

        setUpScenario3();

        json.addJsonO();

        assertNotNull(json.getDb().getDataBaseO());

        json.getOrders().clear();

        assertEquals(0, json.getOrders().size());

        ArrayList<String> products;

        products =  new ArrayList<>();

        products.add("avion");

        LocalTime time;

        time = LocalTime.now().withNano(0);

        json.addOrder("luca",products,400, time);

        assertEquals(1, json.getOrders().size());

    }

    @Test

    public void addJsonP1(){

        setUpScenario1();

        json.addJsonO();

        assertNotNull(json.getDb().getDataBaseP());

    }

    @Test

    public void addJsonP2(){

        setUpScenario1();

        File file = new File("newFile.json");

        json.getDb().setDataBaseP(file);

        json.addJsonO();

        assertNotNull(json.getDb().getDataBaseP());

    }

    @Test

    public void addJsonP3(){

        setUpScenario2();

        json.getProducts().clear();

        json.addJsonO();

        assertTrue(json.getProducts().isEmpty());

    }

    @Test

    public void addJsonP4(){

        setUpScenario2();

        json.getProducts().clear();

        File file = new File("newFile.json");

        json.getDb().setDataBaseP(file);

        json.addProduct("avion", "///", 44, 400, 8);

        json.addProduct("Dino", "///", 45, 4009, 5);

        json.addJsonO();

        assertNotNull(json.getDb().getDataBaseP());

    }

    @Test

    public void addJsonP5(){

        setUpScenario2();

        json.getProducts().clear();

        File file = new File("newFile.json");

        json.getDb().setDataBaseP(file);

        json.addProduct("avion", "///", 44, 400, 8);

        json.addProduct("Dino", "///", 45, 4009, 5);

        json.addJsonO();

        assertNotNull(json.getDb().getDataBaseP());

        json.getProducts().clear();

        assertEquals(0, json.getProducts().size());

        json.addJsonO();

        assertNotNull(json.getDb().getDataBaseP());

    }


}
