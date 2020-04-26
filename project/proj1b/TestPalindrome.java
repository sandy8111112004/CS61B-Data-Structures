import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static OffByOne offByOne = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }
    @Test
    public void testIsPalindrome(){
        boolean actual1 = palindrome.isPalindrome("cat");
        assertEquals(false, actual1);

        boolean actual2 = palindrome.isPalindrome("racecar");
        assertEquals(true, actual2);

        boolean actual3 = palindrome.isPalindrome("a");
        assertEquals(true, actual3);

        boolean actual4 = palindrome.isPalindrome("");
        assertEquals(true, actual4);

    }

    @Test
    public void testIsPalindrome2(){
        boolean actual1 = palindrome.isPalindrome("cat", offByOne);
        assertEquals(false, actual1);

        boolean actual2 = palindrome.isPalindrome("racecar", offByOne);
        assertEquals(false, actual2);

        boolean actual3 = palindrome.isPalindrome("a", offByOne);
        assertEquals(true, actual3);

        boolean actual4 = palindrome.isPalindrome("", offByOne);
        assertEquals(true, actual4);

        boolean actual5 = palindrome.isPalindrome("acgdb", offByOne);
        assertEquals(true, actual5);

        boolean actual6 = palindrome.isPalindrome("befa", offByOne);
        assertEquals(true, actual6);

    }
}
//Uncomment this class once you've created your Palindrome class.