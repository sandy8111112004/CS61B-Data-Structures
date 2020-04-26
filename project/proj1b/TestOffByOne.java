import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars(){
        boolean actual1 = offByOne.equalChars('a','b');
        assertEquals(true, actual1);

        boolean actual2 = offByOne.equalChars('&','%');
        assertEquals(true, actual2);

        boolean actual3 = offByOne.equalChars('B','A');
        assertEquals(true, actual3);

        boolean actual4 = offByOne.equalChars('x','m');
        assertEquals(false, actual4);

        boolean actual5 = offByOne.equalChars('x','x');
        assertEquals(false, actual5);

    }
} //Uncomment this class once you've created your CharacterComparator interface and OffByOne class.