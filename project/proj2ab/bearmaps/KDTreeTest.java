package bearmaps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.Random;
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

    @Test
    public void testNearestKDTree(){
        Point p1 = new Point(2,3);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4,5);
        Point p4 = new Point(3,3);
        Point p5 = new Point(1,5);
        Point p6 = new Point(4,4);
        Point p7 = new Point(5,3);
        KDTree nn = new KDTree(List.of(p1,p2,p3,p4,p5,p6,p7));

        //initial KDTree and NaiveKDTree
        NaivePointSet nn2;
        nn.clear();
        Random rand = new Random();
        List<Point> nn2Input = new ArrayList<>();
        for(int i =0;i<1000;i++){
            Point temp = new Point(rand.nextDouble()*1000, rand.nextDouble()*1000);
            nn2Input.add(temp);
            nn.add(temp);
        }
        //Check Correctness
        nn2 = new NaivePointSet(nn2Input);
        for(int i =0;i<1000;i++){
            Double x = rand.nextDouble()*1000;
            Double y = rand.nextDouble()*1000;
            Point pKDTree = nn.nearest(x,y);
            Point pNaive = nn2.nearest(x,y);
            assertEquals("x= "+x+"; y= "+y,pNaive,pKDTree);
        }
        //Check time difference
        Stopwatch sw = new Stopwatch();
        for(int i=0;i<100000;i++){
            Double x = rand.nextDouble()*1000;
            Double y = rand.nextDouble()*1000;
            Point pKDTree = nn.nearest(x,y);
        }
        System.out.println("KDTree nearest method Total time elapsed: " + sw.elapsedTime() +  " seconds.");

        Stopwatch sw2 = new Stopwatch();
        for(int i=0;i<100000;i++){
            Double x = rand.nextDouble()*1000;
            Double y = rand.nextDouble()*1000;
            Point pNaive = nn2.nearest(x,y);
        }
        System.out.println("NaivePointSet nearest method Total time elapsed: " + sw2.elapsedTime() +  " seconds.");
    }

}
