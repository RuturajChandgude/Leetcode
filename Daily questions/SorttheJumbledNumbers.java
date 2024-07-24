
//2191

//Approach-1 (Converting to string)
//T.C : O(n*d + nlogn) , Here, n is the size of the nums vector, and d is the average number of digits in the numbers.
//S.C : O(n+d)

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SorttheJumbledNumbers {
    public int[] sortJumbled(int[] mapping, int[] nums) {
        int n = nums.length;
        List<Pair> vec = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String numStr = Integer.toString(nums[i]);
            String mappedStr = getMappedNum(numStr, mapping);
            int mappedNum = Integer.parseInt(mappedStr);
            vec.add(new Pair(mappedNum, i));
        }

        Collections.sort(vec, (a, b) -> Integer.compare(a.mappedNum, b.mappedNum));
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            int originalNumIdx = vec.get(i).originalIdx;
            result[i] = nums[originalNumIdx];
        }

        return result;
    }

    private String getMappedNum(String num, int[] mapping) {
        StringBuilder mappedNum = new StringBuilder();
        for (char ch : num.toCharArray()) {
            mappedNum.append(mapping[ch - '0']);
        }
        return mappedNum.toString();
    }

    private static class Pair {
        int mappedNum;
        int originalIdx;

        Pair(int mappedNum, int originalIdx) {
            this.mappedNum = mappedNum;
            this.originalIdx = originalIdx;
        }
    }
}



//Approach-2 (Without Converting to string)
//T.C : O(n*d + nlogn) , Here, n is the size of the nums vector, and d is the average number of digits in the numbers.
//S.C : O(n)
class Solution {
    public int[] sortJumbled(int[] mapping, int[] nums) {
        int n = nums.length;
        List<Pair> vec = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int mappedNum = getMappedNum(nums[i], mapping);
            vec.add(new Pair(mappedNum, i));
        }

        Collections.sort(vec, (a, b) -> Integer.compare(a.mappedNum, b.mappedNum));
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            int originalNumIdx = vec.get(i).originalIdx;
            result[i] = nums[originalNumIdx];
        }

        return result;
    }

    private int getMappedNum(int num, int[] mapping) {
        if (num < 10) {
            return mapping[num];
        }

        int mappedNum = 0;
        int placeValue = 1;

        while (num > 0) {
            int lastDigit = num % 10;
            int mappedDigit = mapping[lastDigit];
            mappedNum += placeValue * mappedDigit;

            placeValue *= 10;
            num /= 10;
        }

        return mappedNum;
    }

    private static class Pair {
        int mappedNum;
        int originalIdx;

        Pair(int mappedNum, int originalIdx) {
            this.mappedNum = mappedNum;
            this.originalIdx = originalIdx;
        }
    }
}