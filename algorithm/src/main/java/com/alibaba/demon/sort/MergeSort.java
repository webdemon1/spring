package com.alibaba.demon.sort;

/**
 * 归并排序
 *
 * @author: Demon
 * @create: 2019-04-08
 **/
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {23, 4, 56, 2, 4, 56, -333, 344};

        int len = arr.length;
        int[] result = new int[len];
        merge_sort_recursive(arr, result, 0, len - 1);
        for (int anArray : arr) {
            System.out.print(anArray + " ");
        }
    }

    private static void merge_sort_recursive(int[] arr, int[] result, int start, int end) {
        if (start >= end)
            return;
        int len = end - start, mid = (len >> 1) + start;
        int start1 = start, end1 = mid;
        int start2 = mid + 1, end2 = end;
        merge_sort_recursive(arr, result, start1, end1);
        merge_sort_recursive(arr, result, start2, end2);
        int k = start;
        while (start1 <= end1 && start2 <= end2)
            result[k++] = arr[start1] < arr[start2] ? arr[start1++] : arr[start2++];
        while (start1 <= end1)
            result[k++] = arr[start1++];
        while (start2 <= end2)
            result[k++] = arr[start2++];
        for (k = start; k <= end; k++)
            arr[k] = result[k];
    }

}
