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
    
     int[][] intervals = new int[][] {{1,4},{2,6},{8,10},{15,18}};
     System.out.println();
     
     System.out.println("세번째 문제 답:"+meetingRoom(intervals));
     System.out.println();
     
     System.out.println("네번째 문제 답: "+meetingRoom2(intervals));
     System.out.println();
     Arrays.stream(mergeInterval(intervals)).forEach(e -> System.out.println(e[0]+","+e[1]));
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
        
        //new PriorityQueue<>() => 우선순위가 가장 낮은 숫자 순(최솟 값 상단)
        //new PriorityQueue<>(Collections.reverseOrder()) => 최댓 값 상단
        
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
        //1. start기준으로 오름차순 정렬
        Arrays.sort(intervals, (a,b) -> a[0]-b[0]);
        
        //2. end기준으로 오름차순 정렬
        Queue<int[]> q = new PriorityQueue<>((a,b) -> a[1]-b[1]);
        
        //3. 앞의 end타임이 뒤의 start타임보다 작다면 회의시간이 겹치지 않기 때문에 q에서 앞의 시간을 빼주면 된다.  
        for(int[] arr: intervals) {
            if(q.isEmpty()) q.offer(arr); 
            else {
                if(q.peek()[1] <= arr[0]) {
                    q.poll();
                }
                q.offer(arr);             
            }
        }
        return q.size();
    }
    
    public static int[][] mergeInterval(int[][] intervals) {
        Arrays.sort(intervals, (a,b)-> a[0]-b[0]);
        int[][] result = new int[intervals.length][2];
        
        result[0] = intervals[0];
        
        // 문제점. result[i-1]이 간격 겹쳐져서 한개로 됐을 때 비교하는 인덱스가 정확하기자 않음.
//        for(int i=1; i<intervals.length; i++) {
//            if(result[i-1][1] >= intervals[i][0]) {
//                result[i-1][1] = intervals[i][1];
//            }else {
//                result[i] = intervals[i];
//            }
//        }
        // 이차원배열을 list로 받아서 이차원배열로 만들 때 사용하는 방법.
        // List<int[]> list--> int[]값을 list.add()하고, 이차원 배열로 만들때 list.toArray(new int[][])
        List<int[]> res = new ArrayList<>();
        if(intervals.length == 0 || intervals == null)
            return res.toArray(new int[0][]);
        
        // 1. ds
        Arrays.sort(intervals, (a,b) -> a[0]-b[0]);
        
        // 2. for while
        //  합친 배열은 뒤의 배열값이랑도 비교해야 하니까 앞뒤 비교할 수 있는 로직을 짜야 함. 그럴라면 start,end값을 둘 다 저장할 수 있는 변수가 있어야 함.
        int start = intervals[0][0];
        int end = intervals[0][1];
        
        for(int i=1; i<intervals.length; i++) {
            if(end >= intervals[i][0]) {
                end = Math.max(end, intervals[i][1]);
            }else {
                res.add(new int[] {start, end});
                start = intervals[i][0];
                end = intervals[i][1];
            }
        }
        res.add(new int[] {start, end});
        return res.toArray(new int[res.size()][]);
    }
}
