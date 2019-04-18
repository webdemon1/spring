package com.alibaba.demon.sort;

/**
 * 插入排序
 * @author: Demon
 * @create: 2019-04-08
 **/
public class InsertSort {


    public static void main(String[] args) {
        Integer[] array = {23, 4, 56, 2, 4, 56, -333, 344};
        insertSort(array);
        for (int anArray : array) {
            System.out.print(anArray+" ");
        }
    }

    private static <T extends Comparable<T>> void insertSort(T[] arraytoSort) {
        T temp;
        for (int i = 1; i < arraytoSort.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if ( arraytoSort[j].compareTo(arraytoSort[j + 1]) > 0) {
                    temp = arraytoSort[j + 1];
                    arraytoSort[j + 1] = arraytoSort[j];
                    arraytoSort[j] = temp;
                }
            }
        }
    }

    /**
     * 一般来说，插入排序都采用in-place在数组上实现。具体算法描述如下：

         1.从第一个元素开始，该元素可以认为已经被排序
         2.取出下一个元素，在已经排序的元素序列中从后向前扫描
         3.如果该元素（已排序）大于新元素，将该元素移到下一位置
         4.重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
         5.将新元素插入到该位置后
         6.重复步骤2~5
     */
}
