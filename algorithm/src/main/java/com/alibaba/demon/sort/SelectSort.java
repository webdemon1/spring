package com.alibaba.demon.sort;

/**
 * @author: Demon
 * @create: 2019-04-08
 **/
public class SelectSort {

    public static void main(String[] args) {
        int[] array = {23, 4, 56, 2, 4, 56, -333, 344};
        selectSort(array);
        for (int anArray : array) {
            System.out.print(anArray + " ");
        }
    }

    public static void selectSort(int[] arraytoSort) {
        for (int i = 0; i < arraytoSort.length - 1; i++) {
            int min = i;
            int temp;
            //find min
            for (int j = i + 1; j < arraytoSort.length; j++) {
                if (arraytoSort[j] < arraytoSort[min]) {
                    min = j;
                }
            }
            //swap
            temp = arraytoSort[min];
            arraytoSort[min] = arraytoSort[i];
            arraytoSort[i] = temp;
        }
    }
}
