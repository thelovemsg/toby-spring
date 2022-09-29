package tobystudyproject.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Problem2418 {
    public static void main(String[] args) {

        String[] names = {"Mary","John","Emma"};
        int[] height = {180, 165, 170};

        for(int i=0; i<names.length; i++){
            for(int j=i+1; j<names.length; j++){
                if(height[i] > height[j]){
                    String temp1 = names[j];
                    int temp2 = height[j];

                    names[j] = names[i];
                    height[j] = height[i];
                    names[i] = temp1;
                    height[i] = temp2;
                }
            }
        }
        List<String> result = Arrays.stream(names).collect(Collectors.toList());
        Collections.reverse(result);
        result.toArray(new String[result.size()]);

        for (String name : names) {
            System.out.println("name = " + name);
        }
    }
}
