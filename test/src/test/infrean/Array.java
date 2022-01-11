package test.infrean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Array
{
    public static void main(String[] args) {
        int[] nums = new int[] {2, 8, 11, 14};
        int target = 16;
        
        int[] temperatures = new int[] {73, 74, 75, 71, 69, 72, 76, 73};
        
        Arrays.stream(twoSum(nums, target)).forEach(e -> System.out.print(e+","));
    }
    
    
    public static int[] twoSum(int[] nums, int target) {
        
        HashMap<Integer,Integer> save = new HashMap<Integer,Integer>(); 
        int[] result = new int[2];
        
        // output이 배열인덱스가 아니고, 요소가 들어있는 순서임. 
        for(int i=0; i<nums.length; i++) {
            if (save.containsKey(nums[i])) {
                int index = save.get(nums[i]);
                result[0] = index+1;
                result[1] = i+1;
            }else {
                save.put(target-nums[i], i);
            }
        }
        return result;
    }
    
    public static int[] dailyTemperature(int[] temp) {
        
        int[] result = new int[temp.length];
        Stack<Integer> compare = new Stack<>();
        
        /**
         * stack이 필요할 때
         * 계산기 문제 식으로 
         * 가장 마지막 요소와 그 이후의 값들과 비교해야 할 때..
         * 예를 들어
         * 1,2,3,4 있을 때
         * 1과 2~4를 비교하고,
         * 2는 3~4를 비교해야 하는 상황에
         * stack.peek사용하면 됨.
         * */
        for(int i=1;i<temp.length; i++) {
            while(!compare.isEmpty() && temp[compare.peek()] < temp[i]) {
                int index = compare.pop();
                result[index] = i-index;
            }
            compare.push(i);
        }
        
        return result;
    }
    
    
}
