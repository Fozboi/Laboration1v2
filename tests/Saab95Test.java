import Cars.Saab95;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class Saab95Test {
    Saab95 testSaab;

    @BeforeEach
    public void init(){
        testSaab = new Saab95();
    }

    @Test
    public void breakDecreasingSpeed(){
        testSaab.setCurrentSpeed(20);
        double preSpeed = testSaab.getCurrentSpeed();
        testSaab.brake(0.5);
        double postSpeed = testSaab.getCurrentSpeed();

        assertTrue(postSpeed < preSpeed);
    }

    @Test
    public void turboIncreasingAcceleration(){
        double preSpeed = 20;
        double gasFactor = 0.5;

        testSaab.setCurrentSpeed(preSpeed);
        testSaab.setTurboOff();
        testSaab.gas(gasFactor);
        double offSpeed = testSaab.getCurrentSpeed();

        testSaab.setCurrentSpeed(preSpeed);
        testSaab.setTurboOn();
        testSaab.gas(gasFactor);
        double onSpeed = testSaab.getCurrentSpeed();


        assertTrue(offSpeed < onSpeed);
    }







}