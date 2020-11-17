import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScaniaTest {

    Scania testScania;

    @BeforeEach
    public void init(){
        testScania = new Scania();
    }


    @Test
    void gasWhileTrailerRaised() {
        testScania.setTrailerAngle(10);
        try{
            testScania.gas(1);
            fail("should have thrown exception");
        } catch (IllegalStateException e){ }

    }

    @Test
    void setTrailerAngleWhileMoving() {
        testScania.gas(1);
        try{
            testScania.setTrailerAngle(10);
            fail("should have thrown exception");
        } catch (IllegalStateException e){ }

    }
    @Test
    void setIllegalTrailerAngle() {
        try{
            testScania.setTrailerAngle(80);
            fail("should have thrown exception");
        } catch (IllegalArgumentException e){ }

    }

}