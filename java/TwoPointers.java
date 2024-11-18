import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoPointers {
    public boolean is_palindrome(String s) {
        int left_idx = 0;
        int right_idx = s.length() - 1;

        while (left_idx < right_idx) {
            char left_char = Character.toLowerCase(s.charAt(left_idx));
            if (!is_alphanumeric(left_char)) {
                left_idx++;
                continue;
            }

            char right_char = Character.toLowerCase(s.charAt(right_idx));
            if (!is_alphanumeric(right_char)) {
                right_idx--;
                continue;
            }

            if (left_char != right_char) {
                return false;
            }

            left_idx++;
            right_idx--;
        }

        return true;
    }

    public boolean is_alphanumeric(char c) {
        return (c >= 'A' && c <= 'Z' ||
                c >= 'a' && c <= 'z' ||
                c >= '0' && c <= '9');
    }

    public int[] two_sum(int[] numbers, int target) {
        int left_idx = 0;
        int right_idx = numbers.length - 1;

        while (left_idx < right_idx) {
            int left_num = numbers[left_idx];
            int right_num = numbers[right_idx];
            int sum = left_num + right_num;

            if (sum < target) {
                left_idx++;
                continue;
            }
            if (sum > target) {
                right_idx--;
                continue;
            }

            return new int[] { left_idx + 1, right_idx + 1 };
        }

        return new int[] {};
    }

    public List<List<Integer>> three_sum(int[] nums) {
        var result = new ArrayList<List<Integer>>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num > 0) {
                break;
            }
            if (i != 0) {
                if (num == nums[i - 1]) {
                    continue;
                }
            }

            int left_idx = i + 1;
            int right_idx = nums.length - 1;

            while (left_idx < right_idx) {
                int left_num = nums[left_idx];
                int right_num = nums[right_idx];
                int sum = num + left_num + right_num;

                if (sum < 0) {
                    while (left_num == nums[left_idx]) {
                        left_idx++;
                    }
                    continue;
                }

                if (sum > 0) {
                    while (right_num == nums[right_idx]) {
                        right_idx--;
                    }
                    continue;
                }

                result.add(Arrays.asList(num, left_num, right_num));

                while (left_idx < right_idx && left_num == nums[left_idx]) {
                    left_idx++;
                }
            }
        }
        return result;
    }

    public int max_area(int[] heights) {
        int max_area = 0;
        int left_idx = 0;
        int right_idx = heights.length - 1;

        while (left_idx < right_idx) {
            int left_height = heights[left_idx];
            int right_height = heights[right_idx];
            int height = Math.min(left_height, right_height);
            int width = right_idx - left_idx;
            int area = height * width;

            if (max_area < area) {
                max_area = area;
            }

            if (left_height < right_height) {
                left_idx++;
                continue;
            }
            right_idx--;
        }

        return max_area;
    }

    public int trapped(int[] height) {
        int trapped_water = 0;
        int left_idx = 0;
        int left_max = 0;
        int right_idx = height.length - 1;
        int right_max = 0;

        while (left_idx <= right_idx) {
            if (left_max < right_max) {
                int current_height = height[left_idx];
                int water_collected = left_max - current_height;
                if (water_collected > 0) {
                    trapped_water += water_collected;
                }
                if (left_max < current_height) {
                    left_max = current_height;
                }
                left_idx++;
                continue;
            }

            int current_height = height[right_idx];
            int water_collected = right_max - current_height;
            if (water_collected > 0) {
                trapped_water += water_collected;
            }
            if (right_max < current_height) {
                right_max = current_height;
            }
            right_idx--;
        }

        return trapped_water;
    }
}
