
import SegmentTreeExceptions.SegmentTreeOutOfBoundsException;
import SegmentTreeExceptions.SizeLimitException;
import java.util.Arrays;

public class SegmentTree {
    static int size;
    static final int SIZE_LIMIT = 100000;
    static int[] containerArray;
    static int[] baseArray;

    SegmentTree(int[] nums) throws SizeLimitException, SegmentTreeOutOfBoundsException{
        // Checking if the size is within bounds
        if(size > SIZE_LIMIT)
            throw  new SizeLimitException();

        this.size = nums.length;
        this.baseArray = nums;
        containerArray = new int[4 * size];
        Arrays.fill(containerArray, Integer.MIN_VALUE);

        generateSegmentTree(containerArray, nums, 0, 0, size-1);
    }

    private void generateSegmentTree(int[] containerArray, int[] nums, int pos, int leftBound, int rightBound) {
        // Base condition
        if(leftBound == rightBound)
            containerArray[pos] = nums[leftBound];

        else {
            int midBound = leftBound + (rightBound - leftBound) / 2;

            generateSegmentTree(containerArray, nums, 2 * pos + 1, leftBound, midBound);
            generateSegmentTree(containerArray, nums, 2 * pos + 2, midBound+1,rightBound);

            containerArray[pos] = containerArray[2 * pos + 1] + containerArray[2 * pos + 2];
        }
    }

    public void displayRAW() {
        System.out.println(Arrays.toString(containerArray));
    }

    public void display() {
        int first = 0;
        while(containerArray[first] != Integer.MIN_VALUE)
            first++;

        System.out.println(Arrays.toString(Arrays.copyOfRange(containerArray, 0, first)));
    }

    public void update(int index, int newValue) throws SegmentTreeOutOfBoundsException {
        if(index < 0 || index >= size)
            throw new SegmentTreeOutOfBoundsException("Exception : Update Index is out of bounds");

        int differenceInValue = newValue - baseArray[index];
        baseArray[index] = newValue;

        // Since any update will begin from root, we pass 0 in pos
        updateHelper(0, size-1, index, differenceInValue, 0);
    }

    private void updateHelper(int leftBound, int rightBound, int index, int differenceInValue, int pos) {
        if(index < leftBound || index > rightBound) return;

        containerArray[pos] += differenceInValue;
        if(leftBound != rightBound) {
            int mid = leftBound + (rightBound - leftBound) / 2;

            updateHelper(leftBound, mid, index, differenceInValue, 2 * pos + 1);
            updateHelper(mid + 1, rightBound, index, differenceInValue, 2 * pos + 2);
        }
    }

    public int getRangeSum(int leftBound, int rightBound) throws SegmentTreeOutOfBoundsException {
        if(leftBound < 0 || rightBound >= size || leftBound > rightBound)
            throw new SegmentTreeOutOfBoundsException("Exception : The Range is out of bounds.");

        return getRangeSumHelper(0, size-1, leftBound, rightBound, 0);
    }

    private int getRangeSumHelper(int leftBound, int rightBound, int queryLeftBound, int queryRightBound, int pos) {
        if(leftBound >= queryLeftBound || rightBound <= queryRightBound) return containerArray[pos];

        // Case of overlap
        if(rightBound < queryLeftBound || leftBound > queryRightBound)  return 0;

        int mid = leftBound + (rightBound - leftBound) / 2;
//        int leftTreeOutput = ;
//        int rightTreeOutput =;

        return getRangeSumHelper(leftBound, mid, queryLeftBound, queryRightBound, 2 * pos + 1) +
                getRangeSumHelper(mid + 1, rightBound, queryLeftBound, queryRightBound, 2 * pos + 2);
    }
}
