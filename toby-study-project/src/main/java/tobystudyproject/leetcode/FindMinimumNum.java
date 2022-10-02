package tobystudyproject.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

public class FindMinimumNum {
    public static void main(String[] args) {
        int[] numbers = {1,2,3,4,6,7,8,0};
        int[] answer = new int[10];
        int sum = 0;
        int length = numbers.length;
        for(int i=0; i<length; i++){
            answer[numbers[i]]++;
        }

        for(int i=0; i<9; i++){
            if(answer[i] == 0)
                sum += i;
        }
        System.out.println("sum = " + sum);

    }
}
