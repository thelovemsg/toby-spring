package me.whiteship.inflearnthejavatest.algorithm.sorting;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Insertionsort {

    public static void main(String[] args) {
/*        int[] arr = {9,8,7,2,3,4,5};
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
        }*/
        int[][] item1 = {{1,1},{4,5},{3,8}};
        int[][] item2 = {{3,1},{1,5}};

        Arrays.sort(item1, Comparator.comparingInt(o -> o[0]));


    }

    public static int[] insertionSort(int[] arr){
        for(int i=1; i<arr.length; i++){
            int j=i;
            while((j>0) && (arr[j-1] > arr[j])){
                int temp = arr[j];
                arr[j] = arr[j-1];
                arr[j-1] = temp;
                j--;
            }
        }
        return arr;
    }

    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int first = items1.length;
        int second = items2.length;
        Stream.of(items1, items2).flatMap(Arrays::stream).forEach(item -> map.put(item[0], map.getOrDefault(item[0], 0) + item[1]));


        List<Integer> collect1 = Arrays.stream(items1).map(o -> o[0]).sorted().collect(Collectors.toList());
        List<Integer> collect2 = Arrays.stream(items2).flatMapToInt(o -> Arrays.stream(o)).mapToObj(i -> i).sorted().collect(Collectors.toList());

        List<Integer> keyList = new ArrayList<>();
        keyList.addAll(collect1);
        keyList.addAll(collect2);
        Collections.sort(keyList);
        keyList = keyList.stream().distinct().collect(Collectors.toList());

        Map<Integer, Integer> map1 = Arrays.stream(items1).collect(Collectors.toMap(o -> o[0], o -> o[1]));
        Map<Integer, Integer> map2 = Arrays.stream(items2).collect(Collectors.toMap(o -> o[0], o -> o[1]));
        Arrays.stream(items2).sorted(Comparator.comparingInt(o -> o[0])).map(o -> map2.put(o[0], o[1]));

        return first > second ? calList(keyList, map1, map2) : calList(keyList, map2, map1);
    }

    public static List<List<Integer>> calList(List<Integer> keyList, Map<Integer, Integer> map1, Map<Integer, Integer> map2){
        List<List<Integer>> result = new ArrayList<>();

        for (Integer integer : keyList) {
            List<Integer> list = new ArrayList<>();
            int sum = map1.getOrDefault(integer, 0) + map2.getOrDefault(integer, 0);
            list.add(integer);
            list.add(sum);
            result.add(list);
        }

        return result;
    }

}
