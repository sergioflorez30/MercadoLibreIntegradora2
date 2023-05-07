package test;

import org.junit.Assert;
import org.junit.Test;
import java.util.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import model.*;

import static org.junit.Assert.*;

public class SearchTest {

    private ShopeVirtual search;

    public void setUpScenario1() {
        search = new ShopeVirtual();
        search.addProduct("avion", "///", 44, 400, 8);

    }

    public void setUpScenario2() {
        setUpScenario1();
        search = new ShopeVirtual();
        ArrayList<String> products;
        products = new ArrayList<>();
        products.add("avion");
        LocalTime time;
        time = LocalTime.now().withNano(0);
        search.addOrder("luca", products, 400, time);
    }

    @Test
    public void testSearchOrdername1() {
        setUpScenario2();
        try {
            search.searchOrderName("noexiste");
        } catch (IllegalArgumentException e) {
            assertEquals("comprador no encontrado.", e.getMessage());
        }

    }

    @Test
    public void testSearchOrdername2() {
        setUpScenario2();
        try {
            search.searchOrderName("luca");
        } catch (Exception e) {
            Assert.fail("An exception was thrown when adding a valid product");
        }

    }

    @Test
    public void testSearchOrderPrice1() {
        setUpScenario2();
        try {
            search.searchOrderPrice(400);
        } catch (Exception e) {
            Assert.fail("An exception was thrown when adding a valid product");
        }
    }

    @Test
    public void testSearchOrderPrice2() {
        setUpScenario2();
        try {
            search.searchOrderPrice(-1);
        } catch (IllegalArgumentException e) {
            assertEquals("precio no encontrado.", e.getMessage());
        }
    }

    @Test
    public void testSearchOrderPrice3() {
        setUpScenario2();
        try {
            search.searchOrderPrice(4555434);
        } catch (IllegalArgumentException e) {
            assertEquals("precio no encontrado.", e.getMessage());
        }
    }

    @Test
    public void testSearchOrderTime1() {
        setUpScenario2();
        ArrayList<String> products;
        products = new ArrayList<>();
        products.add("avion");
        LocalTime time;
        time = LocalTime.now().withNano(0);
        search.addOrder("sam", products, 233243, time);
        try {
            search.searchOrderTime(time);
        } catch (Exception e) {
            Assert.fail("An exception was thrown when adding a valid product");
        }
    }

    @Test
    public void testSearchOrderTime2() {
        setUpScenario2();
        ArrayList<String> products;
        products = new ArrayList<>();
        products.add("avion");
        LocalTime time;
        String horaStr = "12:34:12";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        time = LocalTime.parse(horaStr, formatter);

        search.addOrder("sam", products, 233243, time);
        try {
            search.searchOrderTime(time);
        } catch (IllegalArgumentException e) {
            assertEquals("no hay orden con esta fecha.", e.getMessage());
        }
    }

    @Test
    public void testSearchRangePrice1() {
        setUpScenario2();
        try {
            search.searchRangePrice(300, 500, 1);
        } catch (Exception e) {
            Assert.fail("An exception was thrown when adding a valid product");
        }
    }

    @Test
    public void testSearchRangePrice2() {
        setUpScenario2();
        try {
            search.searchRangePrice(100, 50, 2);
        } catch (Exception e) {
            assertEquals("el rango inferior no puede ser mayor al superior", e.getMessage());
        }

    }

    @Test
    public void testSearchRangePrice3() {
        setUpScenario2();
        try {
            search.searchRangePrice(5, 20, 3);
        } catch (Exception e) {
            assertEquals("tipo invalido", e.getMessage());
        }
    }

    @Test
    public void testSearchRangePref1() {
        setUpScenario2();
        try {
            search.searchPrefOrder("a", "es");
        } catch (Exception e) {
            fail("An exception was thrown when adding a valid product");
        }
    }

    @Test
    public void testSearchRanfePref2() {
        setUpScenario2();
        try {
            search.searchPrefOrder(null, "es");
        } catch (Exception e) {
            assertEquals("prefijos diferentes a nulos o a numeros", e.getMessage());
        }
    }

