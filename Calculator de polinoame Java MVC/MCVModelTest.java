import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MCVModelTest {

    MCVModel model;
    String polinom1;
    String polinom2;
    private static int nrTesteExecutate = 0;
    private static int nrTesteCuSucces = 0;

    @BeforeEach
    void setUp() {

        model = new MCVModel();
        polinom1 = "-x^2 + x + 1";
        polinom2 = "x - 13";
        model.setM_valueOfPolynom1(new Polinom());
        model.reset();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void sumOfPolynoms() {
        model.sumOfPolynoms("-x^2 + x + 1", "x - 13");
        assertEquals(model.toString(model.getM_valueOfResult()), " -x^2 +2.0x -12.0");

    }

    @Test
    void downOfPolynoms() {
        model.downOfPolynoms("-x^2 + x + 1", "x - 13");
        assertEquals(model.toString(model.getM_valueOfResult()), " -x^2 +14.0");

    }

    @Test
    void multiplicationOfPolynoms() {
        model.multiplicationOfPolynoms("-x^2 + x + 1", "x - 13");
        assertEquals(model.toString(model.getM_valueOfResult()), " -x^3 +14.0x^2 -12.0x -13.0");

    }

    @Test
    void divisionOfPolynoms() {
        model.divisionOfPolynoms("-x^2 + x + 1", "x - 13");
        assertEquals(model.toString(model.getM_valueOfResult()), " -x -12.0");
        assertEquals(model.toString(model.getM_restImpartire()), " -155.0");

    }

    @Test
    void derivationOfPolynoms() {
        model.derivationOfPolynoms("-x^2 + x + 1", "x - 13", 0);
        assertEquals(model.toString(model.getM_valueOfResult()), " -2.0x +1");
        model.reset();
        model.derivationOfPolynoms("-x^2 + x + 1", "x - 13", 1);
        assertEquals(model.toString(model.getM_valueOfResult()), "1");
    }

    @Test
    void integrationOfPolynoms() {
        model.integrationOfPolynoms("-x^2 + x + 1", "x - 13", 0);
        assertEquals(model.toString(model.getM_valueOfResult()), " -0.34x^3 +0.5x^2 +x");
        model.reset();
        model.integrationOfPolynoms("-x^2 + x + 1", "x - 13", 1);
        assertEquals(model.toString(model.getM_valueOfResult()), "0.5x^2 -13.0x");
    }

}