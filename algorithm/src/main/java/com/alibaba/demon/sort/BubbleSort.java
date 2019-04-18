package com.alibaba.demon.sort;

/**
 *
 * @author: Demon
 * @create: 2019-04-08
 **/
public class BubbleSort {

    public static void main(String[] args) {
        Integer[] array = {23, 4, 56, 2, 4, 56, -333, 344};
        bubbleSort(array);
        for (int i : array) {
            System.out.print(i+" ");
        }
    }

    private static <T extends Comparable<T>> void bubbleSort(T[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) >0) {
                    T temp = arr[j];

                    arr[j] = arr[j + 1];

                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 冒泡排序算法的原理如下：
         1.比较相邻的元素。如果第一个比第二个大，就交换他们两个。
         2.对每一对相邻元素做同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。
         3.针对所有的元素重复以上的步骤，除了最后一个。
         4.持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
     */

}
