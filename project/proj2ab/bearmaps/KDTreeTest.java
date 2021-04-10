package bearmaps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class KDTreeTest {
    @Test
    public void testAddKDTree(){
        Point p1 = new Point(2,3);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4,5);
        Point p4 = new Point(3,3);
        Point p5 = new Point(1,5);
        Point p6 = new Point(4,4);
        Point p7 = new Point(5,3);
        Point p8 = new Point(5,3);
        KDTree nn = new KDTree(List.of(p1,p2,p3,p4,p5,p6,p7,p8));
        nn.printKDTree();
    }

    @Test
    public void testSizeKDTree(){
        Point p1 = new Point(2,3);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4,5);
        Point p4 = new Point(3,3);
        Point p5 = new Point(1,5);
        Point p6 = new Point(4,4);
        Point p7 = new Point(5,3);
        KDTree nn = new KDTree(List.of(p1,p2,p3,p4,p5,p6,p7));
        assertEquals( 7,nn.size());
    }

    @Test
    public void testContainsKDTree(){
        Point p1 = new Point(2,3);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4,5);
        Point p4 = new Point(3,3);
        Point p5 = new Point(1,5);
        Point p6 = new Point(4,4);
        Point p7 = new Point(5,3);
        KDTree kdTree = new KDTree(List.of(p1,p2,p3,p4,p5,p6,p7));
        assertEquals(false,kdTree.contains(new Point(0,4)));
        assertEquals(true,kdTree.contains(p1));

        Throwable exception1 = assertThrows(
                IllegalArgumentException.class,()->{
                    kdTree.contains(null);
                }
        );
        assertEquals("Please don't enter null point", exception1.getMessage());

        kdTree.clear();
        Throwable exception2 = assertThrows(
                IllegalArgumentException.class,()->{
                    kdTree.contains(p1);
                }
        );
        assertEquals("KDTree is empty", exception2.getMessage());

    }

}
