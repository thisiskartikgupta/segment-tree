import SegmentTreeExceptions.SegmentTreeOutOfBoundsException;
import SegmentTreeExceptions.SizeLimitException;
public class Driver {

    public static void main(String[] args) throws SizeLimitException, SegmentTreeOutOfBoundsException {

        SegmentTree segmentTree = new SegmentTree(new int[] {11, 22, 33, 44, 55});
        segmentTree.display();
        System.out.println(segmentTree.getRangeSum(0,1));
//        segmentTree.displayRAW();
//        System.out.println(segmentTree.getRangeSum(1,3));
//        segmentTree.update(0, 100);
//        segmentTree.displayRAW();
//        segmentTree.display();
//        System.out.println(segmentTree.getRangeSum(1,3));
//        segmentTree.update(0, 200);
//        segmentTree.display();

    }
}
