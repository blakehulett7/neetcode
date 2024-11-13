import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

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
}
