package me.whiteship.inflearnthejavatest.algorithm.sorting;

public class Insertionsort {

    public static void main(String[] args) {
        int[] arr = {9,8,7,2,3,4,5};
        for(int index = 1 ; index < arr.length ; index++){ // 1.
            int temp = arr[index];
            int prev = index - 1;
            while( (prev >= 0) && (arr[prev] > temp) ) {    // 2.
                arr[prev+1] = arr[prev];
                prev--;
            }
            arr[prev + 1] = temp;                           // 3.
        }

        for (int i : arr) {
            System.out.println("i = " + i);
        }

    }

}
