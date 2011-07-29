package test.primefactors.template;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static primefactors.template.PrimeFactors.of;

public class PrimeFactorsTest {
    @Test
    public void primeFactors() throws Exception {
        assertEquals(asList(), of(1));
        assertEquals(asList(2), of(2));
        assertEquals(asList(3), of(3));
        assertEquals(asList(2, 2), of(4));
        assertEquals(asList(5), of(5));
        assertEquals(asList(2, 3), of(6));
        assertEquals(asList(7), of(7));
        assertEquals(asList(2, 2, 2), of(8));
        assertEquals(asList(3, 3), of(9));
        assertEquals(asList(2, 2, 3, 3, 5, 7, 11, 13), of(2 * 2 * 3 * 3 * 5 * 7 * 11 * 13));
    }

}
