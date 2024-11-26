public class BinarySearch {
    public int search(int[] nums, int target) {
        int left_idx = 0;
        int right_idx = nums.length - 1;
        while (left_idx <= right_idx) {
            int mid_idx = (left_idx + right_idx) / 2;
            int num = nums[mid_idx];
            if (num < target) {
                left_idx = mid_idx + 1;
                continue;
            }
            if (num > target) {
                right_idx = mid_idx - 1;
                continue;
            }
            return mid_idx;
        }
        return -1;
    }
}
