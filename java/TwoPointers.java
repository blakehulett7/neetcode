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
}
