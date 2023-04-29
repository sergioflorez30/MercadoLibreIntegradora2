package test;

import org.junit.Assert;
import org.junit.Test;

import model.*;

import static org.junit.Assert.assertEquals;

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


}
