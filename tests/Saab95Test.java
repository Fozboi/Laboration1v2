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

    @Test
    public void turboIncreasingAcceleration(){
        double preSpeed = 20;
        double gasFactor = 0.5;

        testSaab.currentSpeed = preSpeed;
        testSaab.setTurboOff();
        testSaab.gas(gasFactor);
        double offSpeed = testSaab.getCurrentSpeed();

        testSaab.currentSpeed = preSpeed;
        testSaab.setTurboOn();
        testSaab.gas(gasFactor);
        double onSpeed = testSaab.getCurrentSpeed();


        assertTrue(offSpeed < onSpeed);
    }







}