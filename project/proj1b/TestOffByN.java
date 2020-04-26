import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestOffByN {
    static CharacterComparator offByN = new OffByN(3);

    // Your tests go here.
    @Test
    public void testEqualChars(){
        boolean actual1 = offByN.equalChars('a','d');
        assertEquals(true, actual1);

        boolean actual2 = offByN.equalChars('&','%');
        assertEquals(false, actual2);

        boolean actual3 = offByN.equalChars('D','A');
        assertEquals(true, actual3);

        boolean actual4 = offByN.equalChars('x','m');
        assertEquals(false, actual4);

        boolean actual5 = offByN.equalChars('x','x');
        assertEquals(false, actual5);

    }
}
