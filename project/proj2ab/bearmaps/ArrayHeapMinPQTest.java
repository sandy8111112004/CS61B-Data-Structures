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
        minPQ.add("My name", 1);
        minPQ.add("is", 2);
        minPQ.add("Ming", 3);

        assertEquals("My name",minPQ.getNodeValue(0));
        assertEquals("is",minPQ.getNodeValue(1));
        assertEquals("Ming",minPQ.getNodeValue(2));

        Throwable exception = assertThrows(
                IllegalArgumentException.class,()->{
                    minPQ.add("My name",3);
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

//    @Test
//    public void testMinPQRemoveSmallest(){
//        minPQ.clear();
//
//        minPQ.add("My name", 1);
//        minPQ.add("is", 2);
//        minPQ.add("Ming", 3);
//
//        assertEquals("My Name", minPQ.removeSmallest());
//        assertEquals("is", minPQ.removeSmallest());
//        assertEquals("Ming", minPQ.removeSmallest());
//
//        //if no items exist, throw a NoSuchElementException
//        Throwable exception = assertThrows(
//                NoSuchElementException.class,()->{
//                    minPQ.removeSmallest();
//                }
//        );
//
//        assertEquals("MinPQ is empty",exception.getMessage());
//    }

//    @Test
//    public void testMinPQSize(){
//        minPQ.clear();
//        assertEquals(0,minPQ.size());
//        minPQ.add("My name", 1);
//        assertEquals(1,minPQ.size());
//        minPQ.add("is", 2);
//        minPQ.add("Ming", 3);
//        assertEquals(3,minPQ.size());
//
//    }

//    @Test
//    public void testMinPQChangePriority(){
//        minPQ.clear();
//        //if the item doesn't exist, throw a NoSuchElementException
//        Throwable exception = assertThrows(
//                NoSuchElementException.class,()->{
//                    minPQ.changePriority("Ming",4);
//                }
//        );
//
//        assertEquals("MinPQ is empty",exception.getMessage());
//
//
//        minPQ.add("My name", 1);
//        minPQ.add("is", 2);
//        minPQ.add("Ming", 3);
//
//        assertEquals("My name", minPQ.getSmallest());
//        minPQ.changePriority("My name", 2.5);
//        assertEquals("is", minPQ.removeSmallest());
//        assertEquals("My name", minPQ.getSmallest());
//
//    }
}
