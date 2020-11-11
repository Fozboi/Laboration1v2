import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Saab95Test {
    Saab95 testSaab;

    @BeforeEach
    public void init(){
        testSaab = new Saab95();
    }

    @Test
    public void breakDecreasingSpeed(){
        testSaab.currentSpeed = 20;
        double preSpeed = testSaab.currentSpeed;
        testSaab.brake(0.5);
        double postSpeed = testSaab.currentSpeed;

        assertTrue(postSpeed < preSpeed);

    }





}