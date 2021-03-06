package test.infrean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoPointer
{

    public static void main(String[] args) {
        String input = "pawwkea";
        System.out.println(LongestSubWithoutRepeatChar(input));
    }
    
    public static int LongestSubWithoutRepeatChar(String input) {
        Map<Character, Integer> map = new HashMap<>();
        int l=0, r=0, counter=0, max=0;
        
        // right, left pointer가 다르게 작용해야함.
        /**
         * right는 문자열의 문자 순서대로 오른쪽으로 이동하는 반면,
         * left는 중복된 문자가 있을 때를 기준으로 이동해야 함. 
         * right와 left의 차로 가장 긴 문자열 길이를 재는 것이 핵심.
         * 
         * 중복되는 부분의 저장은 
         * 우선 map에 key값:문자, value값: 문자갯수를 저장하고
         * 중복발생 시 해당 value값이 1이상인 것을 초기화함.
         * 
         * */
        
        while(r < input.length()) {
            char c = input.charAt(r);
            map.put(c, map.getOrDefault(c, 0)+1);
            if(map.get(c)>1) {
                counter++;
            }
            r++;
            
            while(counter > 0) {
                char c2 = input.charAt(l);
                if(map.get(c2)>1) {
                    counter--;
                }
                map.put(c2, map.get(c2)-1);
                l++;
            }
            max = Math.max(max, r-l);
        }
        return max;
    }
    
    public static int LongestSubMostTwoDistinct(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int l=0, r=0, counter=0, max=0;
        
        /**
         *  고유문자 2개 이상일 때를 체크해야 함.
         *  counter로 map에 문자넣을 때마다 증가시켜서
         *  counter 3개 이상 될 경우, 이전의 문자값을 삭제시켜서 counter를 삭제해야 함.
         *  그리고 r-l포인터 차이로 가장 긴 문자열 갯수 반환.
         *  
         * */
        
        while(r<s.length()) {
            char c = s.charAt(r);
            map.put(c, map.getOrDefault(c, 0)+1);
            if(map.get(c)==1) {
                counter++;
            }
            r++;
            
            while(counter>2) {
                char c2 = s.charAt(l);
                map.put(c2, map.get(c2)-1);
                if(map.get(c2)==0) {
                    counter--;
                }
                l++;
            }
            max= Math.max(max, r-l);
        }
        return max;
    }
    
    public static List<Integer> findAllAnagramInAString(String input, String anagram) {
        
        /**
         * 해당하는 알파벳이 있는지 여부를 판단하기 위해서 int[]에 char형태로 차를 구해서 해당 알파벳자릿값의 인덱스++해줌.
         * 
         * */
        List<Integer> result = new ArrayList<>();
        int[] ana = new int[26];
        for(int i=0; i<anagram.length(); i++) {
            ana[anagram.charAt(i)-'a']++;
        }
        
        // left값이 i, right값이 j
        // i+j는 전체 문자열길이가 되고,
        // j는 아나그램 문자열 길이만큼 측정해서 
        // 아나그램 문자배열값과 같은지 right값이 커질수록 비교함.
        for(int i=0; i<input.length()-anagram.length()+1; i++) {
            int[] inp = new int[26];
            for(int j=0; j<anagram.length(); j++) {                   
                inp[input.charAt(i+j) - 'a']++;
            }
            if(check(ana, inp)) {
                result.add(i);
            }
        }
        return result;
    }
    
    private static boolean check(int[] ana, int[] inp) {
        for(int i=0; i<ana.length; i++) {
            if(ana[i]!=inp[i]) {
                return false;
            }
        }
        return true;
    }
}
