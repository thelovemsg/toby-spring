package me.whiteship.inflearnthejavatest.algorithm.sorting;

public class CountingSort {

    public static void main(String[] args) {
        int[] arr = new int[100];
        int[] counting = new int[31];
        int[] result = new int[100];

        for(int i=0; i<arr.length; i++){
            arr[i] = (int)(Math.random()*31);
            System.out.println(arr[i]);
        }

        for(int i=0; i<arr.length; i++){
            counting[arr[i]]++;
        }

        for(int i=1; i<counting.length; i++){
            counting[i] += counting[i=1];
        }

        for(int i=arr.length -1; i>=0; i--){
            int value = arr[i];
            counting[value]--;
            result[counting[value]] = value;
        }

        System.out.println("array[]");
        for(int i=0; i<arr.length; i++){
            if(i % 10 == 0) System.out.println();
            System.out.println(arr[i]);
        }
    }

}
