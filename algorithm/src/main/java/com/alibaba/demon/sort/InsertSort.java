package com.alibaba.demon.sort;

/**
 * 插入排序
 *
 * @author: Demon
 * @create: 2019-04-08
 **/
public class InsertSort {


    public static void main(String[] args) {
        int[] array = {23, 4, 56, 2, 4, 56, -333, 344};
        insertSort(array);
        for (int anArray : array) {
            System.out.print(anArray+" ");
        }
    }

    private static void insertSort(int[] arraytoSort) {
        int temp;
        for (int i = 1; i < arraytoSort.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arraytoSort[j + 1] < arraytoSort[j]) {
                    temp = arraytoSort[j + 1];
                    arraytoSort[j + 1] = arraytoSort[j];
                    arraytoSort[j] = temp;
                }
            }
        }

    }
}
