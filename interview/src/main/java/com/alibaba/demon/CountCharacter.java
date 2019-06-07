package com.alibaba.demon;

import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个全是字母的字符串
 * 求出现最多的那个字母，如果两个字母出现次数一样，则取先出现的;
 */
public class CountCharacter {


    public static void main(String[] args) {
        String s1 = "ddffgrgmkjjjjhggggffg";
        String result1 = getMaxExistsCharacter(s1);
        System.out.println(result1);

        String s2 = "ddfffrrrt";
        String result2 = getMaxExistsCharacter(s2);
        System.out.println(result2);
    }

    private static String getMaxExistsCharacter(String input) {
        if (StringUtils.isBlank(input)) {
            return null;
        }

        Map<Character, Integer> characterCountMap = new LinkedHashMap<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (characterCountMap.containsKey(c)) {
                characterCountMap.put(c, characterCountMap.get(c) + 1);
            } else {
                characterCountMap.put(c, 1);
            }
        }

        Set<Map.Entry<Character, Integer>> entrySet = characterCountMap.entrySet();
        int value = 0;
        Character c = null;
        boolean flag = true;
        for (Map.Entry<Character, Integer> entry : entrySet) {
            if (flag) {
                value = entry.getValue();
                c = entry.getKey();
                flag = false;
            }
            int characterCount = entry.getValue();
            if (characterCount > value) {
                value = characterCount;
                c = entry.getKey();
            }
        }
        return c + ":" + value;
    }

}
