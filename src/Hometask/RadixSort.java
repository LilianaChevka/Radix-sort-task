package Hometask;

import java.util.Arrays;
import java.util.Scanner;

public class RadixSort {
    private static void sort(int[] array) {
        RadixSort.sort(array,10);
    }

    private static void sort(int[] array, int radix) {
        if (array.length == 0) {
            return;
        }

        int minValue = array[0];
        int maxValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < minValue) {
                minValue = array[i];
            } else if (array[i] > maxValue) {
                maxValue = array[i];
            }
        }

        int exponent = 1;
        while ((maxValue - minValue) / exponent >= 1) {
            RadixSort.countingSortByDigit(array, radix, exponent, minValue);
            exponent *= radix;
        }
    }

    private static void countingSortByDigit(
            int[] array, int radix, int exponent, int minValue) {
        int bucketIndex;
        int[] buckets = new int[radix];
        int[] output = new int[array.length];

        for (int i = 0; i < radix; i++) {
            buckets[i] = 0;
        }

        for (int i = 0; i < array.length; i++) {
            bucketIndex = (((array[i] - minValue) / exponent) % radix);
            buckets[bucketIndex]++;
        }

        for (int i = 1; i < radix; i++) {
            buckets[i] += buckets[i - 1];
        }

        for (int i = array.length - 1; i >= 0; i--) {
            bucketIndex = (((array[i] - minValue) / exponent) % radix);
            output[--buckets[bucketIndex]] = array[i];
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = output[i];
        }
    }

    public static void main(String args[])
    {
        Scanner scan = new Scanner(System.in);
        RadixSort ob = new RadixSort();
        System.out.println("Enter array length: ");
        int size = scan.nextInt();
        int[]a = new int[size];

        for(;size <=0;){
            System.out.println("Enter a number greater than 0:");
            size = scan.nextInt();
        }
        System.out.println("Insert array elements: ");
        for( int i = 0;i<size;i++)
            a[i] = scan.nextInt();

        ob.sort(a);
        System.out.println("Sorted Array:");
        System.out.println(Arrays.toString(a));
    }
}


