package oblig3.test;

import oblig3.MengdeADT;
import oblig3.JavaSetToMengde;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

class JavaSetToMengdeTest {

    private JavaSetToMengde<Integer> mengde;

    @BeforeEach
    void setUp() {
        mengde = new JavaSetToMengde<>();
    }

    @Test
    void testErTom() {
        assertTrue(mengde.erTom());
        mengde.leggTil(1);
        assertFalse(mengde.erTom());
    }

    @Test
    void testInneholder() {
        mengde.leggTil(1);
        mengde.leggTil(2);

        assertTrue(mengde.inneholder(1));
        assertTrue(mengde.inneholder(2));
        assertFalse(mengde.inneholder(3));
    }

    @Test
    void testFjern() {
        mengde.leggTil(1);
        mengde.leggTil(2);

        assertEquals(1, mengde.fjern(1));
        assertFalse(mengde.inneholder(1));
        assertEquals(1, mengde.antallElementer());

        assertNull(mengde.fjern(3)); // Element not in the set
    }

    @Test
    void testTilTabell() {
        mengde.leggTil(1);
        mengde.leggTil(2);

        Integer[] expected = {1, 2};
        assertArrayEquals(expected, mengde.tilTabell());
    }

    @Test
    void testAntallElementer() {
        assertEquals(0, mengde.antallElementer());
        mengde.leggTil(1);
        assertEquals(1, mengde.antallElementer());
        mengde.leggTil(2);
        assertEquals(2, mengde.antallElementer());
    }

    @Test
    void testLeggTil() {
        mengde.leggTil(1);
        assertTrue(mengde.inneholder(1));
        assertEquals(1, mengde.antallElementer());

        // Test adding duplicate element
        mengde.leggTil(1);
        assertEquals(1, mengde.antallElementer());
    }

    @Test
    void testLeggTilAlleFra() {
        JavaSetToMengde<Integer> annenMengde = new JavaSetToMengde<>();
        annenMengde.leggTil(3);
        annenMengde.leggTil(4);

        mengde.leggTil(1);
        mengde.leggTil(2);
        mengde.leggTilAlleFra(annenMengde);

        assertTrue(mengde.inneholder(3));
        assertTrue(mengde.inneholder(4));
        assertEquals(4, mengde.antallElementer());
    }

    @Test
    void testUnion() {
        JavaSetToMengde<Integer> annenMengde = new JavaSetToMengde<>();
        annenMengde.leggTil(2);
        annenMengde.leggTil(3);

        mengde.leggTil(1);
        mengde.leggTil(2);

        MengdeADT<Integer> result = mengde.union(annenMengde);

        assertTrue(result.inneholder(1));
        assertTrue(result.inneholder(2));
        assertTrue(result.inneholder(3));
        assertEquals(3, result.antallElementer());
    }

    @Test
    void testSnitt() {
        JavaSetToMengde<Integer> annenMengde = new JavaSetToMengde<>();
        annenMengde.leggTil(2);
        annenMengde.leggTil(3);

        mengde.leggTil(1);
        mengde.leggTil(2);

        MengdeADT<Integer> result = mengde.snitt(annenMengde);

        assertTrue(result.inneholder(2));
        assertFalse(result.inneholder(1));
        assertFalse(result.inneholder(3));
        assertEquals(1, result.antallElementer());
    }

    @Test
    void testErDelmengdeAv() {
        JavaSetToMengde<Integer> annenMengde = new JavaSetToMengde<>();
        annenMengde.leggTil(1);
        annenMengde.leggTil(2);
        annenMengde.leggTil(3);

        mengde.leggTil(1);
        mengde.leggTil(2);

        assertTrue(mengde.erDelmengdeAv(annenMengde));

        mengde.leggTil(4);
        assertFalse(mengde.erDelmengdeAv(annenMengde));
    }

    @Test
    void testErLik() {
        JavaSetToMengde<Integer> annenMengde = new JavaSetToMengde<>();
        annenMengde.leggTil(1);
        annenMengde.leggTil(2);

        mengde.leggTil(1);
        mengde.leggTil(2);

        assertTrue(mengde.erLik(annenMengde));

        mengde.leggTil(3);
        assertFalse(mengde.erLik(annenMengde));
    }

    @Test
    void testErDisjunkt() {
        JavaSetToMengde<Integer> annenMengde = new JavaSetToMengde<>();
        annenMengde.leggTil(3);
        annenMengde.leggTil(4);

        mengde.leggTil(1);
        mengde.leggTil(2);

        assertTrue(mengde.erDisjunkt(annenMengde));

        mengde.leggTil(3);
        assertFalse(mengde.erDisjunkt(annenMengde));
    }

    @Test
    void testMinus() {
        JavaSetToMengde<Integer> annenMengde = new JavaSetToMengde<>();
        annenMengde.leggTil(2);
        annenMengde.leggTil(3);

        mengde.leggTil(1);
        mengde.leggTil(2);

        MengdeADT<Integer> result = mengde.minus(annenMengde);

        assertTrue(result.inneholder(1));
        assertFalse(result.inneholder(2));
        assertEquals(1, result.antallElementer());
    }
}