    @Test
    public void testSearchRanfePref3() {
        setUpScenario2();
        try {
            search.searchPrefOrder("ser", null);
        } catch (Exception e) {
            assertEquals("prefijos diferentes a nulos o a numeros", e.getMessage());
        }
    }

    @Test
    public void testSearchRangePref4() {
        setUpScenario2();
        try {
            search.searchPrefOrder("c", "es");
        } catch (Exception e) {
            fail("An exception was thrown when adding a valid product");
        }
    }

    @Test
    public void testSearchRangePref5() {
        setUpScenario2();
        try {
            search.searchPrefOrder(null, null);
        } catch (Exception e) {
            assertEquals("prefijos diferentes a nulos o a numeros", e.getMessage());
        }
    }

    @Test
    public void testSearchRangePref6() {
        setUpScenario2();
        try {
            search.searchPrefOrder(Integer.toString(5), Integer.toString(10));
        } catch (Exception e) {
            assertEquals("prefijos diferentes a nulos o a numeros", e.getMessage());
        }
    }

    @Test
    public void testSearchPname1() {
        setUpScenario2();
        try {
            search.searchOrderName("noexiste");
        } catch (IllegalArgumentException e) {
            assertEquals("comprador no encontrado.", e.getMessage());
        }


    }
    @Test
    public void testSearchPname2() {
        setUpScenario2();
        try {
            search.searchProductName("avion");
        } catch (Exception e) {
            Assert.fail("An exception was thrown when adding a valid product");
        }


    }


    @Test
    public void testSearchPprice1() {
        setUpScenario2();
        try {
            search.searchProductPrice(400);
        } catch (Exception e) {
            Assert.fail("An exception was thrown when adding a valid product");
        }

    }
    
    @Test
    public void testSearchPprice2() {
        setUpScenario2();
        try {
            search.searchProductPrice(-1);
        } catch (IllegalArgumentException e) {
            assertEquals("precio no encontrado.", e.getMessage());
        }
    }
    
    @Test
    public void testSearchPprice3() {
        setUpScenario2();
        try {
            search.searchProductPrice(4555434);
        } catch (IllegalArgumentException e) {
            assertEquals("precio no encontrado.", e.getMessage());
        }

    }

    
    @Test
    public void testSearchPCategoria1() {
        setUpScenario2();
        try {
            search.searchProductCategory(1);
        } catch (IllegalArgumentException e) {
            assertEquals("precio no encontrado.", e.getMessage());
        }

    }

    @Test
    public void testSearchPCategoria2() {
        setUpScenario2();
        try {
            search.searchProductCategory(99);
        } catch (IllegalArgumentException e) {
            assertEquals("categoria no encontrado.", e.getMessage());
        }

    }

    @Test
    public void testSearchPCategoria3() {
        setUpScenario2();
        try {
            search.searchProductCategory(-1);
        } catch (IllegalArgumentException e) {
            assertEquals("categoria no encontrado.", e.getMessage());
        }


    }


    @Test
    public void testSearchPBought1() {
        setUpScenario1();
        try {
            search.searchProductTotalBought(126);
        } catch (IllegalArgumentException e) {
            assertEquals("producto no encontrado.", e.getMessage());
        }

    }
    @Test
    public void testSearchPBought2() {
        setUpScenario2();
        try {
            search.searchProductTotalBought(0);
        } catch (IllegalArgumentException e) {
            assertEquals("producto no encontrado.", e.getMessage());
        }

    }

    @Test
    public void testSearchPBought3() {
        setUpScenario2();
        try {
            search.searchProductTotalBought(-1);
        } catch (IllegalArgumentException e) {
            assertEquals("producto no encontrado.", e.getMessage());
        }

    }



    @Test
    public void testSearchRangePrice1P() {
        setUpScenario2();
        try {
            search.searchRangeProductNumeric(200,400,1,2);
        } catch (IllegalArgumentException e) {
            Assert.fail("An exception was thrown when adding a valid product");
        }

    }

    

