import java.util.Arrays;
import java.util.HashSet;

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
}
