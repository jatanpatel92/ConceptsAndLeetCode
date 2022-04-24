/*
Given a collection of strings, write a method to determine the nTh longest string in that list.
Example: {Yuri, Interview, Nordstrom, Cat, Dog, Telephone, AVeryLongString, This code puzzle is easy}

nTh = 1 > would return "This code puzzle is easy".

This is an interview question at Nordstrom.
*/
import java.util.*;
public class MyClass {
    public static void main(String args[]) {
      String[] sentences = {
           "Yuri", "Interview", "Nordstrom", "Cat", "Dog", "Telephone", "AVeryLongString", "This code puzzle is easy"
      };
      // Sort with custom comparator
      //Arrays.sort(sentences, (a, b) -> Integer.compare(b.length(), a.length()));
      // Quick Select and Partition
      quickSelect(sentences, 1, 0, sentences.length-1);
      System.out.println("\nQuick select : " + sentences[0]);
    }
    private static void quickSelect(String[] arr, int k, int l, int h){
        if(h>arr.length || l<0 || l>h) return;
        int pivot = partition(arr, l, h);
        if(pivot == k-1)
            return;
        if(pivot>=k-1){
            quickSelect(arr, k, l, pivot-1);
        }
        else{
            quickSelect(arr, k, pivot+1, h);
        }
    }
    private static int partition(String[] arr, int l, int h){
        int k = l;
        int i = l;
        int j = h;
        while(i<j){
            while(i<arr.length && arr[i].length()>=arr[k].length()) i++;
            while(j>=0 && arr[j].length()<arr[k].length()){
                j--;
            }
            if(i<j){
               swap(arr, i, j);
            }
        }
        swap(arr, l, j);
        System.out.println();
        System.out.println("Pivot: "+j);
        for(String s:arr){
            System.out.print(s+", ");
        }
        return j;
    }
    private static void swap(String[] arr, int i, int j){
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