    @Test
    public void testSearchRangePrice2P() {
        setUpScenario2();
        try {
            search.searchRangeProductNumeric(0,0,1,2);
        } catch (IllegalArgumentException e) {
            Assert.fail("An exception was thrown when adding a valid product");
        }

    }

    @Test
    public void testSearchRangePrice3P() {
        setUpScenario2();
        try {
            search.searchRangeProductNumeric(-1,200,1,2);
        } catch (IllegalArgumentException e) {
            Assert.fail("An exception was thrown when adding a valid product");
        }

    }

    @Test
    public void testSearchRangePrice4P() {
        setUpScenario2();
        try {
            search.searchRangeProductNumeric(-1,-1,1,2);
        } catch (IllegalArgumentException e) {
            Assert.fail("An exception was thrown when adding a valid product");
        }

    }

    @Test
    public void testSearchRangePrice5P() {
        setUpScenario2();
        try {
            search.searchRangeProductNumeric(0,1000000,1,2);
        } catch (IllegalArgumentException e) {
            Assert.fail("An exception was thrown when adding a valid product");
        }
    }


    @Test
    public void testSearchRangeAmount1P() {
        setUpScenario2();
        try {
            search.searchRangeProductNumeric(2,100,2,2);
        } catch (IllegalArgumentException e) {
            Assert.fail("An exception was thrown when adding a valid product");
        }

    }

    @Test
    public void testSearchRangeAmount2P() {
        setUpScenario2();
        try {
            search.searchRangeProductNumeric(100,100,2,2);
        } catch (IllegalArgumentException e) {
            Assert.fail("An exception was thrown when adding a valid product");
        }


    }

    @Test
    public void testSearchRangeAmount3P() {
        setUpScenario2();
        try {
            search.searchRangeProductNumeric(0,-100,2,2);
        } catch (IllegalArgumentException e) {
            Assert.fail("An exception was thrown when adding a valid product");
        }


    }

    @Test
    public void testSearchRangeAmount4P() {
        setUpScenario2();
        try {
            search.searchRangeProductNumeric(-10,-100,2,2);
        } catch (IllegalArgumentException e) {
            Assert.fail("An exception was thrown when adding a valid product");
        }



    }

    @Test
    public void testSearchRangeAmount5P() {

        setUpScenario2();
        try {
            search.searchRangeProductNumeric(0,0,2,2);
        } catch (IllegalArgumentException e) {
            Assert.fail("An exception was thrown when adding a valid product");
        }
    }

    @Test
    public void testSearchRangeBought1P() {
        
        setUpScenario2();
        try {
            search.searchRangeProductNumeric(2,5,3,2);
        } catch (IllegalArgumentException e) {
            Assert.fail("An exception was thrown when adding a valid product");
        }

    }

    @Test
    public void testSearchRangeBought2P() {

        setUpScenario2();
        try {
            search.searchRangeProductNumeric(5,5,3,2);
        } catch (IllegalArgumentException e) {
            Assert.fail("An exception was thrown when adding a valid product");
        }
    }

    @Test
    public void testSearchRangeBought3P() {
        setUpScenario2();
        try {
            search.searchRangeProductNumeric(-1,-5,3,2);
        } catch (IllegalArgumentException e) {
            Assert.fail("An exception was thrown when adding a valid product");
        }

    }

    @Test
    public void testSearchRangeBought4P() {

        setUpScenario2();
        try {
            search.searchRangeProductNumeric(-1,5,3,2);
        } catch (IllegalArgumentException e) {
            Assert.fail("An exception was thrown when adding a valid product");
        }

    }

    @Test
    public void testSearchRangeBought5P() {
        setUpScenario2();
        try {
            search.searchRangeProductNumeric(1,-5,3,2);
        } catch (IllegalArgumentException e) {
            Assert.fail("An exception was thrown when adding a valid product");
        }

    }



}
