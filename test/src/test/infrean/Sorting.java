package test.infrean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class Sorting
{
    public static void main(String[] args) {
        
     Integer[] input = new Integer[] {3,2,3,1,2,4,5,5,6};
     int k = 4;
     Arrays.stream(moveZeros(input)).forEach(e -> System.out.print(e + " "));
     System.out.println();
     
     System.out.println(String.format("두번째 문제 답 : %s",kthLargestElementInAnArray(input, k)));
    
     int[][] intervals = new int[][] {{6,10},{1,3}};
     System.out.println();
     
     System.out.println("세번째 문제 답:"+meetingRoom(intervals));
    }
    
    public static Integer[] moveZeros(Integer[] nums) {
        int count = 0;
        for (int i=0; i<nums.length; i++) {
            if (nums[i] != 0) {
                nums[count] = nums[i];
                count++;
            }
        }
        for(int i=count; i<nums.length; i++) {
            nums[i] = 0;
        }
        return nums;
    }
    
    public static int kthLargestElementInAnArray(Integer[] nums, int k) {
        
//        Set<Integer> distinctNums = new HashSet<Integer>(Arrays.asList(nums));
//        int result = -1;
//        
//        // 1.HashSet 정렬하는 방법 : treeset이용하기
//        TreeSet<Integer> treeNums = new TreeSet<>();
//        treeNums.addAll(distinctNums);
//        
//        // 2. arraylist 이용하기
//        List<Integer> sortedList = new ArrayList<>(distinctNums);
//        Collections.sort(sortedList);
//        
//        result = sortedList.get(nums.length - k);  
        
        //  Min Heap사용하기
        // heap사이즈를 k사이즈로 두고, heap안에 값을 넣을수록 최솟값이 가장 최상단으로 올라가서 제거됨. 
        Queue<Integer> pq = new PriorityQueue<>();
        
        for(int i: nums) {
            pq.offer(i);
            if(pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
//        return result;
    }
    
    public static Boolean meetingRoom(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return true;
        }
        
        // start기준으로 sorting
        Arrays.sort(intervals, (a, b) -> a[0]-b[0]);    //오름차순
        
        // 전미팅 end > 후미팅 start보다 크면 안됨.
        int compareEnd = intervals[0][1];
        
        for(int i=1; i<intervals.length; i++) {
            if(intervals[i][0] < compareEnd) {
                return false;
            }
            compareEnd = intervals[i][1];
        }
        return true;
    }
    
    public static int meetingRoom2(int[][] intervals) {
        
        return 0;
    }
}
