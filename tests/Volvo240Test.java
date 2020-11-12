import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class Volvo240Test {
    Volvo240 testVolvo;

    @BeforeEach
    public void init(){
        testVolvo = new Volvo240();
    }

    @Test
    public void breakDecreasingSpeed(){
        testVolvo.setCurrentSpeed(20);
        double preSpeed = testVolvo.getCurrentSpeed();
        testVolvo.brake(0.5);
        double postSpeed = testVolvo.getCurrentSpeed();

        assertTrue(postSpeed < preSpeed);
    }









}
