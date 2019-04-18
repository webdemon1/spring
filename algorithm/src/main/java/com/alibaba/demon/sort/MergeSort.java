package com.alibaba.demon.sort;

/**
 * 归并排序
 *
 * @author: Demon
 * @create: 2019-04-08
 **/
public class MergeSort {

    public static void main(String[] args) {
        Integer[] arr = {23, 4, 56, 2, 4, 56, -333, 344};

        int len = arr.length;
        Integer[] result = new Integer[len];
        merge_sort_recursive(arr, result, 0, len - 1);
        for (int anArray : arr) {
            System.out.print(anArray + " ");
        }
    }

    private static <T extends Comparable<T>> void merge_sort_recursive(T[] arr, T[] result,
                                                                       int start, int end) {
        if (start >= end)
            return;
        int len = end - start, mid = (len >> 1) + start;
        int start1 = start;
        int start2 = mid + 1;
        merge_sort_recursive(arr, result, start1, mid);
        merge_sort_recursive(arr, result, start2, end);
        int k = start;
        while (start1 <= mid && start2 <= end)
            result[k++] = arr[start2].compareTo(arr[start1]) > 0 ? arr[start1++] : arr[start2++];
        while (start1 <= mid)
            result[k++] = arr[start1++];
        while (start2 <= end)
            result[k++] = arr[start2++];
        for (k = start; k <= end; k++)
            arr[k] = result[k];
    }

/**
 * from wikipedia
 * 归并操作（merge), 也叫归并算法，指的是将两个已经排序的序列合并成一个序列的操作。归并排序算法依赖归并操作

 递归法（Top-down）
 1.申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列
 2.设定两个指针，最初位置分别为两个已经排序序列的起始位置
 3.比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置
 4.重复步骤3直到某一指针到达序列尾
 5.将另一序列剩下的所有元素直接复制到合并序列尾
 */


}
