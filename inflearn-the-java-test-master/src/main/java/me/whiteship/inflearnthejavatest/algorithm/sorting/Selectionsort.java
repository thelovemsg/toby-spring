package me.whiteship.inflearnthejavatest.algorithm.sorting;

public class Selectionsort {
    public static void main(String[] args) {

        int[] arr = {4,5,9,7,2};
        for(int i=0; i<arr.length-1; i++){
            int minIdx = i;
            for(int j=i+1; j<arr.length; j++){
                if(arr[j]<arr[minIdx]){
                    minIdx = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = temp;
        }

        for (int i : arr) {
            System.out.println("i = " + i);
        }

    }
}
