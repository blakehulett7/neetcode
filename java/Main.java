import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println("Christ is King!");
    }
}

class Array_Problems {
    public boolean has_duplicates(int[] nums) {
        var seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(num)) {
                return true;
            }
            seen.add(num);
        }
        return false;
    }

    public boolean has_duplicates_length(int[] nums) {
        return Arrays.stream(nums).distinct().count() < nums.length;
    }

    public boolean has_duplicates_sorted(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }
        return false;
    }

    public boolean is_anagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        var s_hash = new HashMap<Character, Integer>();
        var t_hash = new HashMap<Character, Integer>();

        for (int i = 0; i < s.length(); i++) {
            s_hash.put(s.charAt(i), s_hash.getOrDefault(s.charAt(i), 0) + 1);
            t_hash.put(t.charAt(i), t_hash.getOrDefault(t.charAt(i), 0) + 1);
        }

        return s_hash.equals(t_hash);
    }

    public int[] two_sum(int[] nums, int target) {
        var seen = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int number = nums[i];
            int difference = target - number;
            if (seen.containsKey(difference)) {
                return new int[] { seen.get(difference), i };
            }
            seen.put(number, i);
        }

        return new int[] {};
    }

    public List<List<String>> group_anagrams(String[] strings) {
        var gram_hash = new HashMap<String, List<String>>();
        for (String str : strings) {
            var gram = new int[26];
            for (char c : str.toCharArray()) {
                gram[c - 'a']++;
            }
            String key = Arrays.toString(gram);
            gram_hash.putIfAbsent(key, new ArrayList<>());
            gram_hash.get(key).add(str);
        }
        return new ArrayList<>(gram_hash.values());
    }

    public int[] top_k_frequent(int[] nums, int k) {
        var counts = new HashMap<Integer, Integer>();
        List<Integer>[] buckets = new List[nums.length + 1];

        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        for (int key : counts.keySet()) {
            buckets[counts.get(key)].add(key);
        }

        var result = new int[k];
        int idx = 0;
        for (int i = nums.length - 1; idx < k; i--) {
            var bucket = buckets[i];
            if (bucket.size() == 0) {
                continue;
            }
            for (int num : bucket) {
                result[idx] = num;
                idx++;
                if (idx == k) {
                    return result;
                }
            }
        }
        return result;
    }

    public String encode(List<String> strs) {
        if (strs.size() == 0) {
            return "";
        }

        var encoded = new StringBuilder();
        for (String str : strs) {
            encoded.append(str.length()).append('#').append(str);
        }

        return encoded.toString();
    }

    public List<String> decode(String str) {
        if (str.isEmpty()) {
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>();
        int idx = 0;

        while (idx < str.length()) {
            var size_string = new StringBuilder();
            while (str.charAt(idx) != '#') {
                size_string.append(str.charAt(idx));
                idx++;
            }
            idx++;

            int size = Integer.parseInt(size_string.toString());

            result.add(str.substring(idx, idx + size));
            idx += size;
        }

        return result;
    }

    public int[] product_except_self(int[] nums) {
        var result = new int[nums.length];

        int prefix = 1;
        for (int i = 0; i < result.length; i++) {
            result[i] = prefix;
            prefix *= nums[i];
        }

        int postfix = 1;
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] *= postfix;
            postfix *= nums[i];
        }

        return result;
    }

    public boolean is_valid_sudoku(char[][] board) {
        var row_hash = new HashSet<Character>();
        var column_hash = new HashMap<Integer, Set<Character>>();
        var grid_hash = new HashMap<String, Set<Character>>();

        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < 9; column++) {
                char num = board[row][column];
                if (num == '.') {
                    continue;
                }

                if (row_hash.contains(num)) {
                    return false;
                }
                row_hash.add(num);

                column_hash.putIfAbsent(column, new HashSet<>());
                if (column_hash.get(column).contains(num)) {
                    return false;
                }
                column_hash.get(column).add(num);

                String grid_key = (row / 3) + "," + (column / 3);
                grid_hash.putIfAbsent(grid_key, new HashSet<>());
                if (grid_hash.get(grid_key).contains(num)) {
                    return false;
                }
                grid_hash.get(grid_key).add(num);
            }
            row_hash = new HashSet<>();
        }
        return true;
    }

    public int longest_consecutive(int[] nums) {
        int longest_sequence = 0;
        var num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        for (int num : nums) {
            int sequence = 1;
            if (num_set.contains(num - 1)) {
                continue;
            }

            while (num_set.contains(num + sequence)) {
                sequence++;
            }

            if (longest_sequence < sequence) {
                longest_sequence = sequence;
            }
        }

        return longest_sequence;
    }
}
