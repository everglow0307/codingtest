package test.infrean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Array
{
    public static void main(String[] args)
    {
        int[] nums = new int[] { 2, 8, 11, 14 };
        int target = 16;

        int[] temperatures = new int[] { 73, 74, 75, 71, 69, 72, 76, 73 };

        int[] nums2 = new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 };

        Arrays.stream(twoSum(nums, target)).forEach(e -> System.out.print(e + ","));
        System.out.println();

        Arrays.stream(dailyTemperature(temperatures)).forEach(e -> System.out.print(e + " "));
        System.out.println();

        System.out.println(maximumSubarray(nums2));

        String[] strs = new String[] { "eat", "tea", "tan", "ate", "nat", "bat" };
        System.out.println();

        System.out.println(groupAnagrams(strs));
        System.out.println(groupAnagramsNotSorting(strs));

        int[] height = new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
        System.out.println();
        System.out.println(height);

        int[] rangeNum = new int[] { 2, 3, 5, 50, 75 };
        int lower = 0;
        int upper = 99;
        System.out.println();
        System.out.println(missingRanges(rangeNum, lower, upper));
    }

    public static int[] twoSum(int[] nums, int target)
    {
        HashMap<Integer, Integer> save = new HashMap<Integer, Integer>();
        int[] result = new int[2];

        // output이 배열인덱스가 아니고, 요소가 들어있는 순서임.
        for (int i = 0; i < nums.length; i++)
        {
            if (save.containsKey(nums[i]))
            {
                int index = save.get(nums[i]);
                result[0] = index + 1;
                result[1] = i + 1;
            }
            else
            {
                save.put(target - nums[i], i);
            }
        }
        return result;
    }

    public static int[] dailyTemperature(int[] temp)
    {

        int[] result = new int[temp.length];
        Stack<Integer> compare = new Stack<>();
        /**
         * stack이 필요할 때 계산기 문제 식으로 가장 마지막 요소와 그 이후의 값들과 비교해야 할 때.. 예를 들어 1,2,3,4 있을 때 1과 2~4를 비교하고, 2는 3~4를 비교해야 하는 상황에
         * stack.peek사용하면 됨.
         */
        for (int i = 0; i < temp.length; i++)
        {
            while (!compare.isEmpty() && temp[compare.peek()] < temp[i])
            {
                int index = compare.pop();
                result[index] = i - index;
            }
            compare.push(i);
        }

        return result;
    }

    public static int maximumSubarray(int[] nums)
    {

        int curMax = nums[0];
        int allMax = nums[0];

        for (int i = 1; i < nums.length; i++)
        {
            curMax = Math.max(nums[i], nums[i] + curMax);
            allMax = Math.max(curMax, allMax);
        }
        return allMax;
    }

    public static List<List<String>> groupAnagrams(String[] strs)
    {

        List<List<String>> result = new ArrayList<>();
        if (strs == null || strs.length == 0)
        {
            return result;
        }
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs)
        {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);

            String key = String.valueOf(chars);

            // if(map.containsKey(key)) {
            // map.get(key).add(str);
            // } else {
            // List<String> words = new ArrayList<>();
            // words.add(str);
            // map.put(key, words);
            // }
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }

        // result.addAll(map.values());
        // return result;
        // return new ArrayList<>(map.values());
        for (Map.Entry<String, List<String>> entry : map.entrySet())
        {
            result.add(entry.getValue());
        }
        return result;
    }

    public static List<List<String>> groupAnagramsNotSorting(String[] strs)
    {
        Map<String, List<String>> map = new HashMap<>();
        List<List<String>> result = new ArrayList<>();

        for (String str : strs)
        {
            int[] count = new int[26];
            for (int k = 0; k < str.length(); k++)
            {
                count[str.charAt(k) - 'a']++;
            }
            String key = Arrays.toString(count);

            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }

        result.addAll(map.values());
        return result;
    }

    public static int trappingRainWater(int[] nums)
    {
        // {0, 1, 0, 2, 1, 0, 1, 3, 2 ,1 ,2, 1}

        int[] left = new int[nums.length];
        int[] right = new int[nums.length];

        int max = nums[0];
        left[0] = nums[0];

        // left max
        for (int i = 1; i < nums.length; i++)
        {
            if (nums[i] < max)
            {
                left[i] = max;
            }
            else
            {
                left[i] = nums[i];
                max = nums[i];
            }
        }

        // right max
        max = nums[nums.length - 1];
        right[nums.length - 1] = nums[nums.length - 1];

        for (int i = nums.length - 2; i >= 0; i--)
        {
            if (nums[i] < max)
            {
                right[i] = max;
            }
            else
            {
                right[i] = nums[i];
                max = nums[i];
            }
        }

        int result = 0;
        for (int i = 0; i < nums.length; i++)
        {
            result += Math.min(left[i], right[i]) - nums[i];
        }
        return result;
    }

    public static List<String> missingRanges(int[] nums, int lower, int upper)
    {

        List<String> result = new ArrayList<>();
        int len = nums.length;

        if (nums == null || nums.length == 0)
        {
            return result;
        }

        // 1
        if (lower < nums[0])
        {
            result.add(makeRange(lower, nums[0] - 1));
        }

        // 2
        for (int i = 0; i < nums.length - 1; i++)
        {
            if (nums[i] != nums[i + 1] && nums[i] + 1 < nums[i + 1])
            {
                result.add(makeRange(nums[i] + 1, nums[i + 1] - 1));
            }
        }

        // 3
        if (upper > nums[len - 1])
        {
            result.add(makeRange(nums[len - 1] + 1, upper));
        }
        return result;
    }

    public static String makeRange(int low, int high)
    {
        return low == high ? String.valueOf(low) : String.format("%d -> %d", low, high);
    }

    public static List<Integer> spiralMatrix(int[][] matrix)
    {

        List<Integer> result = new ArrayList<>();

        int rowStart = 0;
        int rowEnd = matrix.length;

        int colStart = 0;
        int colEnd = matrix[0].length;

        while (rowStart <= rowEnd && colStart <= colEnd)
        {
            // right
            for (int i = colStart; i <= colEnd; i++)
            {
                result.add(matrix[rowStart][i]);
            }
            rowStart++;

            // down
            for (int i = rowStart; i <= rowEnd; i++)
            {
                result.add(matrix[i][colEnd]);
            }
            colEnd--;

            // left
            if(rowStart <= rowEnd) {
                for (int i = colEnd; i >= colStart; i--)
                {
                    result.add(matrix[rowEnd][i]);
                }
            }
            rowEnd--;

            // up
            if (colStart <= colEnd)
            {
                for (int i = rowEnd; i >= rowStart; i--)
                {
                    result.add(matrix[i][colStart]);
                }
            }
            colStart++;
        }

        return result;
    }
}
