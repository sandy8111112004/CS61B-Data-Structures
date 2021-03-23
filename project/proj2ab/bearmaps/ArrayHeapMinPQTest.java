package bearmaps;


import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.function.Executable;
import static org.junit.Assert.assertEquals;
import java.util.NoSuchElementException;


public class ArrayHeapMinPQTest {
    static ArrayHeapMinPQ minPQ = new ArrayHeapMinPQ();

    @Test
    public void testMinPQAdd(){
        minPQ.clear();
        minPQ.add("A", 1);
        minPQ.add("B", 5);
        minPQ.add("C", 1);
        assertEquals("A",minPQ.getNodeValue(0));
        assertEquals("B",minPQ.getNodeValue(1));
        assertEquals("C",minPQ.getNodeValue(2));
        minPQ.add("D", 6);
        minPQ.add("E", 5);
        minPQ.add("F", 6);
        minPQ.add("G", 3);
        minPQ.add("H", 7);
        minPQ.add("I", 7);
        minPQ.add("J", 8);
        assertEquals("[(A,1.0), (B,5.0), (C,1.0), (D,6.0), (E,5.0), (F,6.0), (G,3.0), (H,7.0), (I,7.0), (J,8.0)]",minPQ.printableMinPQ());
        minPQ.add("K", 3);
        assertEquals("K",minPQ.getNodeValue(1));
        assertEquals("B",minPQ.getNodeValue(4));
        assertEquals("E",minPQ.getNodeValue(10));
        assertEquals("[(A,1.0), (K,3.0), (C,1.0), (D,6.0), (B,5.0), (F,6.0), (G,3.0), (H,7.0), (I,7.0), (J,8.0), (E,5.0)]",minPQ.printableMinPQ());


        Throwable exception = assertThrows(
                IllegalArgumentException.class,()->{
                    minPQ.add("A",3);
                }
        );

        assertEquals("The ArrayHeapMinPQ contains this item already",exception.getMessage());

    }

    @Test
    public void testMinPQContains(){
        minPQ.clear();
        minPQ.add("My name", 1);
        minPQ.add("is", 2);
        minPQ.add("Ming", 3);

        assertEquals(true,minPQ.contains("My name"));
        assertEquals(true,minPQ.contains("is"));
        assertEquals(true,minPQ.contains("Ming"));
        assertEquals(false,minPQ.contains("Min"));
    }

    @Test
    public void testMinPQGetSmallest(){
        minPQ.clear();
        //if no items exist, throw a NoSuchElementException
        Throwable exception = assertThrows(
                NoSuchElementException.class,()->{
                    minPQ.getSmallest();
                }
        );

        assertEquals("ArrayHeapMinPQ is empty",exception.getMessage());


        minPQ.add("My name", 1);
        minPQ.add("is", 2);
        minPQ.add("Ming", 3);

        assertEquals("My name", minPQ.getSmallest());
    }

    @Test
    public void testMinPQRemoveSmallest(){
        minPQ.clear();

        //if no items exist, throw a NoSuchElementException
        Throwable exception = assertThrows(
                NoSuchElementException.class,()->{
                    minPQ.removeSmallest();
                }
        );
        assertEquals("ArrayHeapMinPQ is empty",exception.getMessage());

        minPQ.add("A", 1);
        minPQ.add("B", 5);
        minPQ.add("C", 1);
        minPQ.add("D", 6);
        minPQ.add("E", 5);
        minPQ.add("F", 6);
        minPQ.add("G", 3);
        minPQ.add("H", 7);
        minPQ.add("I", 7);
        minPQ.add("J", 8);
        minPQ.add("K", 3);
        assertEquals("[(A,1.0), (K,3.0), (C,1.0), (D,6.0), (B,5.0), (F,6.0), (G,3.0), (H,7.0), (I,7.0), (J,8.0), (E,5.0)]",minPQ.printableMinPQ());
        minPQ.removeSmallest();
        assertEquals("[(C,1.0), (K,3.0), (G,3.0), (D,6.0), (B,5.0), (F,6.0), (E,5.0), (H,7.0), (I,7.0), (J,8.0)]",minPQ.printableMinPQ());

    }

    @Test
    public void testMinPQSize(){
        minPQ.clear();
        assertEquals(0,minPQ.size());
        minPQ.add("My name", 1);
        assertEquals(1,minPQ.size());
        minPQ.add("is", 2);
        minPQ.add("Ming", 3);
        assertEquals(3,minPQ.size());

    }

    @Test
    public void testMinPQChangePriority(){
        minPQ.clear();
        //if the item doesn't exist, throw a NoSuchElementException
        Throwable exception = assertThrows(
                NoSuchElementException.class,()->{
                    minPQ.changePriority("A",4);
                }
        );

        assertEquals("The item is not in the ArrayHeapMinPQ",exception.getMessage());


        minPQ.add("A", 1);
        minPQ.add("B", 2);
        minPQ.add("C", 3);

        assertEquals("A", minPQ.getSmallest());
        minPQ.changePriority("A", 2.5);
        assertEquals("B", minPQ.getSmallest());


    }
}
