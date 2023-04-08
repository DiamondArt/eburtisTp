package com.eburtis.tp;

import org.junit.jupiter.api.*;


/************************************************************
 * @author Meissa kouadio <https://github.com/DiamondArt>
 * *********************************************************/
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("CalculTest")
@DisplayName("Testing Calcul class ")
public class CalculTest {
    private float a = 5;
    private float b= 2;
    Calcul calcul = new Calcul(a,b);

    @Order(value = 1)
    @DisplayName("Addition Method")
    @Test
    public void CalculAdditionTest() throws Exception {
        Assertions.assertEquals(this.calcul.additionner(a,b), 7.0);
    }

    @Order(value = 2)
    @DisplayName("Soustraire Method")
    @Test
    public void CalculSoustraireTest() throws Exception {
        Assertions.assertEquals(this.calcul.soustraire(a,b), 3.0);
    }

    @Order(value = 3)
    @DisplayName("Multiplier Method")
    @Test
    public void CalculMultiplierTest() throws Exception {
        Assertions.assertEquals(this.calcul.multiplier(a,b), 10.0);
    }

    @Order(value = 4)
    @DisplayName("Diviser Method")
    @Test
    public void CalculDiviserTest() throws Exception {
        Assertions.assertEquals(this.calcul.diviser(a,b), 7.0);
    }

    @Order(value = 5)
    @DisplayName("Carre Method")
    @Test
    public void CalculCarreTest() throws Exception {
        Assertions.assertEquals(this.calcul.carre(a), 25.0);
    }

    @Order(value = 6)
    @DisplayName("IdentiteRemarquable Method")
    @Test
    public void CalculIdentiteRemarquableTest() throws Exception {
        Assertions.assertEquals(this.calcul.identiteRemarquable(a,b), 49.0);
    }
}
