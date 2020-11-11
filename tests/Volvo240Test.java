import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Volvo240Test {
    Volvo240 testVolvo;

    @BeforeEach
    public void init(){
        testVolvo = new Volvo240();
    }

    @Test
    public void breakDecreasingSpeed(){
        testVolvo.currentSpeed = 20;
        double preSpeed = testVolvo.currentSpeed;
        testVolvo.brake(0.5);
        double postSpeed = testVolvo.currentSpeed;

        assertTrue(postSpeed < preSpeed);
    }









}
