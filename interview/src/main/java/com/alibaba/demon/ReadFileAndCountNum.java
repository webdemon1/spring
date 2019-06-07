package com.alibaba.demon;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
/**
 * 给一个全是字母的文件，统计每个小写字母出现的次数
 * 并按字母的顺序输出到一个文件 “hello”
 * eg:
 * e:1
 * h:1
 * l:2
 * o:1
 */
public class ReadFileAndCountNum {

    @Test
    public void test() throws IOException {
        countAndWrite2File("C:\\workspace\\spring\\interview\\src\\main\\resources\\characters.txt");
    }

    private void countAndWrite2File(String path) throws IOException {
        if (StringUtils.isBlank(path)) {
            return;
        }

        FileReader fr = new FileReader(path);
        int len;
        Map<Character, Integer> characterMap = new HashMap<>();
        while ((len = fr.read()) != -1) {
            char c = (char) len;
            if (!(c > 'a' && c < 'z')) {
                continue;
            }
            if (characterMap.containsKey(c)) {
                characterMap.put(c, characterMap.get(c) + 1);
            } else {
                characterMap.put(c, 1);
            }
        }
        fr.close();

        Map<Character,Integer> result = characterMap.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b,
                        Maps::newLinkedHashMap));

        FileWriter fw = new FileWriter("C:\\workspace\\spring\\interview\\src\\main\\resources\\out.txt");
        Set<Map.Entry<Character,Integer>> set = result.entrySet();
        for (Map.Entry<Character,Integer> entry : set) {
            Character key = entry.getKey();
            Integer value = entry.getValue();
            fw.write(key+":"+value);
            fw.write("\n");

        }
       fw.close();
    }

}

