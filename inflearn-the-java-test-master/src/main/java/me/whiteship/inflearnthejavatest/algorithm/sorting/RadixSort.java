package me.whiteship.inflearnthejavatest.algorithm.sorting;

import java.util.Arrays;

public class RadixSort {
    void radixSort(int arr[], int n){
        int m = getMax(arr,n);

        for(int exp = 1; m/exp>0; exp*=10)
            countSort(arr, n, exp);
    }

    private int getMax(int[] arr, int n) {
        int max = arr[0];
        for (int i=1; i<n; i++){
            if(arr[i] > max)
                max = arr[i];
        }

        return max;
    }

    void countSort(int arr[], int size, int place){
        int[] output = new int[size + 1];
        int max = arr[0];
        for(int i=1; i<size; i++) {
            if(arr[i] > max)
                max = arr[i];
        }
        int[] count = new int[max + 1];

        for(int i=0; i<max;++i)
            count[i]=0;

        for(int i=size -1; i>=0; i--){
            output[count[(arr[i]/place)%10]-1] = arr[i];
            count[(arr[i]/place)%10]--;
        }

        for(int i=0; i<size; i++){
            arr[i] = output[i];
        }
    }

    // Driver code
    public static void main(String args[]) {
        int[] data = { 121, 432, 564, 23, 1, 45, 788 };
        int size = data.length;
        RadixSort rs = new RadixSort();
        rs.radixSort(data, size);
        System.out.println("Sorted Array in Ascending Order: ");
        System.out.println(Arrays.toString(data));
    }
}
