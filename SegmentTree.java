
import SegmentTreeExceptions.SizeLimitException;

import java.util.Arrays;

public class SegmentTree {

    static int size;
    static final int SIZE_LIMIT = 100000;
    int[] containerArray;

    SegmentTree(int[] nums) throws SizeLimitException{

        // Checking if the size is within bounds
        if(size > SIZE_LIMIT)
            throw  new SizeLimitException();

        this.size = nums.length;
        containerArray = new int[4 * size];
        System.out.println(size);
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

    public void display() {
        System.out.println(Arrays.toString(containerArray));
    }
}
