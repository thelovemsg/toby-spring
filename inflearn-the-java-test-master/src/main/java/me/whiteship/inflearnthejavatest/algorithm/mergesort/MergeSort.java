package me.whiteship.inflearnthejavatest.algorithm.mergesort;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MergeSort {
    public static void main(String[] args) {

/*        String[] strs = {"flower","flow","flight"};

        Arrays.sort(strs, Comparator.comparingInt(String::length));*/
        int[] nums = {1,5,3,2,2,7,6,4,8,9};
        Arrays.sort(nums); // 1,2,2,3,4,5,6,7,8,9
        int[] compare = new int[nums.length];
        IntStream.range(0,nums.length).forEach(s -> compare[s] = s+1);
        for (int i : compare) {
            System.out.println("i = " + i);
        }

    }
    private static void mergeSort(int[] arr){
        int[] tmp = new int[arr.length];
        mergeSort(arr,tmp, 0, arr.length -1);
    }

    private static void mergeSort(int[] arr, int[] tmp, int start, int end) {
        if(start < end){
            int mid = (start+end) / 2;
            mergeSort(arr,tmp, start, mid);
            mergeSort(arr,tmp, mid+1, end);
            merge(arr, tmp, start, mid, end);
        }
    }

    private static void merge(int[] arr, int[] tmp, int start, int mid, int end) {
        for (int i =start; i<=end; i++) {
            tmp[i] = arr[i];
        }
        int part1 = start;
        int part2 = mid + 1;
        int index = start;
        while(part1<=mid && part2 <= end){
            if(tmp[part1] <= tmp[part2]) {
                arr[index] = tmp[part1];
                part1++;
            }else{
                arr[index] = tmp[part2];
                part2++;
            }
            index++;
        }
        for(int i=0; i<=mid-part1; i++){
            arr[index + i] = tmp[part1+i];
        }
    }
}
