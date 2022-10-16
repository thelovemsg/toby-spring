package me.whiteship.inflearnthejavatest.member.recursion;

public class Code02 {
    public static void main(String[] args) {
        int[] data = {1,2,3,4,5,5,6,8,4};

        System.out.println("============= search2 test =============");
        System.out.println(search2(data,1, 6, 5));

        System.out.println("============= findMax test =============");
        System.out.println(findMax(data,1, 6));
    }

    public static int length(String str){
        if(str.equals(""))
            return 0;
        else
            return 1+length(str.substring(1));
    }

    public static void printChars(String str){
        if (str.length() == 0)
            return;
        else{
            System.out.println(str.charAt(0));
            printChars(str.substring(1));
        }
    }

    public static void printCharsReverse(String str){
        if(str.length() == 0){
            return;
        }else{
            printCharsReverse(str.substring(1));
            System.out.println(str.charAt(0));
        }
    }

    public void printInBinary(int n){
        if(n < 2){
            System.out.println(n);
        }else{
            printInBinary(n/2);
            System.out.println(n%2);
        }
    }

    public static int sum(int n ,int[] data){
        if(n<=0)
            return 0;
        else
            return sum(n-1, data) + data[n-1];
    }

    public static int search(int [] data, int n, int target){
        for(int i=0; i<n; i++)
            if(data[i] == target)
                return i;
        return -1;
    }

    public static int search(int[] data, int begin, int end, int target){
        if (begin > end)
            return -1;
        else if(target == data[begin])
            return begin;
        else
            return search(data, begin+1, end, target);
    }

    public static int search1(int [] data, int begin, int end, int target){
        if(begin > end)
            return -1;
        else if(target == data[end])
            return end;
        else
            return search1(data, begin, end-1, target);
    }

    public static int search2(int [] data, int begin, int end, int target){
        if(begin > end)
            return -1;
        else {
            int middle = (begin + end) /2;
            if(data[middle] == target)
                return middle;
            int index = search2(data, begin, middle-1, target);
            if (index != -1)
                return index;
            else
                return search2(data, middle+1, end ,target);
        }
    }

    public static int findMax(int[] data, int begin, int end){
        if(begin == end){
            return data[begin];
        }else {
            return Math.max(data[begin], findMax(data, begin+1, end));
        }
    }

    public static int findMax1(int[] data, int begin, int end) {
        if(begin == end)
            return data[begin];
        else {
            int middle = (begin+end)/2;
            int max1 = findMax(data, begin, middle);
            int max2 = findMax(data, middle+1, end);
            return Math.max(max1, max2);
        }
    }

    public static int binarySearch(String[] items, String target, int begin, int end) {
        if(begin > end)
            return -1;
        else{
            int middle = (begin+end)/2;
            int compResult = target.compareTo(items[middle]);
            if(compResult == 0)
                return middle;
            else if(compResult < 0)
                return binarySearch(items, target, begin, middle-1);
            else
                return binarySearch(items, target, middle+1, end);
        }
    }

}
