package com.alibaba.demon.sort;

/**
 * @author: Demon
 * @create: 2019-04-08
 **/
public class SelectSort {

    public static void main(String[] args) {
        Integer[] array = {23, 4, 56, 2, 4, 56, -333, 344};
        selectionSort(array);
        for (int anArray : array) {
            System.out.print(anArray + " ");
        }
    }

    private static <T extends Comparable<T>> void selectionSort(T[] arr) {
        int min;
        T temp;
        for (int i = 0; i < arr.length; i++) {
            // 初始化未排序序列中最小数据数组下标
            min = i;
            for (int j = i + 1; j < arr.length; j++) {
                // 在未排序元素中继续寻找最小元素，并保存其下标
                if (arr[min].compareTo(arr[j]) > 0) {
                    min = j;
                }
            }
            // 将未排序列中最小元素放到已排序列末尾
            if (min != i) {
                temp = arr[min];
                arr[min] = arr[i];
                arr[i] = temp;
            }
        }
    }

    /**
     *
     * 选择排序（Selection sort）是一种简单直观的排序算法。它的工作原理如下。
     * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
     * 然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
     * 以此类推，直到所有元素均排序完毕。
     */
}
